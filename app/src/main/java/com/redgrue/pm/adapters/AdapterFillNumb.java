package com.redgrue.pm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.redgrue.pm.R;

import java.util.ArrayList;

/**
 * Created by rouge on 24.01.2015.
 */
public class AdapterFillNumb extends ArrayAdapter<String> {

    private static final String Log_TAG = AdapterFillNumb.class.getSimpleName();
    private final LayoutInflater mLayoutInflater;
    private final int mLayoutResId;
    private boolean isCorrect = false;
    private ArrayList<String> mCorrectAnswers;

    public AdapterFillNumb(Context context, int resource, ArrayList<String> userAnswers, ArrayList<String> correctAnswers) {
        super(context, resource, userAnswers);
        mLayoutInflater = LayoutInflater.from(context);
        mLayoutResId = resource;
        mCorrectAnswers = correctAnswers;
    }

    public void correctAnswers(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(mLayoutResId, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.answerView = (TextView) convertView.findViewById(R.id.showNumbButton);
            viewHolder.numerationView = (TextView) convertView.findViewById(R.id.adapterTextNumeration);
            viewHolder.correctAnswersView = (TextView) convertView.findViewById(R.id.adapterTextCoincidence);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String userAnswer = getItem(position);
        String correctAnswers = mCorrectAnswers.get(position);

        viewHolder.numerationView.setText(String.format("%02d.", position + 1));
        viewHolder.answerView.setText(userAnswer);
        viewHolder.correctAnswersView.setText(correctAnswers);
        if(isCorrect) {
            viewHolder.correctAnswersView.setVisibility(View.VISIBLE);
            if (!mCorrectAnswers.get(position).equals(getItem(position))) {
                viewHolder.answerView.setBackgroundColor(0x77FF0000);
            }
        }
        return convertView;
    }

    private class ViewHolder {
        public TextView answerView;
        public TextView numerationView;
        public TextView correctAnswersView;
    }
}