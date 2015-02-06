package com.redgrue.pm.container;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


public class MemoryAnswersContainer implements Serializable {

    // Class that create random elements relating the type(numbers, letters or cards and photos)


    public static final String TYPE_NUMBS_TWO = "TYPE_NUMBS_TWO";
    public static final String TYPE_NUMBS_THREE = "TYPE_THREE_NUMBS";
    public static final String TYPE_CARDS = "TYPE_CARDS";
    public static final String TYPE_VOICE_NUMBS = "TYPE_VOICE_NUMBS";


    private int mAmountMistakes = 0;
    private final short mAmountElements;

    private ArrayList<String> correctAnswersArray;
    private ArrayList<Long> timeShowAnswers;
    private ArrayList<String> usersAnswersArray;

    public MemoryAnswersContainer(short amountElements, String type) {
        mAmountElements = amountElements;
        timeShowAnswers = new ArrayList<>(amountElements);
        switch (type) {
            case TYPE_NUMBS_TWO: {
                randomTwoNumbCollections(amountElements);
                break;
            }
            case TYPE_NUMBS_THREE: {
                randomThreeNumbCollections(amountElements);
                break;
            }
            case TYPE_CARDS: {
                randomCardsCollections(amountElements);
                break;
            }
        }
    }

    private int checkMistakes() {
        int mist = 0;
        for (int i = 0; i < correctAnswersArray.size(); i++) {
            if (correctAnswersArray.get(i) != usersAnswersArray.get(i))
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
            correctAnswersArray.add(String.format("%03d", random.nextInt(1000)));
        }
        Log.d("CorrectAnswers", "Answers = " + correctAnswersArray.size());

    }

    public void randomCardsCollections(int amountElements) {

    }

    // Get amount of Mistakes
    public int getAmountMistakes() {
        return mAmountMistakes;
    }

    // Get amount of Elements
    public short getmAmountElements() {
        return mAmountElements;
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
    public ArrayList<Long> getTimeShowAnswers() {
        return timeShowAnswers;
    }

    public void setTimeShowAnswers(ArrayList<Long> timeShowAnswers) {
        this.timeShowAnswers = timeShowAnswers;
    }
}
