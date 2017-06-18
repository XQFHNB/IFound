package com.example.anif.module_activities.widgets;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.example.anif.R;
import com.example.anif.base.FragBase;
import com.example.anif.beans.MyUser;
import com.example.anif.module_activities.model.OnSaveListener;
import com.example.anif.module_activities.presenter.IPresenterActivities;
import com.example.anif.module_activities.presenter.PresenterActivitiesImpl;
import com.example.anif.module_activities.view.IViewPublishActivities;
import com.example.anif.utils.Constants;
import com.example.anif.utils.UtilLog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author XQF
 * @created 2017/6/17
 */
public class FragPublishActivities extends FragBase implements IViewPublishActivities {


    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;


    @BindView(R.id.edt_title_frag_publish_activity)
    protected EditText mEditTextTitle;


    @BindView(R.id.edt_description_frag_publish_activity)
    protected EditText mEditTextDescription;

    @BindView(R.id.btn_addLabel_frag_publish_activity)
    protected Button mButtonAddLabel;

    @BindView(R.id.tablelayout)
    protected TableLayout mTableLayout;

    @BindView(R.id.bottomline_tablelayout)
    protected TextView mLabelBottomline;


    @BindView(R.id.btn_add_image_frag_publish_activites)
    protected Button mButtonaddSelectImage;

    @BindView(R.id.linearlayout_addImage_frag_publish_activites)
    protected LinearLayout mLinearLayout;

    @BindView(R.id.btn_selectFromimages_frag_publish_activites)
    protected Button mButtonSelectImagesFrom;

    @BindView(R.id.imageV_frag_publish_activites)
    protected ImageView mImageView;


    /**
     * 标签的checkbox部分
     */
    @BindView(R.id.cb1)
    protected CheckBox mCheckBox1;
    @BindView(R.id.cb2)
    protected CheckBox mCheckBox2;
    @BindView(R.id.cb3)
    protected CheckBox mCheckBox3;

    @BindView(R.id.cb4)
    protected CheckBox mCheckBox4;
    @BindView(R.id.cb5)
    protected CheckBox mCheckBox5;


    //属性
    private String label;
    private String description;
    private IPresenterActivities mIPresenterActivities;
    private String title;
    private AVObject mObjectActivity;


    //图片字节数组
    private byte[] mImageViewBytes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.frag_publish_activities_layout, container, false);
        ButterKnife.bind(this, itemView);
        mIPresenterActivities = new PresenterActivitiesImpl(this);
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
    }

    @OnClick(R.id.btn_addLabel_frag_publish_activity)
    public void onBtnSelectLabelClick() {
        mTableLayout.setVisibility(View.VISIBLE);
        mLabelBottomline.setVisibility(View.VISIBLE);
    }

    /**
     * 添加照片按钮点击事件
     */
    @OnClick(R.id.btn_add_image_frag_publish_activites)
    public void onBtnAddImage() {
        mLinearLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 从相册中选择照片事件
     */
    @OnClick(R.id.btn_selectFromimages_frag_publish_activites)
    public void onBtnSelectFromAlbum() {
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
                mImageView.setImageBitmap(MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData()));
                mImageViewBytes = getBytes(getActivity().getContentResolver().openInputStream(data.getData()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    /**
     * 初级检查是不是都有输入
     *
     * @return
     */
    private boolean checkOk() {
        label = new String();
        if (mCheckBox1.isChecked()) {
            label += 0;
        } else if (mCheckBox2.isChecked()) {
            label += 1;
        } else if (mCheckBox3.isChecked()) {
            label += 2;
        } else if (mCheckBox4.isChecked()) {
            label += 3;
        } else if (mCheckBox5.isChecked()) {
            label += 4;
        }
        title = mEditTextTitle.getText().toString().trim();
        description = mEditTextDescription.getText().toString().trim();
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description) || TextUtils.isEmpty(label)) {
            return false;
        }
        return true;
    }

    private void saveInBackground() {

        mObjectActivity = new AVObject(Constants.BEAN_KEY_ACTIVITY_TABLE);
        mObjectActivity.put(Constants.BEAN_KEY_ACTIVITY_OWNER, MyUser.getCurrentUser());
        mObjectActivity.put(Constants.BEAN_KEY_ACTIVITY_TITLE, title);
        mObjectActivity.put(Constants.BEAN_KEY_ACTIVITY_DESCRIPTION, description);
        mObjectActivity.put(Constants.BEAN_KEY_ACTIVITY_LABEL, label);
        mObjectActivity.put(Constants.KEY_TYPE, Constants.BEAN_KEY_ACTIVITY_TABLE);
        //有图片的话上传图片
        if (mImageViewBytes != null) {
            AVFile mAvFile = new AVFile(Constants.BEAN_KEY_SECONDHAND_AVFILE, mImageViewBytes);
            mObjectActivity.put(Constants.BEAN_KEY_ACTIVITY_IMAGE, mAvFile);
        }
        mIPresenterActivities.saveData(new OnSaveListener() {
            @Override
            public void onSucess(AVException e) {
                if (e == null) {
                    getActivity().finish();
                }
            }
        });

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


    @Override
    public AVObject saveData() {
        return mObjectActivity;
    }
}
