package com.xiang.map.rollshowlistviewproject;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/5.
 */
public class FristActivity extends Activity {
    private UPMarqueeView upview1;
    List<String> data = new ArrayList<>();
    List<View> views = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upview1);
        initParam();
        initdata();
        initView();
    }
    /**
     * 实例化控件
     */
    private void initParam() {
        upview1 = (UPMarqueeView) findViewById(R.id.upview1);
    }

    /**
     * 初始化界面程序
     */
    private void initView() {
        setView();
        upview1.setViews(views);
    }

    /**
     * 初始化需要循环的View
     * 为了灵活的使用滚动的View，所以把滚动的内容让用户自定义
     * 假如滚动的是三条或者一条，或者是其他，只需要把对应的布局，和这个方法稍微改改就可以了，
     */
    private void setView() {
        for (int i = 0; i < data.size(); i++) {
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.frist_item_view, null);
            //初始化布局的控件
            TextView tv1 = (TextView) moreView.findViewById(R.id.tv1);
           // TextView tv2 = (TextView) moreView.findViewById(R.id.tv2);
            //进行对控件赋值
            tv1.setText(data.get(i).toString());
           /* if (data.size() > i + 1) {
                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
                tv2.setText(data.get(i + 1).toString());
            }else {
               // moreView.findViewById(R.id.rl2).setVisibility(View.GONE);
            }*/

            //添加到循环滚动数组里面去
            views.add(moreView);
        }
    }

    /**
     * 初始化数据
     */
    private void initdata() {
        data = new ArrayList<>();
        data.add("电话号码1");
        data.add("电话号码2");
        data.add("电话号码3！");
        data.add("电话号码4！");
        data.add("电话号码5，");
    }



}
