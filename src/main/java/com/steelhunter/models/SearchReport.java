package com.steelhunter.models;


import com.steelhunter.enums.SearchType;

import java.time.LocalDateTime;

public class SearchReport {
    private final String query;
    private final SearchType searchType;
    private final Artist artist;

    private final int statusCode;
    private final long responseTime;

    private final LocalDateTime executionTime;

    public SearchReport(
            String query,
            SearchType searchType,
            Artist artist,
            int statusCode,
            long responseTime
    ){

        this.query = query;
        this.searchType = searchType;
        this.artist = artist;
        this.statusCode = statusCode;
        this.responseTime = responseTime;
        this.executionTime = LocalDateTime.now();
    }

    public void print(){
        printHeader();
        printSearch();
        printArtist();
        printGenres();
        printRequest();
        printFooter();
    }

    private void printHeader(){

    }
    private void printSearch(){

    }
    private void printArtist(){

    }
    private void printGenres(){

    }
    private void printRequest(){

    }
    private void printFooter(){

    }
}
