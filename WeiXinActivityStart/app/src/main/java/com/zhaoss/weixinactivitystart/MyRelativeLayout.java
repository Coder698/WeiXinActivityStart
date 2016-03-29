package com.zhaoss.weixinactivitystart;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Zhaoss on 2016/3/29.
 */
public class MyRelativeLayout extends RelativeLayout {

    private OnMoveListener listener;

    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    float downX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float slide = moveX - downX;
                if(slide != 0){
                    if(listener!=null) listener.onRightListener(slide);
                }
                downX = moveX;
                break;
            case MotionEvent.ACTION_UP:
                if(listener!=null) listener.onMoveOverListener();
                break;
        }
        return true;
    }

    public interface OnMoveListener{
        /** 向右滑动 */
        void onRightListener(float slide);
        /** 滑动结束 */
        void onMoveOverListener();
    }

    public void setOnMoveListener(OnMoveListener listener){
        this.listener = listener;
    }
}
