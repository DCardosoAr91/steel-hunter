package com.steelhunter.models;

public class SearchReport {

    private final Artist artist;

    public SearchReport(Artist artist){
        this.artist = artist;
    }

    public void print(){
        System.out.println("""
                =================================
                  🎸 STEEL HUNTER REPORT
                =================================

                Artist:
                %s

                Popularity:
                %d / 100

                Followers:
                %s

                Genres:
                %s

                Spotify URI:
                %s

                =================================

                """.formatted(
                artist.getName(),
                artist.getPopularity(),
                artist.getFollowers() != null
                        ? artist.getFollowers().getTotal()
                        : "N/A",
                artist.getGenres(),
                artist.getUri()
            ));
    };
}
