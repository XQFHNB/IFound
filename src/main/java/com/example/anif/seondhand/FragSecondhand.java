package com.example.anif.seondhand;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anif.R;
import com.example.anif.base.FragBase;
import com.example.anif.beans.BeanSecondHand;
import com.example.anif.seondhand.presenter.PresenterSecond;
import com.example.anif.seondhand.presenter.PresenterSecondImpl;
import com.example.anif.seondhand.view.ViewSecond;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author XQF
 * @created 2017/4/19
 */
public class FragSecondhand extends FragBase implements ViewSecond {


    @BindView(R.id.recyclerView_frag_content_common)
    protected RecyclerView mRecyclerView;

    @BindView(R.id.floatingButton_frag_content_common)
    protected FloatingActionButton mFloatingActionButton;

    private PresenterSecond mPresenterSecond;


    private AdapterSecondhand mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_content_common_layout, container, false);
        ButterKnife.bind(this, view);
        mPresenterSecond = new PresenterSecondImpl(this);
        mPresenterSecond.loadItemList();
        mAdapter = new AdapterSecondhand();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void addItems(List<BeanSecondHand> mList) {
        mAdapter.add(mList);
    }

// TODO: 2017/4/19  转到消息发布界面

    /**
     * 转到消息发布界面
     */
    @OnClick(R.id.floatingButton_frag_content_common)
    public void onFloatingButtonClick() {

    }


    /**
     * ViewHolder
     */
    class HolderSecondhand extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.image_item_frag_secondhand)
        protected ImageView mImageView;
        @BindView(R.id.title_item_frag_secondhand)
        protected TextView mTextTitle;
        @BindView(R.id.description_item_frag_secondhand)
        protected TextView mTextDescriptionShort;


        private BeanSecondHand mBeanSecondHand;

        public HolderSecondhand(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(BeanSecondHand bean) {
            mBeanSecondHand = bean;
            String title = bean.getTitle();
            String description = bean.getGoodDescription();
            String imageUrl = bean.getImageUrl();
            mTextTitle.setText(title);
            mTextDescriptionShort.setText(description);
            Glide.with(getActivity()).load(imageUrl).into(mImageView);
        }


        /**
         * @param view ViewHolder的点击事件弹出详情对话框
         */
        @Override
        public void onClick(View view) {
            FragDetailSecondhand fragDetailSecondhand = FragDetailSecondhand.newInstance(mBeanSecondHand);
            fragDetailSecondhand.show(getFragmentManager(), "dd");
        }
    }


    /**
     * Adapter
     */
    class AdapterSecondhand extends RecyclerView.Adapter<HolderSecondhand> {
        private List<BeanSecondHand> mBeanSecondHands;


        public AdapterSecondhand() {
            mBeanSecondHands = new ArrayList<>();
        }

        @Override
        public HolderSecondhand onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_frag_secondhand_layout, parent, false);
            return new HolderSecondhand(view);
        }

        @Override
        public void onBindViewHolder(HolderSecondhand holder, int position) {
            BeanSecondHand beanSecondHand = mBeanSecondHands.get(position);
            holder.bind(beanSecondHand);
        }

        @Override
        public int getItemCount() {
            return mBeanSecondHands.size();
        }

        public void add(List<BeanSecondHand> mList) {
            if (mBeanSecondHands.size() == 0) {
                mBeanSecondHands = mList;
            } else {
                mBeanSecondHands.clear();
                mBeanSecondHands.addAll(mList);
            }
        }
    }
}
