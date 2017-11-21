package com.owenandroid.keepeye;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.owenandroid.keepeye.fragment.ArticleFragment;
import com.owenandroid.keepeye.fragment.ButlerFragment;
import com.owenandroid.keepeye.fragment.GirlFragment;
import com.owenandroid.keepeye.fragment.UserFragment;
import com.owenandroid.keepeye.ui.SettingActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //控件
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton mFabSetting;
    //数据源
    private List<String> titles;
    private List<Fragment> fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //去除actionbar的阴影
        getSupportActionBar().setElevation(0);
        initData();
        initView();
//        L.i(this,"haha");
//        CrashReport.testJavaCrash();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mFabSetting = (FloatingActionButton) findViewById(R.id.main_fab_setting);
        mFabSetting.setVisibility(View.GONE);
        mFabSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });
        mTabLayout = (TabLayout) findViewById(R.id.main_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        //为viewpager设置适配器
        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    mFabSetting.setVisibility(View.GONE);
                }else{
                    mFabSetting.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //将tablayout 与viewpager进行绑定
        mTabLayout.setupWithViewPager(mViewPager);

    }

    /**
     * 初始化数据
     */
    private void initData() {
        //填充tab的标题
        titles = new ArrayList<>();
        titles.add(getString(R.string.chat_robot));
        titles.add(getString(R.string.article));
        titles.add(getString(R.string.girlphoto));
        titles.add(getString(R.string.user));
        //填充viewpager的四个fragment
        fragmentList = new ArrayList<>();
        fragmentList.add(new ButlerFragment());
        fragmentList.add(new ArticleFragment());
        fragmentList.add(new GirlFragment());
        fragmentList.add(new UserFragment());
    }

    /**
     * ViewPager的适配器
     */
    class MyFragmentPagerAdapter extends FragmentPagerAdapter{

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
        //获取tab的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
