package com.redgrue.pm.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

import com.redgrue.pm.R;

/**
 * Created by rouge on 08.02.2015.
 */
public class PustPrefsActivity extends PreferenceActivity {

    public static void startActivity(Activity context) {
        final Intent intent = new Intent(context, PustPrefsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
            onCreatePreferenceFragment();
    }


        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        private void onCreatePreferenceFragment() {
            getFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, new MyPreferenceFragment())
                    .commit();
        }

        public static class MyPreferenceFragment extends PreferenceFragment {
            @Override
            public void onCreate(final Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                addPreferencesFromResource(R.xml.prefs);
            }
        }

}
