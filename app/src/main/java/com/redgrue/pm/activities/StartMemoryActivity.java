package com.redgrue.pm.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.redgrue.pm.AppMnemoNet;
import com.redgrue.pm.R;
import com.redgrue.pm.event.ShowDistractExerciseEvent;
import com.redgrue.pm.event.ShowCorrectAnswersEvent;
import com.redgrue.pm.fragments.StatisticsDrawerFragment;
import com.redgrue.pm.fragments.UsersAnswersFragment;
import com.redgrue.pm.fragments.DistractExerciseFragment;
import com.redgrue.pm.fragments.CorrectNumbFragment;
import com.redgrue.pm.container.MemoryAnswersContainer;
import com.squareup.otto.Subscribe;

/**
 * Created by rouge on 29.01.2015.
 */
public class StartMemoryActivity extends ActionBarActivity implements StatisticsDrawerFragment.NavigationDrawerCallbacks {

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
        mStatisticsDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.statisticsDrawerLayout));
        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey(MainActivity.EXTRA_AMOUNT_ELEMENTS))
                memoryAnswersContainer = new MemoryAnswersContainer(getIntent().getShortExtra(MainActivity.EXTRA_AMOUNT_ELEMENTS, (short) 0));
        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.statisticsDrawerLayout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        fragmentManager
                .beginTransaction()
                .replace(R.id.containerStartMemoryActivity, new CorrectNumbFragment(memoryAnswersContainer.getCorrectAnswersArray()))
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

        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        fragmentManager
                .beginTransaction()
                .replace(R.id.containerStartMemoryActivity, new UsersAnswersFragment(memoryAnswersContainer))
                .commit();
        Log.i(Log_TAG, "onDistractExerciseEventListener implemented");


    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
         Log.d(Log_TAG, "Hello Drawer");
    }
}