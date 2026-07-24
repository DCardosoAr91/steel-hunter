package com.steelhunter.services;

import com.steelhunter.models.Artist;

import java.util.List;

public class GenreFilter {

    public List<Artist> filterByGenre(
            List<Artist> artists,
            String genre
    ){

        return artists.stream()
                .filter(
                        artist -> artist.getGenres()
                                .contains(genre)
                )
                .toList();
    }
}
