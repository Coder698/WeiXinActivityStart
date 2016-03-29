package com.zhaoss.weixinactivitystart;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * Created by Zhaoss on 2016/3/29.
 */
public abstract class BaseActivity extends Activity {

    public View view;
    public static Bitmap bitmap;
    private int wmWidth;
    private MyRelativeLayout myRelativeLayout;
    private float initMove = 100;
    private int animaDate = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = initView();

        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        wmWidth = wm.getDefaultDisplay().getWidth();

        if(bitmap != null){//如果bitmap不是null,说明该activity是startActivity开启的
            RelativeLayout relativeLayout = new RelativeLayout(this);
            relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            myRelativeLayout = createRelative();
            relativeLayout.addView(myRelativeLayout);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            relativeLayout.addView(view);
            bitmap = null;
            setContentView(relativeLayout);
        }else{
            setContentView(view);
        }
        initData();
    }

    /**
     * new一个MyRelativeLayout出来,并且初始化
     */
    protected MyRelativeLayout createRelative() {

        final MyRelativeLayout myRelativeLayout = new MyRelativeLayout(this);
        myRelativeLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        myRelativeLayout.setOnMoveListener(new MyRelativeLayout.OnMoveListener() {
            @Override
            public void onRightListener(float slide) {
                //挪动第一层界面
                if (view.getX() + slide > 0) {
                    ObjectAnimator.ofFloat(view, "translationX", view.getX(), view.getX() + slide).setDuration(0).start();
                }
                //挪动第二层界面
                if (myRelativeLayout.getX() <= 0) {
                    float f = (float) (initMove/wmWidth*1.3);
                    float slideMove = myRelativeLayout.getX() + slide * f;
                    if (slideMove > 0) slideMove = 0;
                    ObjectAnimator.ofFloat(myRelativeLayout, "translationX", myRelativeLayout.getX(), slideMove).setDuration(0).start();
                }
            }
            @Override
            public void onMoveOverListener() {
                if (view.getX() >= wmWidth / 2) {//挪动距离一半以上,关闭activity
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationX", view.getX(), wmWidth);
                    objectAnimator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            BaseActivity.super.finish();
                        }
                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }
                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                    objectAnimator.setDuration(animaDate).start();
                } else {//恢复View布局回到原处
                    ObjectAnimator.ofFloat(view, "translationX", view.getX(), 0).setDuration(animaDate).start();
                    ObjectAnimator.ofFloat(myRelativeLayout, "translationX", myRelativeLayout.getX(), -initMove).setDuration(animaDate).start();
                }
            }
        });
        myRelativeLayout.setBackgroundDrawable(new BitmapDrawable(bitmap));
        ObjectAnimator.ofFloat(myRelativeLayout, "translationX", 0, -initMove).setDuration(0).start();
        return myRelativeLayout;
    }

    /**
     * 关闭界面时执行动画
     */
    @Override
    public void finish() {

        if (myRelativeLayout != null){
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationX", 0, wmWidth);
            ObjectAnimator.ofFloat(myRelativeLayout, "translationX", myRelativeLayout.getX(), 0).setDuration(animaDate).start();
            objectAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }
                @Override
                public void onAnimationEnd(Animator animation) {
                    BaseActivity.super.finish();
                }
                @Override
                public void onAnimationCancel(Animator animation) {

                }
                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            objectAnimator.setDuration(animaDate).start();
        }else{
            super.finish();
        }
    }

    /**
     * 在开启activity的时候, 把自己界面的视图view保存起来
     */
    public void startActivity(Intent intent, BaseActivity activity) {

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565 );
        Canvas canvas = new Canvas(bitmap);
        activity.view.draw(canvas);
        BaseActivity.bitmap = bitmap;
        startActivity(intent);
    }

    protected abstract void initData();

    public abstract View initView();
}
