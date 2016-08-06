package com.xiang.map.rollshowlistviewproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener {

    private ListView listView;
    private List<String> list;
    private ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        list = getList();
        adapter=new com.xiang.map.rollshowlistviewproject.ListAdapter(this,list);

        listView.setOnScrollListener(this);
        listView.setSelection(list.size());

        LayoutAnimationController lac=new LayoutAnimationController(AnimationUtils.loadAnimation(MainActivity.this,R.anim.setin));
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);//顺序随机
        listView.setLayoutAnimation(lac);
        listView.startLayoutAnimation();
        listView.setAdapter(adapter);
    }

    public List<String> getList(){
        List<String> list =  new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    /**
     * 设置滚动监听，当滚动到第二个时，跳到地list.size()+2个，滚动到倒数第二个时，跳到中间第二个，setSelection时，
     * 由于listView滚动并未停止，所以setSelection后会继续滚动，不会出现突然停止的现象
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

        /**到顶部添加数据关键<span style="font-size:14px;">代码</span>*/
        if (firstVisibleItem <= 2) {
            listView.setSelection(list.size() + 2);
        } else if (firstVisibleItem + visibleItemCount > adapter.getCount() - 2) {//到底部添加数据
            listView.setSelection(firstVisibleItem - list.size());
        }

    }

    }
