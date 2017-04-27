package com.kisen.loadanimtemplate;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * 加载动画
 * Created by huang on 2017/1/17.
 */
public class LoadingProgress  extends FrameLayout{

    private AnimationDrawable mAnimDrawable;

    public LoadingProgress(Context context) {
        this(context, null);
    }

    public LoadingProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mAnimDrawable = (AnimationDrawable) ContextCompat.getDrawable(context, R.drawable.anim_progressbar);

        ImageView progress = new ImageView(context, attrs);
        progress.setImageResource(R.mipmap.loading_1);
        progress.setBackgroundDrawable(mAnimDrawable);
        progress.setContentDescription(null);
        addView(progress, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        setClickable(true);
        dismiss();
    }

    public void show() {
        mAnimDrawable.start();
        setVisibility(VISIBLE);
    }

    public void dismiss() {
        mAnimDrawable.stop();
        setVisibility(GONE);
    }
}

