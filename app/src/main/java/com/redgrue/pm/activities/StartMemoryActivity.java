package com.redgrue.pm.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;

import com.redgrue.pm.AppMnemoNet;
import com.redgrue.pm.R;
import com.redgrue.pm.event.ShowDistractExerciseEvent;
import com.redgrue.pm.event.ShowCorrectAnswersEvent;
import com.redgrue.pm.event.ShowStatisticsEvent;
import com.redgrue.pm.fragments.StatisticsDrawerFragment;
import com.redgrue.pm.fragments.UsersAnswersFragment;
import com.redgrue.pm.fragments.DistractExerciseFragment;
import com.redgrue.pm.fragments.ShowCorrectNumbFragment;
import com.redgrue.pm.container.MemoryAnswersContainer;
import com.squareup.otto.Subscribe;

/**
 * Created by rouge on 29.01.2015.
 */
public class StartMemoryActivity extends ActionBarActivity {

    private static final String Log_TAG = StartMemoryActivity.class.getSimpleName();
    private short answersCounter;
    private MemoryAnswersContainer memoryAnswersContainer;
    private FragmentManager fragmentManager;

    private StatisticsDrawerFragment mStatisticsDrawerFragment;
    private CharSequence mTitle;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_memory);
        fragmentManager = getFragmentManager();

        mStatisticsDrawerFragment = (StatisticsDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mDrawerLayout = (DrawerLayout) findViewById(R.id.statisticsDrawerLayout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        mStatisticsDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.statisticsDrawerLayout));
        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey(MainActivity.EXTRA_KEY_AMOUNT_ELEMENTS)) {
                short amountOfElements = getIntent().getShortExtra(MainActivity.EXTRA_KEY_AMOUNT_ELEMENTS, (short) 0);
                String typeMemory = getIntent().getStringExtra(MainActivity.EXTRA_KEY_MEMORY_TYPE);
                memoryAnswersContainer = new MemoryAnswersContainer(amountOfElements, typeMemory);
            }
        }

        fragmentManager
                .beginTransaction()
                .replace(R.id.containerStartMemoryActivity, new ShowCorrectNumbFragment(memoryAnswersContainer))
                .commit();
        Log.i(Log_TAG, "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppMnemoNet.getInstance().getBus().register(this);
        Log.i(Log_TAG, "onResume");
    }

    protected void onStop() {
        super.onStop();
        AppMnemoNet.getInstance().getBus().unregister(this);
        Log.i(Log_TAG, "onStop");
    }

    @Subscribe
    public void onShowCorrectAnswersEventListener(ShowCorrectAnswersEvent event) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.containerStartMemoryActivity, new DistractExerciseFragment())
                .commit();
        Log.i(Log_TAG, "onShowCorrectAnswersEventListener implemented");
    }

    @Subscribe
     public void onDistractExerciseEventListener(ShowDistractExerciseEvent event) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.containerStartMemoryActivity, new UsersAnswersFragment(memoryAnswersContainer))
                .commit();
        Log.i(Log_TAG, "onDistractExerciseEventListener implemented");
    }

    @Subscribe
    public void onSetUserAnswerEventListener(ShowStatisticsEvent event) {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        mDrawerLayout.openDrawer(Gravity.END);
        mStatisticsDrawerFragment.fillUserStatisticsFields(memoryAnswersContainer);
        Log.i(Log_TAG, "onDistractExerciseEventListener implemented");
    }

}