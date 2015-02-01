package com.redgrue.pm.objects;

import android.util.Log;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by rouge on 29.01.2015.
 */
public class MemoryAnswersContainer implements Serializable {

    public final static String twoNumbs = "TWO_NUMBS";
    public final static String threeNumbs = "THREE_NUMBS";
    public final static String cards = "CARDS";


    private short mAmountElements;
    private String[] correctAnswersArray;
    private String[] usersAnswersArray;

    private String[] timeShowAnswers;

    public MemoryAnswersContainer(short amountElements) {
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

    public void randomTwoNumbCollections(int amountElements) {
        Random random = new Random();
        correctAnswersArray = new String[amountElements];
        for (int i = 0; i < amountElements; i++) {
            correctAnswersArray[i] = (String.format("%02d", random.nextInt(100)));
        }
        Log.d("CorrectAnswers", "Answers = " + correctAnswersArray.length);

    }

    public void randomThreeNumbCollections(int amountElements) {
        Random random = new Random();
        correctAnswersArray = new String[amountElements];
        for (int i = 0; i < amountElements; i++) {
            correctAnswersArray[i] = (String.format("%02d", random.nextInt(100)));
        }
        Log.d("CorrectAnswers", "Answers = " + correctAnswersArray.length);

    }

    public void randomCardsCollections(int amountElements) {

    }

        // Get/Set amount of Elements
    public short getmAmountElements() {
        return mAmountElements;
    }
    public void setmAmountElements(short amountElements) {
        mAmountElements = amountElements;
    }

    // Get/Set correct answers array
    public String[] getCorrectAnswersArray() {
        return correctAnswersArray;
    }
    public void setCorrectAnswersArray(String[] correctAnswersArray) {
        this.correctAnswersArray = correctAnswersArray;
    }

    // Get/Set user's answers array
    public String[] getUsersAnswersArray() {
        return usersAnswersArray;
    }
    public void setUsersAnswersArray(String[] usersAnswersArray) {
        this.usersAnswersArray = usersAnswersArray;
    }

    // Get/Set user's answers time array
    public String[] getTimeShowAnswers() {
        return timeShowAnswers;
    }
    public void setTimeShowAnswers(String[] timeShowAnswers) {
        this.timeShowAnswers = timeShowAnswers;
    }
}
