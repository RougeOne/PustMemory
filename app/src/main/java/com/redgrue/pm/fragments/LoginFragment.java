package com.redgrue.pm.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.redgrue.pm.AppMnemoNet;
import com.redgrue.pm.R;
import com.redgrue.pm.event.LoginEvent;

import java.util.HashSet;
import java.util.Map;

/**
 * Created by rouge on 08.02.2015.
 */
public class LoginFragment extends Fragment {
    private static final String Log_TAG = LoginFragment.class.getSimpleName();
    private EditText userNameEditView;
    private EditText userPasswordEditView;
    public static final String KEY_USER_NAME = "KEY_USER_NAME";
    public static final String KEY_USER_PASSWORD = "KEY_USER_PASSWORD";


    boolean check = false;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login_page, container, false);
        userNameEditView = (EditText) view.findViewById(R.id.userNameEditView);
        userPasswordEditView = (EditText) view.findViewById(R.id.passwordEditView);

        view.findViewById(R.id.registerUserLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!check) {
                    saveUserLogin();
                    Log.d(Log_TAG, "save preferences");
                    check = !check;
                } else {
                    loadData();
                }
//                AppMnemoNet.getInstance().getBus().post(new LoginEvent());
            }
        });
        return view;
    }

    private void saveUserLogin() {
        final String userName = userNameEditView.getText().toString();
        final String userPassword = userPasswordEditView.getText().toString();

        final SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();


        editor.putString(KEY_USER_NAME, userName);
        editor.putString(KEY_USER_PASSWORD, userPassword);
        editor.commit();

    }

    private void loadData() {
        final SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);

    }

}
