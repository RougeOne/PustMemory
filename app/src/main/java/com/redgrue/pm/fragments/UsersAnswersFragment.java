package com.redgrue.pm.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
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
import com.redgrue.pm.event.ShowDistractExerciseEvent;
import com.redgrue.pm.container.MemoryAnswersContainer;

import java.util.ArrayList;

/**
 * Created by rouge on 31.01.2015.
 */
public class UsersAnswersFragment extends Fragment {

    private static final String Log_TAG = UsersAnswersFragment.class.getSimpleName();

    private String emptyAnswer = "∞";
    private int counter;
    private ArrayList<String> usersAnswersArray;
    private final ArrayList<String> correctAnswersArray;
    private MemoryAnswersContainer mMemoryAnswersContainer;
    private AdapterFillNumb mAdapterFillNumb;
    private GridView usersAnswersGridView;

    public UsersAnswersFragment(MemoryAnswersContainer memoryAnswersContainer) {
        counter = 0;
        mMemoryAnswersContainer = memoryAnswersContainer;
        usersAnswersArray = mMemoryAnswersContainer.getUsersAnswersArray();
        correctAnswersArray = mMemoryAnswersContainer.getCorrectAnswersArray();
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
        mAdapterFillNumb = new AdapterFillNumb(getActivity(), R.layout.schedule_answer_view, usersAnswersArray);
        usersAnswersGridView.setAdapter(mAdapterFillNumb);
        usersAnswersGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                correctNumbDialog();
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

    private void isInterrupted() {
        while(counter++ < correctAnswersArray.size()) {
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
                isInterrupted();
                mAdapterFillNumb.checkResults(correctAnswersArray, true);
                break;
            }
            default: {
                Log.e(Log_TAG, "default onOptionsItemSelected was implemented");
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void correctNumbDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Some title")
                .setMessage("Some message")
                .setIcon(R.drawable.ic_launcher);

        final AlertDialog alert = dialog.create();
        alert.show();
        ;
    }

}
