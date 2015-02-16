package com.redgrue.pm.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.redgrue.pm.AppMnemoNet;
import com.redgrue.pm.R;
import com.redgrue.pm.container.MemoryAnswersContainer;
import com.redgrue.pm.event.CorrectAnswersEvent;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by rouge on 14.02.2015.
 */
public class ListenVoiceNumbFragment extends Fragment implements TextToSpeech.OnInitListener {

    private static final String Log_TAG = ListenVoiceNumbFragment.class.getSimpleName();

    // Variables
    private final ArrayList<String> arrayCorrectAnswers;
    private int counter;

    //Text-to-Speech
    private TextToSpeech mTTS;
    private ImageButton getVoice;


    public ListenVoiceNumbFragment(MemoryAnswersContainer memoryAnswersContainer) {
        arrayCorrectAnswers = memoryAnswersContainer.getCorrectAnswersArray();
        counter = 0;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTTS = new TextToSpeech(getActivity(), this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_listen_values, container, false);
        getVoice = (ImageButton) view.findViewById(R.id.listenNumbVoice);
//        getVoice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!pronounceNumb())
//                    AppMnemoNet.getInstance().getBus().post(new CorrectAnswersEvent());
//            }
//        });

        getVoice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.post(mAction);
                        Log.d(Log_TAG, "Action_DOWN");
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                         Log.d(Log_TAG, "Action_UP");
                        break;

                }
                return false;
            }
        });
         Log.d(Log_TAG, "OnCreate Listen fragment");
        return view;
    }
    private Handler mHandler;
    private Runnable mAction = new Runnable() {
        @Override public void run() {
            pronounceNumb();
            mHandler.postDelayed(this, 1800);
            Log.d(Log_TAG, "RUNNABLE");
        }
    };



    private boolean pronounceNumb() {
        if(counter < arrayCorrectAnswers.size()) {
            mTTS.speak(arrayCorrectAnswers.get(counter++), TextToSpeech.QUEUE_FLUSH, null);
            return true;
        }
        return false;
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown mTTS!
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            Locale locale = new Locale("ru");
            int result = mTTS.setLanguage(locale);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Извините, этот язык не поддерживается");
            } else {
                getVoice.setEnabled(true);
            }
        } else {
            Log.e("TTS", "Ошибка!");
        }
    }
}
