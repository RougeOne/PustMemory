package com.redgrue.pm.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.redgrue.pm.AppMnemoNet;
import com.redgrue.pm.R;
import com.redgrue.pm.event.ElementTypeEvent;
import com.redgrue.pm.fragments.ChooseMemoryDrawerFragment;
import com.redgrue.pm.service.QuickStartService;
import com.squareup.otto.Subscribe;

public class MainActivity extends ActionBarActivity {

    private static final String Log_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_KEY_AMOUNT_ELEMENTS = "EXTRA_KEY_AMOUNT_ELEMENTS";
    public static final String EXTRA_KEY_MEMORY_TYPE= "EXTRA_KEY_MEMORY_TYPE";


    private FragmentManager fragmentManager;
    private boolean drawerOpen;

    private TextView loadLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadLine = (TextView) findViewById(R.id.loadLine);
        fragmentManager = getFragmentManager();

//        LayoutInflater inflater = LayoutInflater.from(this);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.galaxyContainer);
        RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(28, 28);
        relativeParams.setMargins(140, 140, 0, 0);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.circle_dark_blue);
        relativeLayout.addView(imageView, relativeParams);
        // Create drawer with start memory layout(layout to choose and start memorizing exercise
        fragmentManager
                .beginTransaction()
                .add(R.id.drawerMainActivity, new ChooseMemoryDrawerFragment())
                .commit();

        findViewById(R.id.MstartButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!drawerOpen) {
                    loadLine.setVisibility(View.VISIBLE);
                    findViewById(R.id.drawerMainActivity).setVisibility(View.VISIBLE);
                    drawerOpen = true;
                } else {
                    loadLine.setVisibility(View.INVISIBLE);
                    findViewById(R.id.drawerMainActivity).setVisibility(View.GONE);
                    drawerOpen = false;
                }
            }
        });

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

    @Subscribe
    public void onEventBusListener(ElementTypeEvent event) {
        Intent intent = new Intent(this, StartMemoryActivity.class);
        intent.putExtra(EXTRA_KEY_AMOUNT_ELEMENTS, event.getAmountElements());
        intent.putExtra(EXTRA_KEY_MEMORY_TYPE, event.getMemoryType());
        startActivity(intent);
    }
}
