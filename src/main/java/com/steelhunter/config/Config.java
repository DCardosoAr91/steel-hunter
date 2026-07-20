package com.steelhunter.config;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {

    private static final Dotenv dotenv = Dotenv.load();

    public static String getSpotifyClientId(){
        return dotenv.get("SPOTIFY_CLIENT_ID");
    }

    public static String getSpotifyClientSecret(){
        return dotenv.get("SPOTIFY_CLIENT_SECRET");
    }
}
