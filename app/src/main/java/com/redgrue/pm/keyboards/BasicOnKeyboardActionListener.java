package com.redgrue.pm.keyboards;

import android.app.Activity;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;

/**
 * Created by rouge on 13.02.2015.
 */
public class BasicOnKeyboardActionListener implements KeyboardView.OnKeyboardActionListener {
    private static final String Log_TAG = BasicOnKeyboardActionListener.class.getSimpleName();
    private final KeyReturnAnswersCallBack mCallbacks;
    private Activity mTargetActivity;

    /**
     * @param targetActivity Activity a cui deve essere girato l'evento
     *                       "pressione di un tasto sulla tastiera"
     */
    public BasicOnKeyboardActionListener(Activity targetActivity) {
        mTargetActivity = targetActivity;
        try {
            mCallbacks = (KeyReturnAnswersCallBack) targetActivity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        if (primaryCode == 66) {
            mCallbacks.onKeyReturnAnswersCallBack();
        } else {
            long eventTime = System.currentTimeMillis();
            KeyEvent event = new KeyEvent(eventTime, eventTime,
                    KeyEvent.ACTION_DOWN, primaryCode, 0, 0, 0, 0,
                    KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE);
            mTargetActivity.dispatchKeyEvent(event);
        }
    }

    public static interface KeyReturnAnswersCallBack {
        void onKeyReturnAnswersCallBack();
    }

    @Override
    public void swipeUp() {
        // TODO Auto-generated method stub
    }

    @Override
    public void swipeRight() {
        // TODO Auto-generated method stub

    }

    @Override
    public void swipeLeft() {
        // TODO Auto-generated method stub

    }

    @Override
    public void swipeDown() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onText(CharSequence text) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onRelease(int primaryCode) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPress(int primaryCode) {
        // TODO Auto-generated method stub

    }

}