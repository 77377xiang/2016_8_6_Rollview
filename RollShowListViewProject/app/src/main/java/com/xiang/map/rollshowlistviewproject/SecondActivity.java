package com.xiang.map.rollshowlistviewproject;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;

import java.util.logging.Logger;

public class SecondActivity extends Activity {
    Button sendBtn, stopBtn;
    TextView textView1, textView2;
    int i = 0;
    int a;
    String number[] = {"123", "456", "789", "147", "258", "369"};

    private boolean isSetAnimDuration = false;
    //动画的时间
    private int animDuration = 500;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            a = number.length;
            if (a<1){
                textView2.setVisibility(View.GONE);
            }else {

                AnimationNoe();
                if (i<a){
                    textView2.setText(number[i]);
                    i++;
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView2 = (TextView) findViewById(R.id.text2);
        sendBtn = (Button) findViewById(R.id.sendBtn);
        stopBtn = (Button) findViewById(R.id.stopBtn);
        setSend();
       // stop();

    }

    //接受消息
    @Subscribe
    public void  onEventMainThread(MessageEvent event) {
        String msg = "onEventMainThread收到了消息：" + event.getMsg();
        Log.d("harvic", msg);
        textView2.setText(msg);
        AnimationNoe();
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    // 消息开始无限循环
    private void setSend() {
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注册：三个参数分别是，消息订阅者（接收者），接收方法名，事件类
                // EventBus.getDefault().register(this,"onEventMainThread",MessageEvent.class);
                //EventBus.getDefault().register(this);
               //EventBus.getDefault().post(new MessageEvent("这是EventBus发送的消息"));

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(0);

                    }
                }, 0, 4000);
            }
        });

    }


   /* private void stop() {
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 EventBus.getDefault().unregister(this);//取消订阅
            }
        });

    }*/

  /* @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消订阅
    }*/

    //动画效果
    private void AnimationNoe() {
        // ObjectAnimator.ofFloat(imageView,"translationX",0,200f).setDuration(1000).start();//x轴平移
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(textView2, "translationY", 0, -100f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(textView2, "translationY", 0 - 100, -180f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(textView2, "alpha", 1, 0);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(textView2, "alpha", 1, 1);
        animator2.setStartDelay(2000);

        AnimatorSet set = new AnimatorSet();
        set.playSequentially(animator1, animator2);//连续运行
        set.play(animator2).with(animator3);//同时进行
        set.play(animator4).with(animator1);//同时进行
        // set.play(animator1).after(animator3);animator1在animator3后运行  即after后的先运行，play后边的后运行
        set.setDuration(1000);//动作时间
        set.start();
    }


}

