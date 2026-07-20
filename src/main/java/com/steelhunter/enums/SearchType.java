package com.steelhunter.enums;

public enum SearchType {
    ARTIST("artist"),
    TRACK("track"),
    ALBUM("album"),
    PLAYLIST("playlist");

    private final String value;

    SearchType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }


}
