package com.example.anif.module_seondhand.widgets;

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
import com.example.anif.module_seondhand.model.OnSaveListener;
import com.example.anif.module_seondhand.presenter.PresenterSecond;
import com.example.anif.module_seondhand.presenter.PresenterSecondImpl;
import com.example.anif.module_seondhand.view.ViewPublishSecond;
import com.example.anif.utils.Constants;
import com.example.anif.utils.UtilLog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author XQF
 * @created 2017/4/19
 */
public class FragPublishSecondhand extends FragBase implements ViewPublishSecond {


    //toolbar
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;


    //编辑标题
    @BindView(R.id.edt_title_frag_publish_secondhand)
    protected EditText mEdtTitle;
    //编辑描述
    @BindView(R.id.edt_description_frag_publish_secondhand)
    protected EditText mEdtDescription;

    //联系方式部分
    //编辑联系方式按钮
    @BindView(R.id.btn_addContact_frag_publish_secondhand)
    protected Button mBtnAddContact;

    //联系方式编辑框
    @BindView(R.id.edt_contact_frag_publish_secondhand)
    protected EditText mEdtContact;

    //联系方式弹出的下划线
    @BindView(R.id.bottomline_contact)
    protected TextView mEdtContactBottomline;


    //标签选择部分
    @BindView(R.id.tablelayout)
    protected TableLayout mTableLayout;
    @BindView(R.id.btn_addLabel_frag_publish_secondhand)
    protected Button mBtnSelectLabel;

    @BindView(R.id.bottomline_tablelayout)
    protected TextView mLabelBottomline;


    //图片展示部分,非必需项目
    //添加图片选择按钮
    @BindView(R.id.btn_add_image_frag_publish_secondhand)
    protected Button mBtnAddImage;
    //显示布局
    @BindView(R.id.linearlayout_addImage_frag_publish_secondhand)
    protected LinearLayout mLinearLayout;
    //选择图片的按钮
    @BindView(R.id.btn_selectFromimages_frag_publish_secondhand)
    protected Button mBtnSelectFrom;

    //图片展示
    @BindView(R.id.imageV_frag_publish_secondhand)
    protected ImageView mImageView;


    //CheackBox标签部分

    @BindView(R.id.cb11)
    protected CheckBox mCheckBox11;
    @BindView(R.id.cb12)
    protected CheckBox mCheckBox12;
    @BindView(R.id.cb13)
    protected CheckBox mCheckBox13;

    @BindView(R.id.cb21)
    protected CheckBox mCheckBox21;
    @BindView(R.id.cb22)
    protected CheckBox mCheckBox22;
    @BindView(R.id.cb23)
    protected CheckBox mCheckBox23;

    @BindView(R.id.cb31)
    protected CheckBox mCheckBox31;
    @BindView(R.id.cb32)
    protected CheckBox mCheckBox32;
    @BindView(R.id.cb33)
    protected CheckBox mCheckBox33;


    //图片字节数组
    private byte[] mImageViewBytes;


    //属性
    private String label;
    private String description;
    private String title;
    private String contact;

    private AVObject secondHandObject;

    private PresenterSecond mPresenterSecond;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_publish_secondhand_layout, container, false);
        ButterKnife.bind(this, view);
        mPresenterSecond = new PresenterSecondImpl(this);
        init();
        return view;
    }


    // TODO: 2017/4/24 响应关闭和完成事件 
    private void init() {
        //返回上一个Activity
        mToolbar.setNavigationIcon(R.drawable.publish_edit_close);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed(true);
            }
        });

        //完成编辑，点击按钮保存上传
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

    /**
     * 添加联系方式的点击事件
     */
    @OnClick(R.id.btn_addContact_frag_publish_secondhand)
    public void onBtnAddContactClick() {
        mEdtContact.setVisibility(View.VISIBLE);
        mEdtContactBottomline.setVisibility(View.VISIBLE);
    }

    /**
     * 添加标签点击事件
     */
    @OnClick(R.id.btn_addLabel_frag_publish_secondhand)
    public void onBtnSelectLabelClick() {
        mTableLayout.setVisibility(View.VISIBLE);
        mLabelBottomline.setVisibility(View.VISIBLE);
    }

    /**
     * 添加照片按钮点击事件
     */
    @OnClick(R.id.btn_add_image_frag_publish_secondhand)
    public void onBtnAddImage() {
        mLinearLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 从相册中选择照片事件
     */
    @OnClick(R.id.btn_selectFromimages_frag_publish_secondhand)
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


    /**
     * 检查是不是均有输入
     *
     * @return
     */
    private boolean checkOk() {
        //多个标签的使用，现在只是使用一个标签
        ArrayList<String> labelList = new ArrayList<>();
        if (mCheckBox11.isChecked()) {
            label = "0";
        } else if (mCheckBox12.isChecked()) {
            label = "1";
        } else if (mCheckBox13.isChecked()) {
            label = "2";
        } else if (mCheckBox21.isChecked()) {
            label = "3";
        } else if (mCheckBox22.isChecked()) {
            label = "4";
        } else if (mCheckBox23.isChecked()) {
            label = "5";
        } else if (mCheckBox31.isChecked()) {
            label = "6";
        } else if (mCheckBox32.isChecked()) {
            label = "7";
        } else if (mCheckBox33.isChecked()) {
            label = "8";
        }
        title = mEdtTitle.getText().toString().trim();
        description = mEdtDescription.getText().toString().trim();
        contact = mEdtContact.getText().toString().trim();
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description) || TextUtils.isEmpty(contact) || TextUtils.isEmpty(label)) {
            return false;
        }
        return true;
    }

    /**
     * 数据上传
     */
    private void saveInBackground() {
        //建表
        secondHandObject = new AVObject(Constants.BEAN_KEY_SECONDHAND_TABLE);
        secondHandObject.put(Constants.BEAN_KEY_SECONDHAND_OWNER, MyUser.getCurrentUser());
        secondHandObject.put(Constants.BEAN_KEY_SECONDHAND_TITLE, title);
        secondHandObject.put(Constants.BEAN_KEY_SECONDHAND_DESCRIPTION, description);
        secondHandObject.put(Constants.BEAN_KEY_SECONDHAND_CONTACT, contact);
        secondHandObject.put(Constants.BEAN_KEY_SECONDHAND_LABEL, label);
        secondHandObject.put(Constants.KEY_TYPE, Constants.BEAN_KEY_SECONDHAND_TABLE);

        //有图片的话上传图片
        if (mImageViewBytes != null) {
            AVFile mAvFile = new AVFile(Constants.BEAN_KEY_SECONDHAND_AVFILE, mImageViewBytes);
            secondHandObject.put(Constants.BEAN_KEY_SECONDHAND_IMAGE, mAvFile);
        }
        mPresenterSecond.saveItem(new OnSaveListener() {
            @Override
            public void onFail(AVException e) {
                if (e == null) {
                    getActivity().finish();
                }
            }
        });
    }

    @Override
    public AVObject saveData() {
        return secondHandObject;
    }
}
