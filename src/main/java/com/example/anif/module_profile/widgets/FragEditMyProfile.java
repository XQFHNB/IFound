package com.example.anif.module_profile.widgets;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.SaveCallback;
import com.bumptech.glide.Glide;
import com.example.anif.R;
import com.example.anif.base.FragBase;
import com.example.anif.beans.MyUser;
import com.example.anif.utils.UtilLog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author XQF
 * @created 2017/6/18
 */
public class FragEditMyProfile extends FragBase {


    public static final String KEY_NAME = "name";
    public static final String KEY_AVATAR = "avatar";
    public static final String KEY_AVATAR_AVFILE = "avatarfile";


    @BindView(R.id.btn_post_avatar)
    protected Button mButtonPostAvatar;

    @BindView(R.id.profile_avatar)
    protected CircleImageView mCircleImageView;

    @BindView(R.id.edt_name)
    protected EditText mEditTextName;
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    private MyUser mMyUser;

    //图片字节数组
    private byte[] mImageViewBytes;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.frag_edit_myprofile, container, false);
        ButterKnife.bind(this, itemView);
        mMyUser = MyUser.getCurrentUser();
        init();
        return itemView;
    }

    private void init() {
        mToolbar.setNavigationIcon(R.drawable.publish_edit_close);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed(true);
            }
        });
        mToolbar.inflateMenu(R.menu.complete_menu);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_complete) {
                    if (checkOk()) {
//                        final ProgressDialog dialog = showSpinnerDialog();
                        saveInBackground();
                    } else {
                        toast("请完善必填项目", getActivity());
                    }
                }
                return true;
            }
        });

        mEditTextName.setText("" + mMyUser.get(KEY_NAME));
        AVFile avFile = (AVFile) mMyUser.get(KEY_AVATAR);
        if (avFile != null) {
            Glide.with(getActivity()).load(avFile.getUrl()).into(mCircleImageView);
        }
    }


    /**
     * 初级检查是不是都有输入
     *
     * @return
     */
    private boolean checkOk() {
        if (TextUtils.isEmpty(mEditTextName.getText())) {
            return false;
        }
        return true;
    }

    private void saveInBackground() {

        mMyUser.put(KEY_NAME, mEditTextName.getText().toString().trim());
        if (mImageViewBytes != null) {
            AVFile mAvFile = new AVFile(KEY_AVATAR_AVFILE, mImageViewBytes);
            mMyUser.put(KEY_AVATAR, mAvFile);
        }
        mMyUser.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                toast("用户信息更新成功", getActivity());
                getActivity().finish();
            }
        });

    }


    @OnClick(R.id.btn_post_avatar)
    public void onBtnPostAvatar() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    /**
     * 从相册中获取的图片
     *
     * @param requestCode 对应开启时的代码
     * @param resultCode  是否获取成功
     * @param data        图片数据
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UtilLog.d("test", "简单测试一下是不是调用了这个方法");
        if (resultCode == getActivity().RESULT_OK && requestCode == 1) {
            try {
                mCircleImageView.setImageBitmap(MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData()));
                mImageViewBytes = getBytes(getActivity().getContentResolver().openInputStream(data.getData()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取图片的字节数组用于创建AVFile
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
