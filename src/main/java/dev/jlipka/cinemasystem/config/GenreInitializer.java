package dev.jlipka.cinemasystem.config;

import dev.jlipka.cinemasystem.model.Genre;
import dev.jlipka.cinemasystem.model.GenreType;
import dev.jlipka.cinemasystem.repository.GenreRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class GenreInitializer {
    private final GenreRepository genreRepository;

    public GenreInitializer(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @PostConstruct
    public void initializeGenres() {
        if (genreRepository.count() == 0) {
            Arrays.stream(GenreType.values())
                    .map(genreType -> {
                        Genre.GenreBuilder builder = Genre.builder();
                        builder.genreType(genreType.name());
                        return builder.build();
                    })
                    .forEach(genreRepository::save);
        }
    }
}
