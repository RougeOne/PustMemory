package com.redgrue.pm.event;

import java.util.Map;

/**
 * Created by rouge on 29.01.2015.
 */
public class ElementTypeEvent {

    public short amountElements;
    public String memoryType;

    public ElementTypeEvent(short amountElements, String memoryType) {
        this.amountElements = amountElements;
        this.memoryType = memoryType;
    }
}
