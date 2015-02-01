package com.redgrue.pm.event;

/**
 * Created by rouge on 29.01.2015.
 */
public class ElementsAmountEvent {
    private short amountElements;

    public ElementsAmountEvent(short amount) {
        amountElements = amount;
    }

    public short getAmountElements() {
        return amountElements;
    }
}
