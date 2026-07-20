package com.steelhunter.client;

import com.steelhunter.auth.TokenManager;
import com.steelhunter.enums.SearchType;
import com.steelhunter.models.Artist;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SpotifyClient {

    private static final String BASE_URL = "https://api.spotify.com";

    public Response searchTracks(String query, SearchType type){
        return given()
                .baseUri(BASE_URL)
                .header(
                        "Authorization",
                        "Bearer " + TokenManager.getToken()
                )
                .queryParam("q", query)
                .queryParam("type", type.getValue())
                .queryParam("limit", 5)
                .when()
                .get("/v1/search");
    }

    public Artist searchArtist(String artistName){
        Response response =
                searchTracks(
                        artistName,
                        SearchType.ARTIST
                );
        return response.jsonPath()
                .getObject(
                        "artists.items[0]",
                        Artist.class
                );
    }
}
