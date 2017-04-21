package com.example.anif.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anif.R;
import com.example.anif.base.FragBase;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author XQF
 * @created 2017/4/17
 */
public class FragMain extends FragBase {

    // TODO: 2017/4/19 toolbar的监听 
    @BindView(R.id.toolBar_frag_main)
    protected Toolbar mToolbar;
    @BindView(R.id.drawer_layout_frag_main)
    protected DrawerLayout mDrawerLayout;

    private AppCompatActivity mAty;

    private FragmentManager mFragManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_layout, container, false);
        ButterKnife.bind(this, view);
        mAty = (AppCompatActivity) getActivity();
        mAty.setSupportActionBar(mToolbar);
        mFragManager = mAty.getSupportFragmentManager();
        showMainView();
        return view;
    }

    private void showMainView() {
        FragMainContent fragContent = new FragMainContent();
        mFragManager.beginTransaction().add(R.id.frame_content_frag_main, fragContent).commit();
    }
}
