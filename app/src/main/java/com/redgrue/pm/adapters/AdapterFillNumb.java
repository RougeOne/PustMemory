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
    private ArrayList<String> mCorrectAnswersArray;
    private ArrayList<String> mUserAnswersArray;
    private boolean checkResult = false;

    public AdapterFillNumb(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        mLayoutInflater = LayoutInflater.from(context);
        mLayoutResId = resource;
    }

    public void checkResults(ArrayList<String> correctAnswersArray, boolean isCheckable) {
        checkResult = isCheckable;
        mCorrectAnswersArray = correctAnswersArray;
        notifyDataSetChanged();
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

        viewHolder.numerationView.setText(String.format("%02d.", position + 1));
        viewHolder.answerView.setText(userAnswer);
        if (checkResult) {
            String correctAnswers = mCorrectAnswersArray.get(position);
            viewHolder.correctAnswersView.setText(correctAnswers);
            viewHolder.correctAnswersView.setVisibility(View.VISIBLE);
            if (!correctAnswers.equals(userAnswer)) {
                viewHolder.answerView.setBackgroundColor(getContext().getResources().getColor(R.color.fb_red));
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