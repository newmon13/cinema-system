package dev.jlipka.cinemasystem.config;

import dev.jlipka.cinemasystem.repository.GenreRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    GenreInitializer genreInitializer(GenreRepository genreRepository) {
        return new GenreInitializer(genreRepository);
    }
}
