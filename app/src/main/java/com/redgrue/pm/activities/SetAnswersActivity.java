package com.redgrue.pm.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.redgrue.pm.AppMnemoNet;
import com.redgrue.pm.R;
import com.redgrue.pm.event.ShowDistractExerciseEvent;
import com.redgrue.pm.event.ShowCorrectAnswersEvent;
import com.redgrue.pm.fragments.UsersAnswersFragment;
import com.redgrue.pm.fragments.DistractExerciseFragment;
import com.redgrue.pm.fragments.CorrectNumbFragment;
import com.redgrue.pm.objects.MemoryAnswersContainer;
import com.squareup.otto.Subscribe;

/**
 * Created by rouge on 29.01.2015.
 */
public class SetAnswersActivity extends ActionBarActivity {

    private static final String Log_TAG = SetAnswersActivity.class.getSimpleName();
    private short answersCounter;
    private MemoryAnswersContainer memoryAnswersContainer;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);

        fragmentManager = getFragmentManager();
        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey(MainActivity.EXTRA_AMOUNT_ELEMENTS))
                memoryAnswersContainer = new MemoryAnswersContainer(getIntent().getShortExtra(MainActivity.EXTRA_AMOUNT_ELEMENTS, (short) 0));
        }

        fragmentManager
                .beginTransaction()
                .replace(R.id.containerAnswersActivity, new CorrectNumbFragment(memoryAnswersContainer.getCorrectAnswersArray()))
                .commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        AppMnemoNet.getInstance().getBus().register(this);
        Log.d(Log_TAG, "onResume");
    }

    protected void onStop() {
        super.onStop();
        AppMnemoNet.getInstance().getBus().unregister(this);
        Log.d(Log_TAG, "onStop");
    }

    @Subscribe
    public void onShowCorrectAnswersEventListener(ShowCorrectAnswersEvent event) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.containerAnswersActivity, new DistractExerciseFragment())
                .commit();

        Log.i(Log_TAG, "onShowCorrectAnswersEventListener implemented");

    }

    @Subscribe
    public void onDistractExerciseEventListener(ShowDistractExerciseEvent event) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.containerAnswersActivity, new UsersAnswersFragment())
                .commit();

        Log.i(Log_TAG, "onDistractExerciseEventListener implemented");
    }
}