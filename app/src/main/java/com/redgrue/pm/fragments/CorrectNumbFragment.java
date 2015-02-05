package com.redgrue.pm.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.redgrue.pm.AppMnemoNet;
import com.redgrue.pm.R;
import com.redgrue.pm.event.ShowCorrectAnswersEvent;

import java.util.ArrayList;

/**
 * Created by rouge on 19.01.2015.
 */
public class CorrectNumbFragment extends Fragment {

    private static final String Log_TAG = CorrectNumbFragment.class.getSimpleName();
    private TextView textView;
    private short counter;
    private final ArrayList<String> arrayCorrectAnswers;


    public CorrectNumbFragment(ArrayList<String> container) {
        arrayCorrectAnswers = container;
        counter = 0;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_show_numb, container, false);
        textView = (TextView) view.findViewById(R.id.ShowNumbView);
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
        if (counter < arrayCorrectAnswers.size())
            textView.setText(arrayCorrectAnswers.get(counter++));
        else
            AppMnemoNet.getInstance().getBus().post(new ShowCorrectAnswersEvent());
    }
}
