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
import com.example.anif.base.FragBase;
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
//            R.drawable.bee,
//            R.drawable.butterfly,
//            R.drawable.cat,
//            R.drawable.deer,
//            R.drawable.dolphin,
//            R.drawable.eagle,
//            R.drawable.horse,
//            R.drawable.jellyfish,
//            R.drawable.owl,
//            R.drawable.peacock,
//            R.drawable.pig,
//            R.drawable.rat,
//            R.drawable.snake,
//            R.drawable.squirrel
    };


    // TODO: 2017/4/19 toolbar的监听
    @BindView(R.id.toolBar_frag_main)
    protected Toolbar mToolbar;
    @BindView(R.id.drawer_layout_frag_main)
    protected DrawerLayout mDrawerLayout;

    @BindView(R.id.navigation_view_frag_main)
    protected NavigationView mNavigationView;

    @BindView(R.id.btn_label_classfy)
    protected BoomMenuButton mBoomMenuButton;

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

//        mBoomMenuButton = (BoomMenuButton) mToolbar.findViewById(R.id.btn_label_classfy);
        for (int i = 0; i < mBoomMenuButton.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            Toast.makeText(getActivity(), "Clicked " + index, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .normalImageRes(getImageResource())
                    .normalText(getext());
            mBoomMenuButton.addBuilder(builder);
        }

//        mToolbar.inflateMenu(R.menu.base_toobar_menu);
//        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                int menuItemId = item.getItemId();
//                if (menuItemId == R.id.action_search) {
//                    Toast.makeText(getActivity(), "dddzzz", Toast.LENGTH_SHORT).show();
//
//                }
//                return true;
//            }
//        });

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
//            mBoomMenuButton.performClick();
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
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
}
