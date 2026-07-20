package com.steelhunter.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Followers {

    private int total;

    public int getTotal(){
        return total;
    }
}
