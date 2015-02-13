package com.redgrue.pm.fragments;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.redgrue.pm.AppMnemoNet;
import com.redgrue.pm.R;
import com.redgrue.pm.container.MemoryAnswersContainer;
import com.redgrue.pm.event.ShowCorrectAnswersEvent;

import java.util.ArrayList;

/**
 * Created by rouge on 11.02.2015.
 */
public class ShowDeckCardFragment extends Fragment {

    private static final String Log_TAG = ShowDeckCardFragment.class.getSimpleName();
    private final ArrayList<Integer> cardsDeck;

    // Users and Correct answers
    private final ArrayList<String> arrayCorrectAnswers;
    private ArrayList<Long> arrayTimeShowAnswers;

    // Tracking variables
    private short counter;
    private long showTime;

    // Working Views
    private ImageView cardView;
    private RelativeLayout mainBackground;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fillCardsDeck();
    }

    public ShowDeckCardFragment(MemoryAnswersContainer memoryAnswersContainer) {
        cardsDeck = new ArrayList<Integer>(52);
        arrayCorrectAnswers = memoryAnswersContainer.getCorrectAnswersArray();
        arrayTimeShowAnswers = memoryAnswersContainer.getTimeShowAnswers();
        counter = 0;
    }

    private void fillCardsDeck() {
        int identifier;
        String symbol = "c";
        for (int i = 0; i < 4; i++) {
            if (i == 1)
                symbol = "d";
            else if (i == 2)
                symbol = "h";
            else if (i == 3)
                symbol = "s";
            for (int j = 0; j <= 12; j++) {
                identifier = getResources().getIdentifier(String.format("%s%d", symbol, j + 1), "drawable", getActivity().getPackageName());
                cardsDeck.add(identifier);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_show_cards, container, false);
        mainBackground = (RelativeLayout) view.findViewById(R.id.showCardMainBackground);
        cardView = (ImageView) view.findViewById(R.id.showCardView);
        showTime = System.currentTimeMillis();
        setCard();
        view.findViewById(R.id.showCardView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCard();
                changeBackgroundColor();
            }
        });
        return view;
    }

    private void setCard() {
        if (counter < arrayCorrectAnswers.size()) {
            showTime = System.currentTimeMillis() - showTime;
            arrayTimeShowAnswers.add(showTime);
            int randomIndex = Integer.parseInt(arrayCorrectAnswers.get(counter++));
            cardView.setImageResource(cardsDeck.get(randomIndex));
            showTime = System.currentTimeMillis();
        } else
            AppMnemoNet.getInstance().getBus().post(new ShowCorrectAnswersEvent());
    }

    private void changeBackgroundColor() {
        Log.d(Log_TAG, "Counter = " + counter % 2);
        if (counter % 2 == 0)
            mainBackground.setBackgroundColor(getResources().getColor(R.color.fb_grey));
        else
            mainBackground.setBackgroundColor(getResources().getColor(R.color.fb_blue_transparent));

    }


}
