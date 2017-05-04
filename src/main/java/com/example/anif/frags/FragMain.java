package com.example.anif.frags;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.anif.R;
import com.example.anif.atys.AtyMyManagement;
import com.example.anif.base.FragBase;
import com.example.anif.module_management.widgets.FragMyManagement;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

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

    @BindView(R.id.navigation_view_frag_main)
    protected NavigationView mNavigationView;

    private ActionBarDrawerToggle mDrawerToggle;

    private AppCompatActivity mAty;

    private FragmentManager mFragManager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_layout, container, false);
        ButterKnife.bind(this, view);
        mAty = (AppCompatActivity) getActivity();
        mAty.setSupportActionBar(mToolbar);
        mFragManager = mAty.getSupportFragmentManager();
        init();
        showMainView();
        return view;
    }


    private void init() {
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerContent(mNavigationView);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.base_toobar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_classfy) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 显示内容部分
     */
    private void showMainView() {
        FragMainContent fragContent = new FragMainContent();
        mFragManager.beginTransaction().add(R.id.frame_content_frag_main, fragContent).commit();
    }

    /**
     * 设置抽屉部分
     *
     * @param navigationView
     */
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        int itemId = menuItem.getItemId();
                        switch (itemId) {
                            case R.id.navigation_item_updateprofie:
                                switchToUpdateProfile();
                                break;
                            case R.id.navigation_item_management:
                                switchToMyManageMent();
                                break;
                            case R.id.navigation_item_about:
                                switchToAbout();
                                break;
                        }
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void switchToMyManageMent() {
        AtyMyManagement.start(getActivity(), AtyMyManagement.class);
    }


    private void switchToUpdateProfile() {

    }

    private void switchToAbout() {

    }
}
