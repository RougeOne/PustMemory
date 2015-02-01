package com.redgrue.pm.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.redgrue.pm.AppMnemoNet;
import com.redgrue.pm.R;
import com.redgrue.pm.event.ElementsAmountEvent;
import com.redgrue.pm.fragments.StartMemoryDrawerFragment;
import com.redgrue.pm.fragments.StartMemoryFragment;
import com.redgrue.pm.preferences.PrefsActivity;
import com.squareup.otto.Subscribe;

public class MainActivity extends ActionBarActivity {

    private static final String Log_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_AMOUNT_ELEMENTS = "EXTRA_AMOUNT_ELEMENTS";

    private StartMemoryDrawerFragment mStartMemoryDrawerFragment;
//    private DrawerLayout drawerLayout;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();

//        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mStartMemoryDrawerFragment = (StartMemoryDrawerFragment) fragmentManager.findFragmentById(R.id.drawerMainActivity);
//        mStartMemoryDrawerFragment.setUp(R.id.drawerMainActivity, drawerLayout);

        findViewById(R.id.MstartButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager
                        .beginTransaction()
                        .add(R.id.drawerMainActivity, new StartMemoryFragment())
                        .commit();
                findViewById(R.id.drawerMainActivity).setVisibility(View.VISIBLE);
//                drawerLayout.openDrawer(Gravity.END);
            }
        });
        Log.d(Log_TAG, "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppMnemoNet.getInstance().getBus().register(this);
    }

    protected void onStop() {
        super.onStop();
        AppMnemoNet.getInstance().getBus().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, R.id.actionCheckResult, 0, "Настройка");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        PrefsActivity.startActivity(this);
        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onEventBusListener(ElementsAmountEvent event) {
            Intent intent = new Intent(this, SetAnswersActivity.class);
            intent.putExtra(EXTRA_AMOUNT_ELEMENTS, event.getAmountElements());
            startActivity(intent);
    }
}
