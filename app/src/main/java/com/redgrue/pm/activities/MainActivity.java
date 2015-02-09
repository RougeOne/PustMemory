package com.redgrue.pm.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.redgrue.pm.AppMnemoNet;
import com.redgrue.pm.R;
import com.redgrue.pm.event.LoginEvent;
import com.redgrue.pm.event.MainMenuEvent;
import com.redgrue.pm.event.MemoryTypeEvent;
import com.redgrue.pm.fragments.ChooseMemoryDrawerFragment;
import com.redgrue.pm.fragments.LoginFragment;
import com.redgrue.pm.fragments.MainNavigationFragment;
import com.redgrue.pm.preferences.PrefsActivity;
import com.redgrue.pm.service.QuickStartService;
import com.squareup.otto.Subscribe;

public class MainActivity extends Activity {

    private static final String Log_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_KEY_AMOUNT_ELEMENTS = "EXTRA_KEY_AMOUNT_ELEMENTS";
    public static final String EXTRA_KEY_MEMORY_TYPE = "EXTRA_KEY_MEMORY_TYPE";


    private FragmentManager fragmentManager;
    private boolean drawerOpen;

    //    private TextView loadLine;
    private DrawerLayout mDrawerLayout;
    private ChooseMemoryDrawerFragment mChooseMemoryDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();

        //Locking drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.mainActivityDrawerLayout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        // Getting fragment drawer with start memory layout(layout to choose and start memorizing exercise
        mChooseMemoryDrawerFragment = (ChooseMemoryDrawerFragment)
                fragmentManager.findFragmentById(R.id.drawerMainActivity);
        mChooseMemoryDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.mainActivityDrawerLayout));


        fragmentManager
                .beginTransaction()
                .add(R.id.containerMainActivity, new LoginFragment())
                .commit();
        Log.d(Log_TAG, "onCreate");
    }


    private void startForegroundService() {
        final Intent intent = new Intent(this, QuickStartService.class);
        intent.putExtra(QuickStartService.EXTRA_KEY_COMMAND,
                QuickStartService.COMMAND_MAKE_FOREGROUND);
        startService(intent);
    }

    private void stopForegroundService() {
        final Intent intent = new Intent(this, QuickStartService.class);
        stopService(intent);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_preferenses, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        PrefsActivity.startActivity(this);
        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onStartMemoryBusListener(MemoryTypeEvent event) {
        Intent intent = new Intent(this, StartMemoryActivity.class);
        intent.putExtra(EXTRA_KEY_AMOUNT_ELEMENTS, event.amountElements);
        intent.putExtra(EXTRA_KEY_MEMORY_TYPE, event.memoryType);
        startActivity(intent);
    }

    @Subscribe
    public void onMainMenuBusListener(MainMenuEvent event) {
        mDrawerLayout.openDrawer(Gravity.END);
    }

    @Subscribe
    public void onLogInBusListener(LoginEvent event) {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        fragmentManager.beginTransaction()
                .replace(R.id.containerMainActivity, new MainNavigationFragment())
                .commit();
    }
}
