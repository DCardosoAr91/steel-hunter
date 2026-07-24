package com.steelhunter.services;

import com.steelhunter.models.Artist;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ArtistSimilarityCalculator {
    private static final Map<String, Integer> GENRE_WEIGHTS =
            Map.ofEntries(
                    Map.entry("glam metal", 50),
                    Map.entry("aor", 50),
                    Map.entry("arena rock", 40),
                    Map.entry("hard rock", 25),
                    Map.entry("glam rock", 25),
                    Map.entry("heavy metal", 20),
                    Map.entry("nwobhm", 20),
                    Map.entry("power metal", 20),
                    Map.entry("progressive metal", 15),
                    Map.entry("speed metal", 15),
                    Map.entry("thrash metal", 15),
                    Map.entry("doom metal", 15),
                    Map.entry("metal", 5),
                    Map.entry("rock", 1)
            );

    public int calculateSimilarity(
            Artist target,
            Artist candidate
    ) {

        int score = 0;

        List<String> relevantGenres = getRelevantGenres(target);

        System.out.println(
                target.getName()
                        + " vs "
                        + candidate.getName()
                        + " => "
                        + relevantGenres
        );

        for(String genre : relevantGenres) {

            if(candidate.getGenres().contains(genre)) {
                score += GENRE_WEIGHTS.getOrDefault(
                        genre,
                        0
                );
            }
        }

        return score;
    }

    public List<String> getRelevantGenres(Artist artist){

        List<String> relevant = artist.getGenres()
                .stream()
                .filter(
                        GENRE_WEIGHTS::containsKey
                )
                .sorted(
                        Comparator.comparingInt(
                                genre -> GENRE_WEIGHTS.get(genre)
                        ).reversed()
                )
                .limit(3)
                .toList();


        if(relevant.isEmpty()){
            return artist.getGenres()
                    .stream()
                    .limit(1)
                    .toList();
        }

        return relevant;
    }
}