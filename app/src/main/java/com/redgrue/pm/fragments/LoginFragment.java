package com.redgrue.pm.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redgrue.pm.AppMnemoNet;
import com.redgrue.pm.R;
import com.redgrue.pm.event.LoginEvent;

/**
 * Created by rouge on 08.02.2015.
 */
public class LoginFragment extends Fragment {
    private static final String Log_TAG = LoginFragment.class.getSimpleName();

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login_page, container, false);
        view.findViewById(R.id.registerUserLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppMnemoNet.getInstance().getBus().post(new LoginEvent());
            }
        });
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(Log_TAG, "onStop");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(Log_TAG, "onDetach");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(Log_TAG, "onDestroy");
    }
}
