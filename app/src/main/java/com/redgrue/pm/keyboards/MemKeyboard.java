package com.redgrue.pm.keyboards;

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;

/**
 * Created by rouge on 13.02.2015.
 */
public class MemKeyboard extends KeyboardView {

    public MemKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void showWithAnimation(Animation animation) {
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setVisibility(View.VISIBLE);
            }
        });
        setAnimation(animation);
    }
}
