package com.redgrue.pm.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.redgrue.pm.AppMnemoNet;
import com.redgrue.pm.R;
import com.redgrue.pm.event.ElementsAmountEvent;

/**
 * Created by rouge on 18.01.2015.
 */
public class StartMemoryDrawerFragment extends Fragment {
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
    private static final String Log_TAG = StartMemoryDrawerFragment.class.getSimpleName();

    private ActionBarDrawerToggle mDrawerToggle;
    private int defaultElemenets;
    private View mFragmentContainerView;
    private DrawerLayout mDrawerLayout;
    private int mCurrentSelectedPosition;

    public StartMemoryDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        defaultElemenets = 20;
        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
        }
        Log.d(Log_TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_start_memory, container, false);
        final EditText elementEditText = (EditText) view.findViewById(R.id.setElementsView);
        view.findViewById(R.id.MstartButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!elementEditText.getText().toString().isEmpty()) {
                    Short elementsAmount = Short.parseShort(elementEditText.getText().toString());
                    AppMnemoNet.getInstance().getBus().post(new ElementsAmountEvent(elementsAmount));
                    mDrawerLayout.closeDrawers();
                } else {
                    Toast.makeText(getActivity(), "Укажите число элементов", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Log.d(Log_TAG, "onCreateView");
        return view;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the navigation drawer and the action bar app icon.
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),	/* host Activity */
                mDrawerLayout,                    					/* DrawerLayout object */
                R.string.navigation_drawer_open,  					/* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  					/* "close drawer" description for accessibility */) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }
                getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }
                getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };

        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    private ActionBar getActionBar() {
        return ((ActionBarActivity) getActivity()).getSupportActionBar();
    }
}
