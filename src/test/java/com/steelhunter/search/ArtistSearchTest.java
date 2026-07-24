package com.steelhunter.search;

import com.steelhunter.client.SpotifyClient;
import com.steelhunter.enums.SearchType;
import com.steelhunter.models.Artist;
import com.steelhunter.reporting.SearchReport;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArtistSearchTest {

    private final SpotifyClient spotifyClient = new SpotifyClient();

    @Test
    void shouldSearchArtistSuccessfully(){
        Response response =
                spotifyClient.search("Ratt",
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
    void shouldSearchSpecificArtist() {
        Response response =
                spotifyClient.search(
                        "Ratt",
                        SearchType.ARTIST
                );
        Artist artist =
                response.jsonPath()
                        .getObject(
                                "artists.items[0]",
                                Artist.class
                        );
        SearchReport report =
                new SearchReport(
                        "Ratt",
                        SearchType.ARTIST,
                        artist,
                        response.statusCode(),
                        response.getTime()
                );
        report.print();
        assertEquals(
                200,
                response.statusCode()
        );
        assertEquals(
                "Ratt",
                artist.getName()
        );
        assertTrue(
                artist.getGenres()
                        .contains("glam metal")
        );
    }
}
