package com.steelhunter.search;

import com.steelhunter.client.SpotifyClient;
import com.steelhunter.models.Artist;
import com.steelhunter.services.ArtistDiscoveryService;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GenreSearchTest {


    private final SpotifyClient spotifyClient =
            new SpotifyClient();


    private final ArtistDiscoveryService discoveryService =
            new ArtistDiscoveryService(
                    spotifyClient
            );


    @Test
    void shouldSearchArtistsByGenre(){

        List<Artist> artists =
                discoveryService.searchArtistsByGenre(
                        "black metal"
                );


        artists.forEach(artist -> {

            System.out.println(
                    artist.getName()
            );

            System.out.println(
                    artist.getGenres()
            );

            System.out.println();

        });


        assertFalse(
                artists.isEmpty()
        );
    }



}