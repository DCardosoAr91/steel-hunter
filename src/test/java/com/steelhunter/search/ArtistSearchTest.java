package com.steelhunter.search;

import com.steelhunter.client.SpotifyClient;
import com.steelhunter.enums.SearchType;
import com.steelhunter.models.Artist;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArtistSearchTest {

    private final SpotifyClient spotifyClient = new SpotifyClient();

    @Test
    void shouldSearchArtistSuccessfully(){
        Response response =
                spotifyClient.searchTracks("Ratt",
                        SearchType.ARTIST);

    System.out.println(response.asPrettyString());
    assertEquals(200, response.statusCode());

    String artistName =
            response.jsonPath()
                    .getString("artists.items[0].name");

    assertEquals(
                "Ratt",
                artistName
        );
    }

    @Test
    void shouldSearchSpecificArtist(){

        Artist artist =
                spotifyClient.searchArtist("Mercyful Fate");

        System.out.println(artist);
        assertEquals(
                "Mercyful Fate",
                artist.getName()
        );

        assertTrue(
                artist.getGenres()
                        .contains("metal")
        );
    }
}
