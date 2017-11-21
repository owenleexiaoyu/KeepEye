package com.owenandroid.keepeye.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.owenandroid.keepeye.MainActivity;
import com.owenandroid.keepeye.R;
import com.owenandroid.keepeye.utils.UtilTools;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页
 * Created by Administrator on 2017/4/9.
 */

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private View view1,view2,view3;
    private List<View> viewList;
    private ImageView ivPoint1,ivPoint2,ivPoint3;//下方小圆点
    private Button btnSkip;//跳过按钮
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.guide_viewpager);
        ivPoint1 = (ImageView) findViewById(R.id.guide_img_point1);
        ivPoint2 = (ImageView) findViewById(R.id.guide_img_point2);
        ivPoint3 = (ImageView) findViewById(R.id.guide_img_point3);
        setPointImg(true,false,false);
        btnSkip = (Button) findViewById(R.id.guide_btn_skip);
        btnSkip.setOnClickListener(this);
        view1 = View.inflate(this,R.layout.guideitem_one,null);
        view2 = View.inflate(this,R.layout.guideitem_two,null);
        view3 = View.inflate(this,R.layout.guideitem_three,null);
        TextView welword1 = (TextView) view1.findViewById(R.id.guide_item1_welword);
        TextView welword2 = (TextView) view2.findViewById(R.id.guide_item2_welword);
        TextView welword3 = (TextView) view3.findViewById(R.id.guide_item3_welword);
        UtilTools.setFontToText(this,welword1);
        UtilTools.setFontToText(this,welword2);
        UtilTools.setFontToText(this,welword3);
        view3.findViewById(R.id.guide_item3_btn_into).setOnClickListener(this);

        //填充数据
        viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        //为Viewpager设置适配器
        mViewPager.setAdapter(new MyPagerAdapter());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        setPointImg(true,false,false);
                        btnSkip.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        setPointImg(false,true,false);
                        btnSkip.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        setPointImg(false,false,true);
                        btnSkip.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void setPointImg(boolean one, boolean two, boolean three){
        if (one){
            ivPoint1.setImageResource(R.mipmap.point_on);
        }else {
            ivPoint1.setImageResource(R.mipmap.point_off);
        }
        if (two){
            ivPoint2.setImageResource(R.mipmap.point_on);
        }else{
            ivPoint2.setImageResource(R.mipmap.point_off);
        }
        if (three){
            ivPoint3.setImageResource(R.mipmap.point_on);
        }else {
            ivPoint3.setImageResource(R.mipmap.point_off);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.guide_btn_skip:
            case R.id.guide_item3_btn_into:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }

    //ViewPager的适配器
    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }
    }
}
