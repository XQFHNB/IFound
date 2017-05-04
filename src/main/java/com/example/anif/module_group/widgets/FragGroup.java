package com.example.anif.module_group.widgets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anif.R;
import com.example.anif.atys.AtyPublish;
import com.example.anif.base.FragBase;
import com.example.anif.beans.BeanGroup;
import com.example.anif.module_group.FragDetailGroup;
import com.example.anif.module_group.presenter.PresenterGroup;
import com.example.anif.module_group.presenter.PresenterGroupImpl;
import com.example.anif.module_group.view.ViewGroups;
import com.example.anif.utils.Constants;
import com.example.anif.utils.UtilLog;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author XQF
 * @created 2017/4/19
 */
public class FragGroup extends FragBase implements ViewGroups {
    /**
     * 标签选择部分
     */
    private static int index = 0;

    static String getext() {
        if (index >= text.length) index = 0;
        return text[index++];

    }

    private static String[] text = new String[]{
            "拼车", "竞赛", "凑单", "旅游", "其他"

    };

    private static int imageResourceIndex = 0;

    static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    private static int[] imageResources = new int[]{
            R.drawable.label_group_carpool,
            R.drawable.label_group_games,
            R.drawable.label_group_grouppurchase,
            R.drawable.label_group_tour,
            R.drawable.label_group_other
    };


    @BindView(R.id.recyclerView_frag_content_common)
    protected RecyclerView mRecyclerView;

    @BindView(R.id.floatingButton_frag_content_common)
    protected FloatingActionButton mFloatingActionButton;


    @BindView(R.id.btn_label_classfy1)
    protected BoomMenuButton mBoomMenuButton;

    @BindView(R.id.swipeRefreshLayout_frag_content_common)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    private Map<String, Integer> mapLabel;
    private List<BeanGroup> mData = new ArrayList<>();
    private PresenterGroup mPresenterGroup;
    private FragDetailGroup mFragDetailGroup;

    private AdapterGroup mAdapterGroup;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_content_common_layout, container, false);
        ButterKnife.bind(this, view);

        mPresenterGroup = new PresenterGroupImpl(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapterGroup = new AdapterGroup();
        mRecyclerView.setAdapter(mAdapterGroup);

        init();
        return view;
    }

    private void init() {
        mapLabel = new HashMap<>();

        for (int i = 0; i < imageResources.length; i++) {
            mapLabel.put(i + "", imageResources[i]);
        }


        mBoomMenuButton.setPiecePlaceEnum(PiecePlaceEnum.DOT_9_1);
        mBoomMenuButton.setButtonPlaceEnum(ButtonPlaceEnum.SC_9_1);
        for (int i = 0; i < mBoomMenuButton.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            Toast.makeText(getActivity(), "Clicked " + text[index], Toast.LENGTH_SHORT).show();
                        }
                    })
                    .normalImageRes(getImageResource())
                    .normalColor(getResources().getColor(R.color.label_bg_color))
                    .normalText(getext());
            mBoomMenuButton.addBuilder(builder);
        }

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mData != null) {
                    mData.clear();
                    mPresenterGroup.loadItemlist();
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    @OnClick(R.id.floatingButton_frag_content_common)
    public void onFloatingButtonClick() {
        AtyPublish.start(getActivity(), AtyPublish.class, Constants.FRAG_PUBLISH_GROUP);
    }


    @Override
    public void onResume() {
        super.onResume();
        mData.clear();
        mPresenterGroup.loadItemlist();
    }

    @Override
    public void addItems(List<BeanGroup> list) {
        if (mData == null || mData.size() == 0) {
            mData = list;
            mAdapterGroup.addItems(list);
            mAdapterGroup.notifyDataSetChanged();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == 1) {
            int info = data.getIntExtra("close", -1);
            if (info == 0) {
                mFragDetailGroup.dismiss();
            }
        }
    }

    class HolderGroup extends RecyclerView.ViewHolder {

        @BindView(R.id.image_item_frag_group)
        protected ImageView mImageView;
        @BindView(R.id.title_item_frag_group)
        protected TextView mTextTitle;
        @BindView(R.id.description_item_frag_group)
        protected TextView mTextDescriptionShort;
        @BindView(R.id.cardView)
        protected CardView mCardView;

        private BeanGroup mBeanGroup;

        public HolderGroup(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(BeanGroup beanGroup) {
            mBeanGroup = beanGroup;
            String title = mBeanGroup.getTitle();
            String description = mBeanGroup.getDescription();
            mTextTitle.setText(title);
            mTextDescriptionShort.setText(description);
            String label = mBeanGroup.getLabel();
            int labelResId = mapLabel.get(label);
            mImageView.setImageResource(labelResId);
        }

        @OnClick(R.id.cardView)
        public void onItemCardViewClick() {
            mFragDetailGroup = FragDetailGroup.newInstance(mBeanGroup);
            mFragDetailGroup.setTargetFragment(FragGroup.this, 1);
            mFragDetailGroup.show(getFragmentManager(), "dd");
        }
    }

    class AdapterGroup extends RecyclerView.Adapter<HolderGroup> {

        private List<BeanGroup> mList;

        public AdapterGroup() {
            mList = new ArrayList<>();
        }

        @Override
        public HolderGroup onCreateViewHolder(ViewGroup container, int viewType) {

            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_frag_group_layout, container, false);
            return new HolderGroup(view);
        }

        @Override
        public void onBindViewHolder(HolderGroup holder, int position) {
            BeanGroup beanGroup = mList.get(position);
            holder.bind(beanGroup);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public void addItems(List<BeanGroup> list) {
            if (mList.size() == 0) {
                mList = list;
            } else {
                mList.clear();
                mList.addAll(list);
            }
        }
    }
}
