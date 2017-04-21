package com.example.anif.main;

import android.app.ProgressDialog;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author XQF
 * @created 2017/4/17
 */
public class FragEditRegister extends FragBase {


    @BindView(R.id.editText_phone_frag_edit_register)
    protected EditText mEdtPhone;

    @BindView(R.id.editText_pwd_frag_edit_register)
    protected EditText mEdtPwd;

    @BindView(R.id.editText_again_pwd_frag_edit_register)
    protected EditText mEditPwdAgain;
    @BindView(R.id.btn_register_frag_edit_register)
    protected Button mBtnLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_edit_register_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_register_frag_edit_register)
    public void onRegisterClick() {
        String mPhone = mEdtPhone.getText().toString().trim();
        String mPwd = mEdtPwd.getText().toString().trim();
        String mPwdAgain = mEditPwdAgain.getText().toString().trim();
        boolean inputOk = inputCheck(mPhone, mPwd, mPwdAgain);
        // TODO: 2017/4/17 验证手机号
        if (inputOk) {
            switchToMainActivity(mPhone, mPwd);
        } else {
            return;
        }
    }


    /**
     * 检查是不是输入正确
     *
     * @param phone
     * @param pwd
     * @param pwdAgain
     * @return
     */
    private boolean inputCheck(String phone, String pwd, String pwdAgain) {
        boolean result = true;
        if (phone == null || pwd == null || pwdAgain == null || phone.length() == 0 || pwd.length() == 0 || pwdAgain.length() == 0) {
            result = false;
            // TODO: 2017/4/17 输入不能为空的提示
            return result;
        }
        if (!pwd.equals(pwdAgain)) {
            result = false;
            // TODO: 2017/4/17 两次密码输入不一致的提示
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
