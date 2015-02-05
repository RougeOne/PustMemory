package com.redgrue.pm.container;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by rouge on 29.01.2015.
 */
public class MemoryAnswersContainer implements Serializable {

    public final static String twoNumbs = "TWO_NUMBS";
    public final static String threeNumbs = "THREE_NUMBS";
    public final static String cards = "CARDS";


    private int mAmountMistakes;

    private short mAmountElements;
    private ArrayList<String> correctAnswersArray;
    private ArrayList<String> usersAnswersArray;

    private ArrayList<String> timeShowAnswers;

    public MemoryAnswersContainer(short amountElements) {
        mAmountMistakes = 0;
        mAmountElements = amountElements;
        randomTwoNumbCollections(amountElements);
//        switch(type) {
//            case twoNumbs: {
//                randomTwoNumbCollections(amountElements);
//                break;
//            }
//            case threeNumbs: {
//                randomThreeNumbCollections(amountElements);
//                break;
//            }
//            case cards: {
//                randomCardsCollections(amountElements);
//                break;
//            }
//        }
    }

    public int checkMistakes() {
        for (int i = 0; i < correctAnswersArray.size(); i++) {
            if(correctAnswersArray.get(i) != usersAnswersArray.get(i))
                mAmountMistakes++;
        }
        return mAmountMistakes;
    }

    public void randomTwoNumbCollections(int amountElements) {
        Random random = new Random();
        correctAnswersArray = new ArrayList<>(amountElements);
        usersAnswersArray = new ArrayList<>(amountElements);
        for (int i = 0; i < amountElements; i++) {
            correctAnswersArray.add(String.format("%02d", random.nextInt(100)));
        }
        Log.d("CorrectAnswers", "Answers = " + correctAnswersArray.size());

    }

    public void randomThreeNumbCollections(int amountElements) {
        Random random = new Random();
        correctAnswersArray = new ArrayList<>(amountElements);
        usersAnswersArray = new ArrayList<>(amountElements);
        for (int i = 0; i < amountElements; i++) {
            correctAnswersArray.add(String.format("%03d", random.nextInt(100)));
        }
        Log.d("CorrectAnswers", "Answers = " + correctAnswersArray.size());

    }

    public void randomCardsCollections(int amountElements) {

    }

    public int getAmountMistakes() {
        return mAmountMistakes;
    }

    public void setAmountMistakes(int mAmountMistakes) {
        this.mAmountMistakes = mAmountMistakes;
    }

    // Get/Set amount of Elements
    public short getmAmountElements() {
        return mAmountElements;
    }

    public void setmAmountElements(short amountElements) {
        mAmountElements = amountElements;
    }

    // Get/Set correct answers array
    public ArrayList<String> getCorrectAnswersArray() {
        return correctAnswersArray;
    }

    public void setCorrectAnswersArray(ArrayList<String> correctAnswersArray) {
        this.correctAnswersArray = correctAnswersArray;
    }

    // Get/Set user's answers array
    public ArrayList<String> getUsersAnswersArray() {
        return usersAnswersArray;
    }

    public void setUsersAnswersArray(ArrayList<String> usersAnswersArray) {
        this.usersAnswersArray = usersAnswersArray;
    }

    // Get/Set user's answers time array
    public ArrayList<String> getTimeShowAnswers() {
        return timeShowAnswers;
    }

    public void setTimeShowAnswers(ArrayList<String> timeShowAnswers) {
        this.timeShowAnswers = timeShowAnswers;
    }
}
