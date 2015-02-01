package com.redgrue.pm.preferences;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.redgrue.pm.R;

/**
 * Created by rouge on 27.01.2015.
 */
public class PrefsActivity extends PreferenceActivity {
    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, PrefsActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.prefs);
    }
}