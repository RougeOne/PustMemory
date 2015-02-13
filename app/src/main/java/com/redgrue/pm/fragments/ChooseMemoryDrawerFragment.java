package com.redgrue.pm.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.redgrue.pm.AppMnemoNet;
import com.redgrue.pm.R;
import com.redgrue.pm.container.MemoryAnswersContainer;
import com.redgrue.pm.event.MemoryTypeEvent;

/**
 * Created by rouge on 01.02.2015.
 */
public class ChooseMemoryDrawerFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private static final String Log_TAG = ChooseMemoryDrawerFragment.class.getSimpleName();
    private String memoryType;

    private DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;

    public ChooseMemoryDrawerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_drawer_choose_memory, container, false);

        final RadioGroup chooseMemoryType = (RadioGroup) view.findViewById(R.id.memoryTypeRadioGroup);
        chooseMemoryType.setOnCheckedChangeListener(this);
        memoryType = MemoryAnswersContainer.TYPE_NUMBS_TWO;

        final EditText elementEditText = (EditText) view.findViewById(R.id.setElementsAmountEditView);
        view.findViewById(R.id.MstartButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(Log_TAG, "MStartButton was pressed");
                if (!elementEditText.getText().toString().isEmpty()) {
                    Short elementsAmount = Short.parseShort(elementEditText.getText().toString());
                    AppMnemoNet.getInstance().getBus().post(new MemoryTypeEvent(elementsAmount, memoryType));
                } else {
                    Toast.makeText(getActivity(), "Укажите число элементов", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Log.d(Log_TAG, "onCreateView");
        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getCheckedRadioButtonId()) {
            case (R.id.twoNumbs): {
                memoryType = MemoryAnswersContainer.TYPE_NUMBS_TWO;
                Log.d(Log_TAG, "Type " + memoryType);
                break;
            }
            case (R.id.threeNumbs): {
                memoryType = MemoryAnswersContainer.TYPE_NUMBS_THREE;
                Log.d(Log_TAG, "Type " + memoryType);
                break;
            }
            case (R.id.Cards): {
                memoryType = MemoryAnswersContainer.TYPE_CARDS;
                Log.d(Log_TAG, "Type " + memoryType);
                break;
            }
//            case (R.id.Voice): {
//                memoryType = MemoryAnswersContainer.TYPE_VOICE;
//                Log.d(Log_TAG, "Type " + memoryType);
//                break;
//            }
            default: {
                Log.e(Log_TAG, "default onClickListener was implemented");
            }
        }
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
