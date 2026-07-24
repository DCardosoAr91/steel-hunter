package com.steelhunter.models;

import java.util.List;

public class ArtistListResponse {

    private List<Artist> artists;

    public List<Artist> getArtists(){
        return artists;
    }

    public void setArtists(List<Artist> artists){
        this.artists = artists;
    }
}
