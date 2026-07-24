package com.steelhunter.client;

import com.steelhunter.auth.TokenManager;
import com.steelhunter.enums.SearchType;
import com.steelhunter.models.Artist;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SpotifyClient {

    private static final String BASE_URL = "https://api.spotify.com";


    public Response search(
            String query,
            SearchType type
    ){

        return given()
                .baseUri(BASE_URL)
                .header(
                        "Authorization",
                        "Bearer " + TokenManager.getToken()
                )
                .queryParam(
                        "q",
                        query
                )
                .queryParam(
                        "type",
                        type.getValue()
                )
                .queryParam(
                        "limit",
                        10
                )
                .when()
                .get("/v1/search");
    }

    public Artist searchArtist(String artistName){

        Response response =
                search(
                        artistName,
                        SearchType.ARTIST
                );

        List<Artist> artists =
                response.jsonPath()
                        .getList(
                                "artists.items",
                                Artist.class
                        );

        return artists.stream()
                .filter(
                        artist ->
                                artist.getName()
                                        .equalsIgnoreCase(artistName)
                )
                .findFirst()
                .orElseThrow(
                        () -> new RuntimeException(
                                "Artist not found: " + artistName
                        )
                );
    }

    public List<Artist> searchArtistsByGenre(String genre){

        Response response =
                search(
                        genre,
                        SearchType.ARTIST
                );

        List<Artist> artists =
                response.jsonPath()
                        .getList(
                                "artists.items",
                                Artist.class
                        );

        return artists.stream()
                .filter(artist ->
                        artist.getGenres() != null &&
                                artist.getGenres()
                                        .stream()
                                        .anyMatch(
                                                g -> g.equalsIgnoreCase(genre)
                                        )
                )
                .toList();
    }


    public List<Artist> searchArtists(String query){
        Response response =
                search(
                        query,
                        SearchType.ARTIST
                );
        return response.jsonPath()
                .getList(
                        "artists.items",
                        Artist.class
                );
    }
}