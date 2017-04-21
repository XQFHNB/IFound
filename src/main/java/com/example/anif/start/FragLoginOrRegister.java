package com.example.anif.start;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.anif.R;
import com.example.anif.base.FragBase;
import com.example.anif.main.AtyEditLogin;
import com.example.anif.main.AtyEditRegister;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author XQF
 * @created 2017/4/14
 */
public class FragLoginOrRegister extends FragBase {


    @BindView(R.id.btn_frag_login)
    protected Button mBtnFragLogin;
    @BindView(R.id.btn_frag_register)
    protected Button mBtnFragRegister;

    public static FragLoginOrRegister newInstance() {
        return new FragLoginOrRegister();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_login_or_register_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 启动登陆活动
     */
    @OnClick(R.id.btn_frag_login)
    public void onLoginClick() {
        AtyEditLogin.start(getActivity(), AtyEditLogin.class);
    }

    /**
     * 启动注册活动
     */
    @OnClick(R.id.btn_frag_register)
    public void onRegisterClick() {
        AtyEditRegister.start(getActivity(), AtyEditRegister.class);
    }
}
