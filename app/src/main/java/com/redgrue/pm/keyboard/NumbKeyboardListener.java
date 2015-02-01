package com.redgrue.pm.keyboard;

import android.app.Activity;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;

/**
 * Created by rouge on 01.02.2015.
 */
public class NumbKeyboardListener implements KeyboardView.OnKeyboardActionListener {

    private Activity mTargetActivity;

    /**
     * @param targetActivity Activity a cui deve essere girato l'evento
     *                       "pressione di un tasto sulla tastiera"
     */
    public NumbKeyboardListener(Activity targetActivity) {
        mTargetActivity = targetActivity;
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        long eventTime = System.currentTimeMillis();
        KeyEvent event = new KeyEvent(eventTime, eventTime,
                KeyEvent.ACTION_DOWN, primaryCode, 0, 0, 0, 0,
                KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE);

        mTargetActivity.dispatchKeyEvent(event);
    }


    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
