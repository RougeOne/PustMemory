package com.redgrue.pm.fragments;

import android.app.Fragment;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.redgrue.pm.AppMnemoNet;
import com.redgrue.pm.R;
import com.redgrue.pm.event.ElementsAmountEvent;
import com.redgrue.pm.keyboard.NumbKeyboard;

/**
 * Created by rouge on 01.02.2015.
 */
public class ChooseMemoryDrawerFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private static final String Log_TAG = ChooseMemoryDrawerFragment.class.getSimpleName();
    private View mTargetView;
    private int defaultElemenets;

    private NumbKeyboard mKeyboardView;
    private Keyboard mKeyboard;


    public ChooseMemoryDrawerFragment() {
        defaultElemenets = 20;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /*public void showKeyboardWithAnimation() {
        if (mKeyboardView.getVisibility() == View.GONE) {
            Animation animation = AnimationUtils
                    .loadAnimation(getActivity(),
                            R.anim.slide_in_bottom);
            mKeyboardView.showWithAnimation(animation);
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_choose_memory, container, false);

        final RadioGroup chooseMemoryType = (RadioGroup) view.findViewById(R.id.memoryTypeRadioGroup);
        chooseMemoryType.setOnCheckedChangeListener(this);
        // Create Custom Numb Keyboard and and(view) to activity
      /*  mKeyboard = new Keyboard(getActivity(), R.xml.keyboard);
        mKeyboardView = (NumbKeyboard) getActivity().findViewById(R.id.keyboard_view);
        mKeyboardView.setKeyboard(mKeyboard);
        mKeyboardView.setOnKeyboardActionListener(new NumbKeyboardListener(
                getActivity()));
        mTargetView = (EditText) view.findViewById(R.id.setElementsAmountEditView);
        mTargetView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                showKeyboardWithAnimation();
                return true;
            }
        });*/


        final EditText elementEditText = (EditText) view.findViewById(R.id.setElementsAmountEditView);
        view.findViewById(R.id.MstartButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!elementEditText.getText().toString().isEmpty()) {
                    Short elementsAmount = Short.parseShort(elementEditText.getText().toString());
                    AppMnemoNet.getInstance().getBus().post(new ElementsAmountEvent(elementsAmount));
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
        switch(group.getCheckedRadioButtonId()) {
            case (R.id.twoNumbs): {
                Log.d(Log_TAG, "twoNubs");
                break;
            }
            case (R.id.threeNumbs): {
                Log.d(Log_TAG, "threeNumbs");
                break;
            }
            default: {
                Log.e(Log_TAG, "default onClickListener was implemented");
            }
        }
    }
}
