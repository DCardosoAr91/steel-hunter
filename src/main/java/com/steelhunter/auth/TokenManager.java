package com.steelhunter.auth;

import com.steelhunter.config.Config;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String accessToken;

    public static String getToken() {
        if(accessToken == null){
            generateToken();
        }
        return accessToken;
    }

    private static void generateToken() {
        Response response =
                given()
                        .auth()
                        .preemptive()
                        .basic(
                                Config.getSpotifyClientId(),
                                Config.getSpotifyClientSecret()
                        )
                        .contentType("application/x-www-form-urlencoded")
                        .formParam(
                                "grant_type",
                                "client_credentials"
                        )
                        .when()
                            .post("https://accounts.spotify.com/api/token");

        accessToken = response.jsonPath()
            .getString("access_token");
    }
}
