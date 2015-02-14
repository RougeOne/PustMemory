package com.redgrue.pm.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.redgrue.pm.R;
import com.redgrue.pm.container.MemoryAnswersContainer;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by rouge on 14.02.2015.
 */
public class ListenVoiceNumbFragment extends Fragment implements TextToSpeech.OnInitListener {

    private static final String Log_TAG = ListenVoiceNumbFragment.class.getSimpleName();
    private final ArrayList<String> arrayCorrectAnswers;


    private TextToSpeech mTTS;
    private ImageButton getVoice;


    public ListenVoiceNumbFragment(MemoryAnswersContainer memoryAnswersContainer) {
        arrayCorrectAnswers = memoryAnswersContainer.getCorrectAnswersArray();
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
        getVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTTS.speak("14", TextToSpeech.QUEUE_FLUSH, null);
            }

        });
        return view;
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
