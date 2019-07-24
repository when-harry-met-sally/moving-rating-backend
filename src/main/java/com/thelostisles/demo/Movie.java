package com.thelostisles.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private Long id;
    private String primaryTitle;
    private boolean isAdult;
    private boolean isFavorite;
    private int runtimeMinutes;
    private String genres;
    private List<Review> reviews;
    private List<Name> names;
}
