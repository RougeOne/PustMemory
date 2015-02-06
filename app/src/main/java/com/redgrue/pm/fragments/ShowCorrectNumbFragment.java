package com.redgrue.pm.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.redgrue.pm.AppMnemoNet;
import com.redgrue.pm.R;
import com.redgrue.pm.container.MemoryAnswersContainer;
import com.redgrue.pm.event.ShowCorrectAnswersEvent;

import java.util.ArrayList;

/**
 * Created by rouge on 19.01.2015.
 */
public class ShowCorrectNumbFragment extends Fragment {

    private static final String Log_TAG = ShowCorrectNumbFragment.class.getSimpleName();
    private TextView textView;
    private short counter;
    private final ArrayList<String> arrayCorrectAnswers;
    private ArrayList<Long> arrayTimeShowAnswers;
    private long showTime;


    public ShowCorrectNumbFragment(MemoryAnswersContainer memoryAnswersContainer) {
        arrayCorrectAnswers = memoryAnswersContainer.getCorrectAnswersArray();
        arrayTimeShowAnswers = memoryAnswersContainer.getTimeShowAnswers();
        counter = 0;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_show_numb, container, false);
        textView = (TextView) view.findViewById(R.id.ShowNumbView);
        showTime = System.currentTimeMillis();
            setNumb();
        view.findViewById(R.id.ShowNumbView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumb();
            }
        });
        view.findViewById(R.id.ButtonNextNumb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumb();
            }
        });
        return view;
    }

    private void setNumb() {
        if (counter < arrayCorrectAnswers.size()) {
            showTime = System.currentTimeMillis() - showTime;
            arrayTimeShowAnswers.add(showTime);
            textView.setText(arrayCorrectAnswers.get(counter++));
            showTime = System.currentTimeMillis();
        } else
            AppMnemoNet.getInstance().getBus().post(new ShowCorrectAnswersEvent());
    }
}
