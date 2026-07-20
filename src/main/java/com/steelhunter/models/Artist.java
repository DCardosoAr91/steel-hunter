package com.steelhunter.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist{

    private String id;
    private String name;
    private int popularity;
    private List<String> genres;
    private Followers followers;
    private String uri;

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getPopularity(){
        return popularity;
    }

    public List<String> getGenres(){
        return genres;
    }

    public Followers getFollowers(){
        return followers;
    }

    public String getUri(){
        return uri;
    }

  @Override
  public String toString(){
        return """
                
                =================================
                STEEL HUNTER REPORT
                =================================
                Artist: %s
                ID: %s
                Popularity: %s
                Followers: %s
                Genres: %s
                Url: %s
                =================================
                """.formatted(
                        name,
                        id,
                        popularity,
                        followers != null ? followers.getTotal() : 0,
                        genres,
                        uri
        );

  }
}
