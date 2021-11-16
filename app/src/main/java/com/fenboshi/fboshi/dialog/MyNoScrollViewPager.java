/**
 * @(#)DialogCommentListView.java Apr 26, 2013
 *
 * Copyright (c) 2010 by vcread.com. All rights reserved.
 * 
 */
package com.fenboshi.fboshi.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;


/**
 * 
 * @since July 4, 2013
 * @author kzhang
 */
public class MyNoScrollViewPager extends ViewPager {

    // 是否禁止 viewpager 左右滑动
    private boolean noScroll = false;

    public void setNoScroll(boolean noScroll){
        this.noScroll = noScroll;
    }

    public MyNoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (noScroll){
            return false;
        }else{
            return super.onTouchEvent(arg0);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (noScroll){
            return false;
        }else{
            return super.onInterceptTouchEvent(arg0);
        }
    }


}
