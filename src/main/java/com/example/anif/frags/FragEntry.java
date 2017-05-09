package com.example.anif.frags;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anif.R;
import com.example.anif.base.FragBase;
import com.example.anif.beans.MyUser;
import com.example.anif.atys.AtyMain;
import com.example.anif.utils.Constants;
import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;

/**
 * @author XQF
 * @created 2017/4/13
 */
public class FragEntry extends FragBase {
    public static FragEntry newInstance() {
        return new FragEntry();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_entry_layout, container, false);

        PaperOnboardingFragment fragment = getPaper();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.guide_container, fragment);
        fragmentTransaction.commit();
        fragment.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                // TODO: 2017/4/17 判断当前的用户状态从而决定跳转界面 
                switchFrag();
            }
        });
        return view;
    }

    private PaperOnboardingFragment getPaper() {
        String[][] strs = Constants.ENTRY_TITLESANDSLOGANANDCOLORS;
        int[] drawables = Constants.ENTRY_BACKGROUND;
        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            PaperOnboardingPage scr = new PaperOnboardingPage(strs[i][0], strs[i][1],
                    Color.parseColor(strs[i][2]), drawables[i], R.drawable.arrow);
            elements.add(scr);
        }
        PaperOnboardingFragment onBoardingFragment = PaperOnboardingFragment.newInstance(elements);
        return onBoardingFragment;
    }

    /**
     * 转场方法
     */
    private void switchFrag() {
        MyUser mUser = MyUser.getCurrentUser();
        if (mUser == null) {
            switchToLoginFrag();
        } else {
            switchToMainActivity();
        }
//        getActivity().finish();
    }

    /**
     * 转到登陆界面
     */
    private void switchToLoginFrag() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.guide_container, FragLoginOrRegister.newInstance()).commit();
    }

    /**
     * 转到主活动
     */
    private void switchToMainActivity() {
        AtyMain.start(getActivity(), AtyMain.class);
    }
}
