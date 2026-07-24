package com.steelhunter.services;

import com.steelhunter.client.SpotifyClient;
import com.steelhunter.enums.SearchType;
import com.steelhunter.models.Artist;
import io.restassured.response.Response;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


import static io.restassured.RestAssured.given;

public class ArtistDiscoveryService {

    private final SpotifyClient spotifyClient;

    private final ArtistSimilarityCalculator similarityCalculator =
            new ArtistSimilarityCalculator();

    public ArtistDiscoveryService(SpotifyClient spotifyClient) {
        this.spotifyClient = spotifyClient;
    }

    public Artist searchArtist(String artistName){
        return spotifyClient.searchArtist(artistName);
    }

    public List<Artist> searchArtistsByGenre(String genre){
        return spotifyClient.searchArtistsByGenre(genre);
    }

    public List<Artist> searchSimilarArtists(String artistName) {

        Artist target = searchArtist(artistName);

        System.out.println(
                target.getName()
                        + " => "
                        + target.getGenres()
        );

        Map<String, Artist> uniqueArtists =
                similarityCalculator.getRelevantGenres(target)
                        .stream()
                        .flatMap(
                                genre ->
                                        searchArtistsByGenre(genre).stream()
                        )
                        .filter(
                                artist ->
                                        artist.getId() != null
                        )
                        .filter(
                                artist ->
                                        !artist.getName()
                                                .equalsIgnoreCase(target.getName())
                        )
                        .collect(
                                Collectors.toMap(
                                        Artist::getId,
                                        artist -> artist,
                                        (a, b) -> a
                                )
                        );


        List<Artist> artists =
                uniqueArtists.values()
                        .stream()
                        .filter(
                                artist ->
                                        similarityCalculator.calculateSimilarity(
                                                target,
                                                artist
                                        ) >= 30
                        )
                        .sorted(
                                Comparator.comparingInt(
                                        (Artist artist) ->
                                                similarityCalculator.calculateSimilarity(
                                                        target,
                                                        artist
                                                )
                                ).reversed()
                        )
                        .toList();


        artists.forEach(
                artist ->
                        System.out.println(
                                artist.getName()
                                        + " => "
                                        + similarityCalculator.calculateSimilarity(
                                        target,
                                        artist
                                )
                        )
        );

        return artists;
    }



    public Artist searchArtistByNameAndGenre(
            String artistName,
            String genre
    ){

        List<Artist> artists =
                spotifyClient.searchArtists(artistName);

        return artists.stream()
                .filter(
                        artist ->
                                artist.getName()
                                        .equalsIgnoreCase(artistName)
                )
                .filter(
                        artist ->
                                artist.getGenres()
                                        .stream()
                                        .anyMatch(
                                                g ->
                                                        g.contains(
                                                                genre.toLowerCase()
                                                        )
                                        )
                )
                .findFirst()
                .orElseThrow(
                        () ->
                                new RuntimeException(
                                        "Artist not found: "
                                                + artistName
                                )
                );
    }
}
