package com.steelhunter.models;


import com.steelhunter.enums.SearchType;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SearchReport {
    private final String query;
    private final SearchType searchType;
    private final Artist artist;

    private final int statusCode;
    private final long responseTime;

    private final LocalDateTime executionTime;

    public SearchReport(
            String query,
            SearchType searchType,
            Artist artist,
            int statusCode,
            long responseTime
    ){

        this.query = query;
        this.searchType = searchType;
        this.artist = artist;
        this.statusCode = statusCode;
        this.responseTime = responseTime;
        this.executionTime = LocalDateTime.now();
    }

    public void print(){
        printHeader();
        printSearch();
        printArtist();
        printGenres();
        printRequest();
        printFooter();
    }

    private void printHeader(){
        System.out.println(
                """
                ╔════════════════════════════════════════════════════════╗
                ║                   🎸 STEEL HUNTER                      ║
                ║               Artist Discovery Report                  ║
                ╚════════════════════════════════════════════════════════╝        
                """
        );
    }
    private void printSearch(){
        System.out.printf("""
                
                🔎 SEARCH
                
                ──────────────────────────────────────────────
                Query           : %s
                Search Type     : %s                
                """,
                query,
                searchType.getValue()
        );
    }
    private void printArtist(){
        System.out.printf("""
                
                🎤 ARTIST
                
                ──────────────────────────────────────────────
                Name            : %s
                Spotify ID      : %s
                Popularity      : %d / 100
                Followers       : %s
                """,
                artist.getName(),
                artist.getId(),
                artist.getPopularity(),
                formatFollowers()
        );
    }
    private void printGenres(){
        System.out.println("""
                
                🎵 GENRES
                ──────────────────────────────────────────────
                """);
        artist.getGenres().forEach(
                genre -> System.out.println(" • " + genre)
        );
        System.out.println();
    }
    private void printRequest(){
        System.out.printf("""
    
            🌐 REQUEST
            ──────────────────────────────────────────────
            Status Code     : %d
            Response Time   : %d ms
            Execution       : %s
            """,
            statusCode,
            responseTime,
            executionTime.format(
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            )
        );
    }

    private void printFooter(){
        System.out.println("""
        ════════════════════════════════════════════════
        Status           : SUCCESS ✅
        ════════════════════════════════════════════════
        """);
    }

    private String formatFollowers(){
        if (artist.getFollowers() == null){
            return "N/A";
        }
        NumberFormat formatter =
                NumberFormat.getInstance(new Locale("pt", "BR"));
        return formatter.format(
                artist.getFollowers().getTotal()
        );
    }

    private String formatExecutionTime(){
        return executionTime.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        );
    }

}
