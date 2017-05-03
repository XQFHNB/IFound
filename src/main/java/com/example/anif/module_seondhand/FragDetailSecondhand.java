package com.example.anif.module_seondhand;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anif.R;
import com.example.anif.beans.BeanSecondHand;
import com.example.anif.utils.Constants;
import com.example.anif.utils.UtilDate;
import com.example.anif.utils.UtilLog;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author XQF
 * @created 2017/4/19
 */
public class FragDetailSecondhand extends DialogFragment {


    private BeanSecondHand mBeanSecondHand;

    @BindView(R.id.texV_title_frag_detail_secondhand)
    protected TextView mTitle;

    @BindView(R.id.texV_publishTime_frag_detail_secondhand)
    protected TextView mTime;

    @BindView(R.id.texV_description_frag_detail_secondhand)
    protected TextView mDescription;

    @BindView(R.id.texV_label_frag_detail_secondhand)
    protected TextView mLabel;

    @BindView(R.id.imageV_portrait_frag_detail_secondhand)
    protected ImageView mImagePortrait;

    @BindView(R.id.texV_nickname_frag_detail_secondhand)
    protected TextView mName;

    @BindView(R.id.btn_close_frag_detail_secondhand)
    protected Button mBtnClose;


    @BindView(R.id.imageV_detail_frag_detail_secondhand)
    protected ImageView mImageViewDetail;

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
        mBeanSecondHand = (BeanSecondHand) getArguments().getSerializable("secondhand");
        View view = inflater.inflate(R.layout.frag_detail_secondhand_layout, container, false);
        ButterKnife.bind(this, view);
        bindView(view);
        return view;
    }


    @OnClick(R.id.btn_close_frag_detail_secondhand)
    public void onBtnCloseClick() {
        sendInfoToFragSecond(Activity.RESULT_OK, 0);
    }

    /**
     * 向上一层的frag发出关闭信号
     *
     * @param resultCode
     * @param info
     */
    public void sendInfoToFragSecond(int resultCode, int info) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("close", info);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }


    /**
     * 数据绑定显示
     *
     * @param view
     */
    private void bindView(View view) {
        mTitle.setText(mBeanSecondHand.getTitle());

        Date d = mBeanSecondHand.getPublishTime();
        String time = UtilDate.getPublishTime(d.toString());
        mTime.setText(time);
        mDescription.setText(mBeanSecondHand.getGoodDescription());
        String label = mBeanSecondHand.getLabel();
        mLabel.setText(Constants.LABEL_SECONDHAND_MAP_IMAGE_TEXT.get(label));

        mImagePortrait.setImageResource(R.mipmap.ic_launcher);
        String detailImageUrl = mBeanSecondHand.getImageUrl();
        if (detailImageUrl != null) {
            mImageViewDetail.setVisibility(View.VISIBLE);
            Glide.with(getActivity()).load(detailImageUrl).into(mImageViewDetail);
        }
        mName.setText("熊清锋");
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
