package com.example.anif.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.LogInCallback;
import com.example.anif.R;
import com.example.anif.base.FragBase;
import com.example.anif.beans.MyUser;
import com.example.anif.utils.UtilCheck;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author XQF
 * @created 2017/4/17
 */
public class FragEditLogin extends FragBase {

    @BindView(R.id.editText_phone_frag_edit_login)
    protected EditText mEdtPhone;

    @BindView(R.id.editText_pwd_frag_edit_login)
    protected EditText mEdtPwd;

    @BindView(R.id.btn_login_frag_edit_login)
    protected Button mBtnLogin;

    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_edit_login_layout, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();
        return view;
    }

    @OnClick(R.id.btn_login_frag_edit_login)
    public void onLoginClick() {
        String mPhone = mEdtPhone.getText().toString().trim();
        String mPwd = mEdtPwd.getText().toString().trim();
        boolean inputOk = inputCheck(mPhone, mPwd);
        if (!inputOk) {
            // TODO: 2017/4/17 弹出重新输入对话框或者提示
            return;
        } else {
            switchToMainActivity(mPhone, mPwd);
        }
    }


    /**
     * 检查手机和密码是否输入和是否输入正确
     *
     * @param phone
     * @param pwd
     * @return
     */
    private boolean inputCheck(String phone, String pwd) {
        boolean result = true;
        if (phone == null || phone.length() == 0 || pwd == null || pwd.length() == 0) {
            result = false;
        }
        if (!UtilCheck.isChinaPhoneLegal(phone)) {
            result = false;
        }
        return result;
    }

    /**
     * 转到主活动
     */
    private void switchToMainActivity(String phone, String pwd) {
        final ProgressDialog dialog = showSpinnerDialog();
        MyUser.logInInBackground(phone, pwd, new LogInCallback<MyUser>() {
            @Override
            public void done(MyUser avUser, AVException e) {
                dialog.dismiss();
                if (e == null) {
                    AtyMain.start(getActivity(), AtyMain.class);
                }
            }
        }, MyUser.class);
    }
}
