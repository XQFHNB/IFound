package com.example.anif.module_management.widgets;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.example.anif.R;
import com.example.anif.base.FragBase;
import com.example.anif.beans.BeanCommon;
import com.example.anif.beans.BeanGroup;
import com.example.anif.beans.BeanSecondHand;
import com.example.anif.beans.MyUser;
import com.example.anif.module_group.FragDetailGroup;
import com.example.anif.module_management.model.ModelManagement;
import com.example.anif.module_management.model.ModelManagementImpl;
import com.example.anif.module_management.model.OnDeleteListener;
import com.example.anif.module_management.presenter.PresenterManagement;
import com.example.anif.module_management.presenter.PresenterManagementImpl;
import com.example.anif.module_management.view.ViewManagement;
import com.example.anif.module_seondhand.FragDetailSecondhand;
import com.example.anif.utils.Constants;
import com.example.anif.utils.UtilLog;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.nikhilpanju.recyclerviewenhanced.OnActivityTouchListener;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author XQF
 * @created 2017/5/1
 */
public class FragMyManagement extends FragBase implements ViewManagement, RecyclerTouchListener.RecyclerTouchListenerHelper {


    /**
     * 标签部分
     */
    private static int index = 0;

    static String getext() {
        if (index >= text.length) index = 0;
        return text[index++];

    }

    private static String[] text = new String[]{
            "二手", "拼组"

    };
    private static int imageResourceIndex = 0;

    static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    private static int[] imageResources = new int[]{
            R.drawable.bat,
            R.drawable.bear,

    };


    public static FragMyManagement newInstance() {
        return new FragMyManagement();
    }


