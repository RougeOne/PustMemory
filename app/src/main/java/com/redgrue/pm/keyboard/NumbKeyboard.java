package com.redgrue.pm.keyboard;

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;

/**
 * Created by rouge on 01.02.2015.
 */
public class NumbKeyboard extends KeyboardView {

    public NumbKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void closing() {
        super.closing();
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
