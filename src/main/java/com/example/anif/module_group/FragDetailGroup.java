package com.example.anif.module_group;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.anif.R;
import com.example.anif.beans.BeanGroup;
import com.example.anif.utils.Constants;
import com.example.anif.utils.UtilDate;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author XQF
 * @created 2017/5/3
 */
public class FragDetailGroup extends DialogFragment {


    public static FragDetailGroup newInstance(BeanGroup beanGroup) {
        Bundle args = new Bundle();
        args.putSerializable("group", beanGroup);
        FragDetailGroup fragDetailGroup = new FragDetailGroup();
        fragDetailGroup.setArguments(args);
        return fragDetailGroup;
    }


    private BeanGroup mBeanGroup;

    @BindView(R.id.texV_title_frag_detail_group)
    protected TextView mTitle;

    @BindView(R.id.texV_publishTime_frag_detail_group)
    protected TextView mTime;

    @BindView(R.id.texV_description_frag_detail_group)
    protected TextView mDescription;

    @BindView(R.id.texV_label_frag_detail_group)
    protected TextView mLabel;

    @BindView(R.id.imageV_portrait_frag_detail_group)
    protected CircleImageView mImagePortrait;

    @BindView(R.id.texV_nickname_frag_detail_group)
    protected TextView mName;

    @BindView(R.id.btn_close_frag_detail_group)
    protected Button mBtnClose;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBeanGroup = (BeanGroup) getArguments().getSerializable("group");
        View view = inflater.inflate(R.layout.frag_detail_group_layout, container, false);
        ButterKnife.bind(this, view);
        bindView();
        return view;
    }

    private void bindView() {
        mTitle.setText(mBeanGroup.getTitle());
        Date d = mBeanGroup.getPublishTime();
        String time = UtilDate.getPublishTime(d.toString());
        mTime.setText(time);
        mDescription.setText(mBeanGroup.getDescription());
        String label = mBeanGroup.getLabel();
        mLabel.setText(Constants.LABEL_GROUP_MAP_IMAGE_TEXT.get(label));
        mImagePortrait.setImageResource(R.mipmap.ic_launcher);
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
