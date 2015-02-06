package com.redgrue.pm.event;

import java.util.Map;

/**
 * Created by rouge on 29.01.2015.
 */
public class ElementTypeEvent {

    private short amountElements;
    private String memoryType;

    public ElementTypeEvent(short amountElements, String memoryType) {
        this.amountElements = amountElements;
        this.memoryType = memoryType;
    }

    public void setAmountElements(short amountElements) {
        this.amountElements = amountElements;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public short getAmountElements() {
        return amountElements;
    }
}