    @BindView(R.id.recyclerV_frag_my_management)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.toolBar_frag_main)
    protected Toolbar mToolbar;


    protected PresenterManagement mPresenterManagement;
    protected MyAdapter mMyAdapter;

    protected List<BeanCommon> mList = new ArrayList<>();
    protected List<AVObject> mAvList = new ArrayList<>();


    private RecyclerTouchListener mOnTouchListener;
    private OnActivityTouchListener touchListener;

    @BindView(R.id.btn_label_classfy1)
    protected BoomMenuButton mBoomMenuButton;


    private FragDetailSecondhand mFragDetailSecondhand;
    private FragDetailGroup mFragDetailGroup;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_my_management_layout, container, false);
        ButterKnife.bind(this, view);
        mPresenterManagement = new PresenterManagementImpl(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMyAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mMyAdapter);

        mToolbar.setTitle("我的管理");
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        mOnTouchListener = new RecyclerTouchListener(getActivity(), mRecyclerView);


        mBoomMenuButton.setPiecePlaceEnum(PiecePlaceEnum.DOT_2_1);
        mBoomMenuButton.setButtonPlaceEnum(ButtonPlaceEnum.SC_2_1);
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

        mOnTouchListener
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        toast(position + " clicked", getActivity());

                        itemClick(mList.get(position));
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {

                    }
                })
                .setSwipeOptionViews(R.id.add, R.id.delete)
                .setSwipeable(R.id.FG_item_frag_my_management, R.id.BG_item_frag_my_management, new RecyclerTouchListener.OnSwipeOptionsClickListener() {

                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                        String message = "";
                        if (viewID == R.id.add) {

                            // TODO: 2017/5/6 添加修改逻辑
                            modifyItem();
                            message += "Add";
                        } else if (viewID == R.id.delete) {
                            // TODO: 2017/5/6 添加删除逻辑
                            message += "delete";

                            AVObject avObject = mAvList.get(position);
                            deleteItem(avObject, position);

                        }
                        message += " clicked for row " + (position + 1);
                        toast(message, getActivity());
                    }
                });


        return view;
    }

    private void itemClick(BeanCommon beanCommon) {
        if (beanCommon.getCommonType() == Constants.BEAN_KEY_SECONDHAND_TABLE) {
            BeanSecondHand beanSecondhand = (BeanSecondHand) beanCommon;
            mFragDetailSecondhand = FragDetailSecondhand.newInstance(beanSecondhand);
            mFragDetailSecondhand.setTargetFragment(FragMyManagement.this, Constants.FRAG_PUBLISH_SECONDHAND);
            mFragDetailSecondhand.show(getFragmentManager(), "dd");
        } else if (beanCommon.getCommonType() == Constants.BEAN_KEY_GROUP_TABLE) {
            BeanGroup beanGroup = (BeanGroup) beanCommon;
            mFragDetailGroup = FragDetailGroup.newInstance(beanGroup);
            mFragDetailGroup.setTargetFragment(FragMyManagement.this, Constants.FRAG_PUBLISH_GROUP);
            mFragDetailGroup.show(getFragmentManager(), "dd");
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        int info = data.getIntExtra("close", -1);
        if (info == 0) {
            if (requestCode == Constants.FRAG_PUBLISH_SECONDHAND) {
                mFragDetailSecondhand.dismiss();
            } else if (requestCode == Constants.FRAG_PUBLISH_GROUP) {
                mFragDetailGroup.dismiss();
            }
        }
    }


    /**
     * 修改该Item对应的object，跳转到publishfrag
     */
    private void modifyItem() {


    }

    /**
     * 删除该item对应的逻辑,弹出对话框然后删除
     */
    private void deleteItem(final AVObject avObject, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("删除");
        builder.setMessage("确认删除？");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mPresenterManagement.deleteItem(avObject, new OnDeleteListener() {
                    @Override
                    public void onSucess(AVException e) {
                        if (e == null) {
                            mList.remove(position);
                            mMyAdapter.notifyDataSetChanged();
                            toast("删除成功", getActivity());
                        }
                    }
                });
            }
        });
        builder.create().show();

    }


    @Override
    public void onResume() {
        super.onResume();
        mList.clear();
        mPresenterManagement.loadData();
        mRecyclerView.addOnItemTouchListener(mOnTouchListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        mRecyclerView.removeOnItemTouchListener(mOnTouchListener);
    }

    @Override
    public void addItem(List<BeanCommon> list, List<AVObject> avList) {

        UtilLog.d("testmanage", Arrays.toString(list.toArray()));
        if (mList.size() == 0) {
            mList = list;
            mMyAdapter.add(mList);
            mMyAdapter.notifyDataSetChanged();
        }
        if (mAvList.size() == 0) {
            mAvList = avList;
        }
    }

    @Override
    public void setOnActivityTouchListener(OnActivityTouchListener listener) {
        this.touchListener = listener;
    }


    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_item_frag_my_management)
        protected ImageView mImageView;

        @BindView(R.id.title_item_frag_my_management)
        protected TextView mTextVTitle;

        @BindView(R.id.description_item_frag_my_management)
        protected TextView mTextVDescription;


        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bind(BeanCommon bean) {
            String type = bean.getCommonType();
            String title = bean.getCommonTitle();
            String description = bean.getCommonDescription();
            UtilLog.d("test1", "为什么没有执行绑定  " + type + " " + title + " " + description);
            mTextVTitle.setText(title);
            mTextVDescription.setText(description);
            int resId = Constants.MAP_TYPE_PROJECT.get(type);
            mImageView.setImageResource(resId);
        }


    }

    class MyAdapter extends RecyclerView.Adapter<MyHolder> {
        List<BeanCommon> myList;

        public MyAdapter() {
            myList = new ArrayList<>();
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup container, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_frag_my_management_layout, container, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            BeanCommon bean = myList.get(position);
            holder.bind(bean);
        }

        @Override
        public int getItemCount() {
            return myList.size();
        }

        public void add(List<BeanCommon> list) {
            UtilLog.d("test1", "那里的list" + Arrays.toString(list.toArray()));
            if (myList.size() == 0) {
                myList = list;
            } else {
                myList.clear();
                myList.addAll(list);
            }

        }

    }
}
