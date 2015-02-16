package com.redgrue.pm.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.redgrue.pm.AppMnemoNet;
import com.redgrue.pm.R;
import com.redgrue.pm.container.MemoryAnswersContainer;
import com.redgrue.pm.event.CorrectAnswersEvent;

import java.util.ArrayList;

public class ShowCorrectNumbFragment extends Fragment {

    private static final String Log_TAG = ShowCorrectNumbFragment.class.getSimpleName();

    // Tracking variables
    private short counter;
    private long showTime;

    // Users and Correct answers
    private final ArrayList<String> arrayCorrectAnswers;
    private ArrayList<Long> arrayTimeShowAnswers;

    // Working View
    private TextView textView;
    private RelativeLayout mainBackground;


    public ShowCorrectNumbFragment(MemoryAnswersContainer memoryAnswersContainer) {
        arrayCorrectAnswers = memoryAnswersContainer.getCorrectAnswersArray();
        arrayTimeShowAnswers = memoryAnswersContainer.getTimeShowAnswers();
        counter = 0;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_show_numb, container, false);
        mainBackground = (RelativeLayout) view.findViewById(R.id.showNumbMainBackground);
        textView = (TextView) view.findViewById(R.id.ShowNumbView);
        showTime = System.currentTimeMillis();
        setNumb();
        view.findViewById(R.id.ShowNumbView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumb();
                changeBackgroundColor();
            }
        });
        view.findViewById(R.id.ButtonNextNumb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumb();
                changeBackgroundColor();
            }
        });
        return view;
    }

    private void changeBackgroundColor() {
        Log.d(Log_TAG, "Counter = " + counter % 2);
        if (counter % 2 == 0)
            mainBackground.setBackgroundColor(getResources().getColor(R.color.fb_grey));
        else
            mainBackground.setBackgroundColor(getResources().getColor(R.color.fb_blue_transparent));

    }

    private boolean setNumb() {
        if (counter < arrayCorrectAnswers.size()) {
            showTime = System.currentTimeMillis() - showTime;
            arrayTimeShowAnswers.add(showTime);
            textView.setText(arrayCorrectAnswers.get(counter++));
            showTime = System.currentTimeMillis();
        } else
            AppMnemoNet.getInstance().getBus().post(new CorrectAnswersEvent());

        return true;
    }
}
