package com.tkerambloch.github.dto;

import com.tkerambloch.github.domain.mongodb.Bike;

import java.util.ArrayList;

/**
 * Created by tkerambloch on 30/05/2016.
 */
public class BikeListDTO {

    private double          count = 0l;
    private ArrayList<Bike> bikes = new ArrayList<>();

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public ArrayList<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(ArrayList<Bike> bikes) {
        this.bikes = bikes;
    }
}
