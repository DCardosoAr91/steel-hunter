package com.steelhunter.search;

import com.steelhunter.client.SpotifyClient;
import com.steelhunter.models.Artist;
import com.steelhunter.services.ArtistDiscoveryService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class SimilarArtistTest {

    private final SpotifyClient spotifyClient =
            new SpotifyClient();

    private final ArtistDiscoveryService service =
            new ArtistDiscoveryService(spotifyClient);


    @Test
    void shouldFindSimilarArtists(){

        List<Artist> artists =
                service.searchSimilarArtists("Ratt");

        artists.forEach(artist -> {
            System.out.println(artist.getName());
            System.out.println(artist.getGenres());
            System.out.println();
        });

        assertFalse(artists.isEmpty());
    }

    @Test
    void shouldFindIconGlamMetal(){

        Artist icon =
                service.searchArtistByNameAndGenre(
                        "ICON",
                        "glam metal"
                );

        System.out.println(icon.getName());
        System.out.println(icon.getGenres());
    }

}