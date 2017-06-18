package com.example.anif.module_activities;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anif.R;
import com.example.anif.beans.BeanActivities;
import com.example.anif.utils.Constants;
import com.example.anif.utils.UtilDate;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author XQF
 * @created 2017/6/18
 */
public class FragDetailActivity extends DialogFragment {
    public static final String KEY_BEANACTIVITY = "beanactivity";


    public static FragDetailActivity newInstance(BeanActivities beanActivities) {
        FragDetailActivity fragDetailActivity = new FragDetailActivity();
        Bundle args = new Bundle();
        args.putSerializable(KEY_BEANACTIVITY, beanActivities);
        fragDetailActivity.setArguments(args);
        return fragDetailActivity;
    }


    private BeanActivities mBeanActivities;

    @BindView(R.id.texV_title_frag_detail_activity)
    protected TextView mTitle;

    @BindView(R.id.texV_publishTime_frag_detail_activity)
    protected TextView mTime;

    @BindView(R.id.texV_description_frag_detail_activity)
    protected TextView mDescription;

    @BindView(R.id.texV_label_frag_detail_activity)
    protected TextView mLabel;

    @BindView(R.id.imageV_portrait_frag_detail_activity)
    protected ImageView mImagePortrait;

    @BindView(R.id.texV_nickname_frag_detail_activity)
    protected TextView mName;

    @BindView(R.id.btn_close_frag_detail_activity)
    protected Button mBtnClose;


    @BindView(R.id.imageV_detail_frag_detail_activity)
    protected ImageView mImageViewDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBeanActivities = (BeanActivities) getArguments().get(KEY_BEANACTIVITY);
        View view = inflater.inflate(R.layout.frag_detail_secondhand_layout, container, false);
        ButterKnife.bind(this, view);
        bindView(view);
        return view;
    }

    /**
     * 数据绑定显示
     *
     * @param view
     */
    private void bindView(View view) {
        mTitle.setText(mBeanActivities.getTitle());

        Date d = mBeanActivities.getPublishTime();
        String time = UtilDate.getPublishTime(d.toString());
        mTime.setText(time);
        mDescription.setText(mBeanActivities.getDescription());
        String label = mBeanActivities.getLabel();
        mLabel.setText(Constants.LABEL_ACTIVITY_MAP_IMAGE_TEXT.get(label));

        mImagePortrait.setImageResource(R.mipmap.ic_launcher);
        String detailImageUrl = mBeanActivities.getImageUrl();
        if (detailImageUrl != null) {
            mImageViewDetail.setVisibility(View.VISIBLE);
            Glide.with(getActivity()).load(detailImageUrl).into(mImageViewDetail);
        }
        mName.setText("熊清锋");
    }

    @OnClick(R.id.btn_close_frag_detail_group)
    public void onBtnCloseClick() {
        sendInfoToFragSecond(Activity.RESULT_OK, 0);
    }

    public void sendInfoToFragSecond(int resultCode, int info) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("close", info);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
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
