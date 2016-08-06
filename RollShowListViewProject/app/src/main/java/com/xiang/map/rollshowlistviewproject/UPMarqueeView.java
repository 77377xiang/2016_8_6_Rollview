package com.xiang.map.rollshowlistviewproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import java.util.List;

/**
 * Created by Administrator on 2016/8/5.
 */
public class UPMarqueeView extends ViewFlipper {

    private Context mContext;
    private boolean isSetAnimDuration = false;
    private int interval = 2000;
    //动画的时间
    private int animDuration = 500;

    public UPMarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
       init(context, attrs, 0);
    }
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        setFlipInterval(interval);
       /* Animation animIn = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_in);
        if (isSetAnimDuration) animIn.setDuration(animDuration);
        setInAnimation(animIn);*/

        Animation animOut = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_out);
        if (isSetAnimDuration) animOut.setDuration(animDuration);
        animOut.setFillEnabled(true);
        setOutAnimation(animOut);

        /*AnimationSet set=new AnimationSet(true);
        set.setFillAfter(true);
        set.addAnimation(animOut);

        set.startNow();    */                     //启动动画


    }
//    public void initSTOP() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                Animation animOut = AnimationUtils.loadAnimation(mContext,R.anim .anim_marquee_in);
//                if (isSetAnimDuration) animOut.setDuration(animDuration);
//                setOutAnimation(animOut);
//
//
//            }
//        }, 1000);
//    }

//
    public void setViews(List<View> views) {
        if (views == null || views.size() == 0) {
            return;
        }
        removeAllViews();
        for (int i = 0; i < views.size(); i++) {
            addView(views.get(i));
        }
        startFlipping();
    }



}
