package com.redgrue.pm.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.redgrue.pm.AppMnemoNet;
import com.redgrue.pm.R;
import com.redgrue.pm.event.ShowDistractExerciseEvent;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by rouge on 29.01.2015.
 */
public class DistractExerciseFragment extends Fragment {
    private static final String Log_TAG = DistractExerciseFragment.class.getSimpleName();

    private String[] correctEqutionAnswers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        GenerateExercise generateExercise = new GenerateExercise();
        String[] equationString = generateExercise.generateEquationString();
        correctEqutionAnswers = generateExercise.correctEquationAnswers;

        final View view = inflater.inflate(R.layout.fragment_distract_exercise, container, false);
        TextView tvEquationFirst = (TextView) view.findViewById(R.id.mathEquationStringFirst);
        tvEquationFirst.setText(equationString[0]);
        TextView tvEquationSecond = (TextView) view.findViewById(R.id.mathEquationStringSecond);
        tvEquationSecond.setText(equationString[1]);
        TextView tvEquationThird = (TextView) view.findViewById(R.id.mathEquationStringThird);
        tvEquationThird.setText(equationString[2]);
        TextView tvEquationFourth = (TextView) view.findViewById(R.id.mathEquationStringFourth);
        tvEquationFourth.setText(equationString[3]);
        TextView tvEquationFifth = (TextView) view.findViewById(R.id.mathEquationStringFifth);
        tvEquationFifth.setText(equationString[4]);

        EditText editTextAnswersFirst = (EditText) view.findViewById(R.id.equationAnswerFirst);
        EditText editTextAnswersSecond = (EditText) view.findViewById(R.id.equationAnswerSecond);
        EditText editTextAnswersThird = (EditText) view.findViewById(R.id.equationAnswerThird);
        EditText editTextAnswersFourth = (EditText) view.findViewById(R.id.equationAnswerFourth);
        EditText editTextAnswersFifth = (EditText) view.findViewById(R.id.equationAnswerFifth);

        view.findViewById(R.id.checkUsersEquationAnswers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answers1 = ((EditText) view.findViewById(R.id.equationAnswerFirst)).getText().toString();
                String answers2 = ((EditText) view.findViewById(R.id.equationAnswerSecond)).getText().toString();
                String answers3 = ((EditText) view.findViewById(R.id.equationAnswerThird)).getText().toString();
                String answers4 = ((EditText) view.findViewById(R.id.equationAnswerFourth)).getText().toString();
                String answers5 = ((EditText) view.findViewById(R.id.equationAnswerFifth)).getText().toString();
                String[] usersAnswers = {answers1, answers2, answers3, answers4, answers5};

                Log.d(Log_TAG, "Answers " + checkExerciseAnswers(usersAnswers));
                if (checkExerciseAnswers(usersAnswers))
                    AppMnemoNet.getInstance().getBus().post(new ShowDistractExerciseEvent());
            }
        });

        return view;
    }

    private boolean checkExerciseAnswers(String[] usersAnswers) {
        if (Arrays.equals(usersAnswers, correctEqutionAnswers))
            return true;
        else
            return false;
    }

    private class GenerateExercise {
        public String[] correctEquationAnswers;

        public String[] generateEquationString() {
            String[] mathEquationString = new String[5];
            correctEquationAnswers = new String[5];
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                int firstNumb = random.nextInt(100);
                int secondNumb = random.nextInt(100);
                correctEquationAnswers[i] = String.valueOf(firstNumb + secondNumb);
                mathEquationString[i] = "" + firstNumb + " + " + secondNumb + " = ";
            }
            return mathEquationString;
        }
    }
}
