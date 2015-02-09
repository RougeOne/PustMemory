package com.redgrue.pm.fragments;

import android.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.redgrue.pm.R;
import com.redgrue.pm.container.MemoryAnswersContainer;

import java.text.DateFormat;
import java.util.Date;

/**
 * Fragment used for managing interactions for and presentation of a navigation drawer.
 * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
 * design guidelines</a> for a complete explanation of the behaviors implemented here.
 */
public class StatisticsDrawerFragment extends Fragment {

    private static final String Log_TAG = StatisticsDrawerFragment.class.getSimpleName();

    private DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;

    //Statistics views
    private TextView userNameView;
    private TextView currentDateView;
    private TextView amountEleventsView;
    private TextView amountMistakesView;
    private TextView userMemorizeTime;
    private TextView userMemorizeIndex;

    public StatisticsDrawerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_drawer_statistics, container, false);
        userNameView = (TextView) view.findViewById(R.id.statisticsFieldUserName);
        currentDateView = (TextView) view.findViewById(R.id.statisticsFieldDate);
        amountEleventsView = (TextView) view.findViewById(R.id.statisticsFieldElements);
        amountMistakesView = (TextView) view.findViewById(R.id.statisticsFieldMistakes);
        userMemorizeTime = (TextView) view.findViewById(R.id.statisticsFieldTime);
        userMemorizeIndex = (TextView) view.findViewById(R.id.statisticsFieldMemoryIndex);
        return view;
    }

    public void fillUserStatisticsFields(MemoryAnswersContainer memoryAnswersContainer) {
        final MemoryAnswersContainer mMemoryAnswersContainer = memoryAnswersContainer;
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Float commonShowTime =(float) mMemoryAnswersContainer.calcCommonShowTime()/1000;
        Short amountOfElements = mMemoryAnswersContainer.getmAmountElements();
        Short amountOfMistakes = mMemoryAnswersContainer.checkMistakes();


        userNameView.setText("Rouge");
        currentDateView.setText(currentDateTimeString);
        amountEleventsView.setText(amountOfElements.toString());
        amountMistakesView.setText(String.format("%d (%d%%)", amountOfMistakes, amountOfMistakes/amountOfElements*100 ));
        userMemorizeTime.setText(String.format("%.2f сек. (%.2f элемент)", commonShowTime, commonShowTime/amountOfElements));
        userMemorizeIndex.setText("Ошибок больше 10%");

    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
    }


}
