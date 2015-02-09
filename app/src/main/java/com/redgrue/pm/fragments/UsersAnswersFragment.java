package com.redgrue.pm.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.redgrue.pm.AppMnemoNet;
import com.redgrue.pm.R;
import com.redgrue.pm.adapters.AdapterFillNumb;
import com.redgrue.pm.container.MemoryAnswersContainer;
import com.redgrue.pm.event.ShowStatisticsEvent;

import java.util.ArrayList;

public class UsersAnswersFragment extends Fragment {

    private static final String Log_TAG = UsersAnswersFragment.class.getSimpleName();

    private final String emptyAnswer = "∞";
    private int counter;
    private int maxAnswerSize;
    private ArrayList<String> usersAnswersArray;
    private final ArrayList<String> correctAnswersArray;
    private AdapterFillNumb mAdapterFillNumb;
    private GridView usersAnswersGridView;

    private boolean isAnswerCompareCheck;

    public UsersAnswersFragment(MemoryAnswersContainer memoryAnswersContainer) {

        MemoryAnswersContainer mMemoryAnswersContainer = memoryAnswersContainer;
        usersAnswersArray = mMemoryAnswersContainer.getUsersAnswersArray();
        correctAnswersArray = mMemoryAnswersContainer.getCorrectAnswersArray();

        counter = 0;
        maxAnswerSize = correctAnswersArray.size();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(Log_TAG, "onAttach");
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.w(Log_TAG, "onCreateView");
        final View view = inflater.inflate(R.layout.fragment_set_answers, container, false);
        final EditText userAnswerEditText = (EditText) view.findViewById(R.id.usersAnswerEditText);

        usersAnswersGridView = (GridView) view.findViewById(R.id.AnswersGridView);
        mAdapterFillNumb = new AdapterFillNumb(getActivity(), R.layout.view_schedule_answer, usersAnswersArray);
        usersAnswersGridView.setAdapter(mAdapterFillNumb);
        usersAnswersGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isAnswerCompareCheck)
                    correctNumbDialog(position);
            }
        });
        view.findViewById(R.id.addUsersAnswer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usersAnswer = userAnswerEditText.getText().toString();
                if (TextUtils.isEmpty(usersAnswer))
                    usersAnswer = emptyAnswer;
                userAnswerEditText.setText("");
                addAnswers(usersAnswer);
            }
        });


        return view;
    }


    private boolean addAnswers(String answer) {
        if (counter++ < correctAnswersArray.size()) {
            usersAnswersArray.add(answer);
            mAdapterFillNumb.notifyDataSetChanged();
        } else {
            Toast.makeText(getActivity(), "Нажмите проверка", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean changeUsersAnswer(int position, String correctedAnswer) {
        usersAnswersArray.set(position, correctedAnswer);
        Log.d(Log_TAG, "Answers " + correctedAnswer + " Position " + position);
        Log.d(Log_TAG, "Get position " + usersAnswersArray.get(position));
        mAdapterFillNumb.notifyDataSetChanged();
        return true;
    }

    private void isInterrupted() {
        while (counter++ < correctAnswersArray.size()) {
            usersAnswersArray.add(emptyAnswer);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_check_answers, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.actionCheckResult): {
                if (counter < maxAnswerSize)
                    isInterrupted();
                mAdapterFillNumb.checkResults(correctAnswersArray, true);
                isAnswerCompareCheck = true;
                AppMnemoNet.getInstance().getBus().post(new ShowStatisticsEvent());
                break;
            }
            default: {
                Log.e(Log_TAG, "default onOptionsItemSelected was implemented");
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void correctNumbDialog(final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        final View messageView = layoutInflater.inflate(R.layout.dialog_correct_numb, null);

        final EditText correctAnswerEditView = (EditText) messageView.findViewById(R.id.usersCorrectAnswers);
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Позиция " + (position + 1))
                .setView(messageView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        String correctedAnswer;
                        if (!TextUtils.isEmpty(correctAnswerEditView.getText().toString())) {
                            correctedAnswer = correctAnswerEditView.getText().toString();
                            changeUsersAnswer(position, correctedAnswer);
                        }
                        dialog.dismiss();
                    }
                }).setNeutralButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog alert = dialog.create();
        alert.show();
    }

}
