package com.redgrue.pm.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.redgrue.pm.AppMnemoNet;
import com.redgrue.pm.R;
import com.redgrue.pm.event.ElementsAmountEvent;

/**
 * Created by rouge on 01.02.2015.
 */
public class StartMemoryFragment extends Fragment {

    private static final String Log_TAG = StartMemoryFragment.class.getSimpleName();
    private int defaultElemenets;

    public StartMemoryFragment() {
        defaultElemenets = 20;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                } else {
                    Toast.makeText(getActivity(), "Укажите число элементов", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Log.d(Log_TAG, "onCreateView");
        return view;
    }
}
