package com.redgrue.pm.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login_page, container, false);
        userNameEditView = (EditText) view.findViewById(R.id.userNameEditView);
        userPasswordEditView = (EditText) view.findViewById(R.id.passwordEditView);

        view.findViewById(R.id.buttonEnterAsGuest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppMnemoNet.getInstance().getBus().post(new LoginEvent());
            }
        });
        view.findViewById(R.id.registerUserLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saveUserLogin()) {
                    Toast.makeText(getActivity(), "Пользователь " + createUser() + " создан",Toast.LENGTH_SHORT).show();
                    AppMnemoNet.getInstance().getBus().post(new LoginEvent());
                } else {
                    Toast.makeText(getActivity(), "Введите имя", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    private String createUser() {
        return userNameEditView.getText().toString();
    }

    private String createPassword() {
            return  userPasswordEditView.getText().toString();
    }

    private boolean saveUserLogin() {
        if (!TextUtils.isEmpty(createUser())) {
            final SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = preferences.edit();
            editor.putString(KEY_USER_NAME, createUser());
            editor.putString(KEY_USER_PASSWORD, createPassword());
            Log.d(Log_TAG, "User = " + createUser() + ":Password = " + createPassword() );
            editor.apply();
            return true;
        }
        return false;
    }

    private boolean loginOnSite() {



        return false;
    }

}
