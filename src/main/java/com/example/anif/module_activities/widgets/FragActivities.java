package com.example.anif.module_activities.widgets;

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
import com.example.anif.beans.BeanActivities;
import com.example.anif.module_activities.FragDetailActivity;
import com.example.anif.module_activities.presenter.IPresenterActivities;
import com.example.anif.module_activities.presenter.PresenterActivitiesImpl;
import com.example.anif.module_activities.view.IViewActivities;
import com.example.anif.utils.Constants;
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
public class FragActivities extends FragBase implements IViewActivities {
    /**
     * 标签选择部分
     */
    private static int index = 0;

    static String getext() {
        if (index >= text.length) index = 0;
        return text[index++];
    }

    private static String[] text = new String[]{
            "社团", "俱乐部", "比赛", "院校", "其他"

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
    private List<BeanActivities> mData = new ArrayList<>();
    private IPresenterActivities mIPresenterActivities;
    private AdapterActivities mAdapterActivities;
    private FragDetailActivity mFragDetailActivity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_content_common_layout, container, false);
        ButterKnife.bind(this, view);
        mIPresenterActivities = new PresenterActivitiesImpl(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapterActivities = new AdapterActivities();
        mRecyclerView.setAdapter(mAdapterActivities);
        init();
        return view;
    }

    private void init() {
        mapLabel = new HashMap<>();

        for (int i = 0; i < imageResources.length; i++) {
            mapLabel.put(i + "", imageResources[i]);
        }


        mBoomMenuButton.setPiecePlaceEnum(PiecePlaceEnum.DOT_5_1);
        mBoomMenuButton.setButtonPlaceEnum(ButtonPlaceEnum.SC_5_1);
        for (int i = 0; i < mBoomMenuButton.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            Toast.makeText(getActivity(), "Clicked " + text[index], Toast.LENGTH_SHORT).show();
                            String label = new String(index + "");
                            mData.clear();
                            mIPresenterActivities.loadItemlist(label);

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
                    mIPresenterActivities.loadItemlist();
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }



    @Override
    public void onResume() {
        super.onResume();
        mData.clear();
        mIPresenterActivities.loadItemlist();
    }


    @OnClick(R.id.floatingButton_frag_content_common)
    public void onFloatingButtonClick() {
        AtyPublish.start(getActivity(), AtyPublish.class, Constants.FRAG_PUBLISH_ACTIVITY);

    }

    @Override
    public void addItems(List<BeanActivities> list) {
        if (mData == null || mData.size() == 0) {
            mData = list;
            mAdapterActivities.addItems(list);
            mAdapterActivities.notifyDataSetChanged();
        }
    }


    class HolderActivity extends RecyclerView.ViewHolder {


        @BindView(R.id.image_item_frag_activity)
        protected ImageView mImageView;
        @BindView(R.id.title_item_frag_activity)
        protected TextView mTextTitle;
        @BindView(R.id.description_item_frag_activity)
        protected TextView mTextDescriptionShort;
        @BindView(R.id.cardView)
        protected CardView mCardView;

        protected BeanActivities mBeanActivities;

        public HolderActivity(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(BeanActivities beanActivities) {
            mBeanActivities = beanActivities;
            String title = beanActivities.getTitle();
            String description = beanActivities.getDescription();
            String label = beanActivities.getLabel();
            int indexLabel = Integer.parseInt(label);
            mTextTitle.setText("[" + text[indexLabel] + "] " + title);
            mTextDescriptionShort.setText(description);
            int labelResId = mapLabel.get(label);
            mImageView.setImageResource(labelResId);

        }

        @OnClick(R.id.cardView)
        public void onItemCardViewClick() {
            mFragDetailActivity = FragDetailActivity.newInstance(mBeanActivities);
            mFragDetailActivity.setTargetFragment(FragActivities.this, 1);
            mFragDetailActivity.show(getFragmentManager(), "dd");
        }
    }

    class AdapterActivities extends RecyclerView.Adapter<HolderActivity> {

        private List<BeanActivities> mList;

        public AdapterActivities() {
            mList = new ArrayList<>();
        }

        @Override
        public HolderActivity onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.item_frag_activities_layout, parent, false);
            return new HolderActivity(itemView);
        }

        @Override
        public void onBindViewHolder(HolderActivity holder, int position) {
            BeanActivities beanActivities = mList.get(position);
            holder.bind(beanActivities);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public void addItems(List<BeanActivities> list) {
            if (mList.size() == 0) {
                mList = list;
            } else {
                mList.clear();
                mList.addAll(list);
            }
        }
    }
}
