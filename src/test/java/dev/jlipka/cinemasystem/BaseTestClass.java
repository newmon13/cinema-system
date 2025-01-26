package dev.jlipka.cinemasystem;

import dev.jlipka.cinemasystem.model.Movie;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseTestClass {
    protected static List<Movie> prepareMovies(int number) {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            movies.add(prepareMovie("Test Title " + i));
        }
        return movies;
    }
    protected static Movie prepareMovie(String title) {
        return Movie.builder()
                .name(title)
                .description("Test Description")
                .duration(Duration.ofHours(2))
                .minimalAge(12)
                .releaseDate(LocalDate.now())
                .build();
    }
}
