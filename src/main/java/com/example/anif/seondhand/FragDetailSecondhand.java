package com.example.anif.seondhand;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anif.R;
import com.example.anif.beans.BeanSecondHand;

import butterknife.ButterKnife;

/**
 * @author XQF
 * @created 2017/4/19
 */
public class FragDetailSecondhand extends DialogFragment {


    private BeanSecondHand mBeanSecondHand;

    /**
     * 传递数据
     *
     * @param beanSecondHand 序列化后的bean
     * @return
     */
    public static FragDetailSecondhand newInstance(BeanSecondHand beanSecondHand) {
        Bundle args = new Bundle();
        args.putSerializable("secondhand", beanSecondHand);
        FragDetailSecondhand fragDetailSecondhand = new FragDetailSecondhand();
        fragDetailSecondhand.setArguments(args);
        return fragDetailSecondhand;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mBeanSecondHand = (BeanSecondHand) savedInstanceState.getSerializable("secondhand");
        }
        View view = inflater.inflate(R.layout.frag_detail_second_hand, container, false);
        bindView(view);
        ButterKnife.bind(this, view);
        return view;
    }


    /**
     * 数据绑定显示
     *
     * @param view
     */
    private void bindView(View view) {

    }

    /**
     * 全屏对话框
     */

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

}
