package com.example.anif.module_seondhand.widgets;

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
import com.example.anif.base.FragBase;
import com.example.anif.beans.BeanSecondHand;
import com.example.anif.module_seondhand.FragDetailSecondhand;
import com.example.anif.atys.AtyPublish;
import com.example.anif.module_seondhand.presenter.PresenterSecond;
import com.example.anif.module_seondhand.presenter.PresenterSecondImpl;
import com.example.anif.module_seondhand.view.ViewSecond;
import com.example.anif.utils.Constants;
import com.example.anif.utils.UtilLog;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.ArrayList;
import java.util.Arrays;
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
public class FragSecondhand extends FragBase implements ViewSecond {

    /**
     * 标签部分
     */
    private static int index = 0;

    static String getext() {
        if (index >= text.length) index = 0;
        return text[index++];

    }

    private static String[] text = new String[]{
            "护肤美颜", "书籍资料", "生活家电",
            "衣物箱包", "优惠卡券", "电子设备",
            "配件外设", "运动器材", "其他"

    };
    private static int imageResourceIndex = 0;

    static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    private static int[] imageResources = new int[]{
            R.drawable.label_second_skin,
            R.drawable.label_second_books,
            R.drawable.label_second_life_electric,
            R.drawable.label_second_clothing,
            R.drawable.label_second_discount,
            R.drawable.label_second_equbment_electric,
            R.drawable.label_second_parts,
            R.drawable.label_second_sports,
            R.drawable.label_second_other
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

    private List<BeanSecondHand> mData = new ArrayList<>();

    private PresenterSecond mPresenterSecond;
    private FragDetailSecondhand fragDetailSecondhand;

    private AdapterSecondhand mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_content_common_layout, container, false);
        ButterKnife.bind(this, view);

        /**
         * MVP层级交接,创建Fragment的时候
         */
        mPresenterSecond = new PresenterSecondImpl(this);

        /**
         * Adapter适配部分
         */
        mAdapter = new AdapterSecondhand();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);


        /**
         * 筛选标签
         */
        mapLabel = new HashMap<>();
        mapLabel.put("11", R.drawable.label_second_books);
        mapLabel.put("12", R.drawable.label_second_clothing);
        mapLabel.put("13", R.drawable.label_second_discount);
        mapLabel.put("21", R.drawable.label_second_equbment_electric);
        mapLabel.put("22", R.drawable.label_second_life_electric);
        mapLabel.put("23", R.drawable.label_second_other);
        mapLabel.put("31", R.drawable.label_second_parts);
        mapLabel.put("32", R.drawable.label_second_skin);
        mapLabel.put("33", R.drawable.label_second_sports);

        for (int i = 0; i < mBoomMenuButton.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            Toast.makeText(getActivity(), "Clicked " + text[index], Toast.LENGTH_SHORT).show();
                            String label = new String();
                            if (index == 0) {
                                label = "11";
                            } else if (index == 1) {
                                label = "12";
                            } else if (index == 2) {
                                label = "13";
                            } else if (index == 3) {
                                label = "21";
                            } else if (index == 4) {
                                label = "22";
                            } else if (index == 5) {
                                label = "23";
                            } else if (index == 6) {
                                label = "31";
                            } else if (index == 7) {
                                label = "32";
                            } else if (index == 8) {
                                label = "33";
                            }
                            mData.clear();
                            mPresenterSecond.loadItemList(label);
                        }
                    })
                    .normalImageRes(getImageResource())
                    .normalColor(getResources().getColor(R.color.label_bg_color))
                    .normalText(getext());
            mBoomMenuButton.addBuilder(builder);
        }


        /**
         * 刷新时，清空数据容器重新加载数据
         */
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mData != null) {
                    mData.clear();
                    mPresenterSecond.loadItemList();
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mData.clear();
        mPresenterSecond.loadItemList();
    }

    @Override
    public void addItems(List<BeanSecondHand> mList) {
        UtilLog.d("test", "测试一下4 " + Arrays.toString(mList.toArray()));
        if (mData == null || mData.size() == 0) {
            mData = mList;
            mAdapter.add(mList);
            mAdapter.notifyDataSetChanged();
            UtilLog.d("test", "测试一下5 " + Arrays.toString(mList.toArray()));
        }
    }

// TODO: 2017/4/19  转到消息发布界面

    /**
     * 转到消息发布界面
     */
    @OnClick(R.id.floatingButton_frag_content_common)
    public void onFloatingButtonClick() {
        AtyPublish.start(getActivity(), AtyPublish.class, Constants.FRAG_PUBLISH_SECONDHAND);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == 1) {
            int info = data.getIntExtra("close", -1);
            if (info == 0) {
                fragDetailSecondhand.dismiss();
            }
        }
    }

    /**
     * ViewHolder
     */
    class HolderSecondhand extends RecyclerView.ViewHolder {
        @BindView(R.id.image_item_frag_secondhand)
        protected ImageView mImageView;
        @BindView(R.id.title_item_frag_secondhand)
        protected TextView mTextTitle;
        @BindView(R.id.description_item_frag_secondhand)
        protected TextView mTextDescriptionShort;

        @BindView(R.id.cardView)
        protected CardView mCardView;

        private BeanSecondHand mBeanSecondHand;

        public HolderSecondhand(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        /**
         * 视图绑定
         *
         * @param bean
         */
        public void bind(BeanSecondHand bean) {
            mBeanSecondHand = bean;
            String title = bean.getTitle();
            String description = bean.getGoodDescription();

            mTextTitle.setText(title);
            mTextDescriptionShort.setText(description);

            String label = bean.getLabel();
            int labelResId = mapLabel.get(label);
            mImageView.setImageResource(labelResId);
        }


        /**
         * ViewHolder的点击事件弹出详情对话框
         *
         * @param
         */
        @OnClick(R.id.cardView)
        public void onItemCardViewClick() {
            fragDetailSecondhand = FragDetailSecondhand.newInstance(mBeanSecondHand);
            fragDetailSecondhand.setTargetFragment(FragSecondhand.this, 1);
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
