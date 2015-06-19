package org.event.controller.model;

import java.util.Date;

public class Tick {
    String symbol;
    Double price;
    Date timeStamp;

    public Tick(String s, double p, long t) {
        symbol = s;
        price = new Double(p);
        timeStamp = new Date(t);
    }
    
    public double getPrice() {
    	return price.doubleValue();
    }
    
    public String getSymbol() {
    	return symbol;
    }
    
    public Date getTimeStamp() {
    	return timeStamp;
    }

    public String toString() {
        return "Price: " + price.toString() + " time: " + timeStamp.toString();
    }
}
