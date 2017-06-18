package com.example.anif.frags;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anif.R;
import com.example.anif.module_activities.widgets.FragActivities;
import com.example.anif.base.FragBase;
import com.example.anif.module_group.widgets.FragGroup;
import com.example.anif.module_sale.FragSale;
import com.example.anif.module_seondhand.widgets.FragSecondhand;
import com.example.anif.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author XQF
 * @created 2017/4/19
 */
public class FragMainContent extends FragBase {

    private AppCompatActivity mAty;

    @BindView(R.id.tablayout_frag_main_content)
    protected TabLayout mTabLayout;

    @BindView(R.id.viewpager_frag_main_content)
    protected ViewPager mViewPager;

    private FragmentManager mFragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_content_layout, container, false);
        mAty = (AppCompatActivity) getActivity();
        mFragmentManager = mAty.getSupportFragmentManager();
        ButterKnife.bind(this, view);
        init();

        return view;
    }

    private void init() {
        MyAdapter mAdapter = new MyAdapter(mFragmentManager);
        FragSecondhand fragSecondhand = new FragSecondhand();
        mAdapter.add(fragSecondhand, Constants.CONTENT_TITLES[0]);
        FragGroup fragGroup = new FragGroup();
        mAdapter.add(fragGroup, Constants.CONTENT_TITLES[1]);
        FragActivities fragActivities = new FragActivities();
        mAdapter.add(fragActivities, Constants.CONTENT_TITLES[2]);
        FragSale fragSale = new FragSale();
        mAdapter.add(fragSale, Constants.CONTENT_TITLES[3]);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(mAdapter);
    }

    class MyAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragmentList;
        private List<String> mTitleList;

        public MyAdapter(FragmentManager fm) {
            super(fm);
            mFragmentList = new ArrayList<>();
            mTitleList = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = mTitleList.get(position);
            return title;
        }

        public void add(Fragment frag, String title) {
            mFragmentList.add(frag);
            mTitleList.add(title);
        }
    }

}
