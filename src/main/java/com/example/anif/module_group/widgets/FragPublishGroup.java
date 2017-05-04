package com.example.anif.module_group.widgets;

import android.os.Bundle;
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
import android.widget.TableLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.example.anif.R;
import com.example.anif.base.FragBase;
import com.example.anif.beans.MyUser;
import com.example.anif.module_group.model.OnSaveListener;
import com.example.anif.module_group.presenter.PresenterGroup;
import com.example.anif.module_group.presenter.PresenterGroupImpl;
import com.example.anif.module_group.view.ViewPublishGroups;
import com.example.anif.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author XQF
 * @created 2017/5/3
 */
public class FragPublishGroup extends FragBase implements ViewPublishGroups {


    //toolbar
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    //编辑标题
    @BindView(R.id.edt_title_frag_publish_group)
    protected EditText mEdtTitle;
    //编辑描述
    @BindView(R.id.edt_description_frag_publish_group)
    protected EditText mEdtDescription;


    //联系方式部分
    //编辑联系方式按钮
    @BindView(R.id.btn_addContact_frag_publish_group)
    protected Button mBtnAddContact;

    //联系方式编辑框
    @BindView(R.id.edt_contact_frag_publish_group)
    protected EditText mEdtContact;

    //联系方式弹出的下划线
    @BindView(R.id.bottomline_contact)
    protected TextView mEdtContactBottomline;


    //标签选择部分
    @BindView(R.id.tablelayout)
    protected TableLayout mTableLayout;
    @BindView(R.id.btn_addLabel_frag_publish_group)
    protected Button mBtnSelectLabel;

    @BindView(R.id.bottomline_tablelayout)
    protected TextView mLabelBottomline;


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
    private String title;
    private String contact;

    private AVObject groupObject;

    private PresenterGroup mPresentGruop;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_publish_group_layout, container, false);
        ButterKnife.bind(this, view);
        mPresentGruop = new PresenterGroupImpl(this);
        init();
        return view;

    }

    private void init() {
        mToolbar.setNavigationIcon(R.drawable.publish_edit_close);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
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

    @OnClick(R.id.btn_addContact_frag_publish_group)
    public void onBtnAddContactClick() {
        mEdtContact.setVisibility(View.VISIBLE);
        mEdtContactBottomline.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btn_addLabel_frag_publish_group)
    public void onBtnSelectLabelClick() {
        mTableLayout.setVisibility(View.VISIBLE);
        mLabelBottomline.setVisibility(View.VISIBLE);
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
        title = mEdtTitle.getText().toString().trim();
        description = mEdtDescription.getText().toString().trim();
        contact = mEdtContact.getText().toString().trim();
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description) || TextUtils.isEmpty(contact) || TextUtils.isEmpty(label)) {
            return false;
        }
        return true;
    }

    private void saveInBackground() {

        groupObject = new AVObject(Constants.BEAN_KEY_GROUP_TABLE);
        groupObject.put(Constants.BEAN_KEY_GROUP_OWNER, MyUser.getCurrentUser());
        groupObject.put(Constants.BEAN_KEY_GROUP_TITLE, title);
        groupObject.put(Constants.BEAN_KEY_GROUP_DESCRIPTION, description);
        groupObject.put(Constants.BEAN_KEY_GROUP_CONTACT, contact);
        groupObject.put(Constants.BEAN_KEY_GROUP_LABEL, label);
        groupObject.put(Constants.KEY_TYPE, Constants.BEAN_KEY_GROUP_TABLE);
        mPresentGruop.saveData(new OnSaveListener() {
            @Override
            public void onSucess(AVException e) {
                if (e == null) {
                    getActivity().finish();
                }
            }
        });

    }

    @Override
    public AVObject saveData() {
        return groupObject;
    }
}
