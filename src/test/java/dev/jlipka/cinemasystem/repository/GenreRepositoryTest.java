package dev.jlipka.cinemasystem.repository;

import dev.jlipka.cinemasystem.config.ApplicationConfig;
import dev.jlipka.cinemasystem.model.Genre;
import dev.jlipka.cinemasystem.model.GenreType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(ApplicationConfig.class)
class GenreRepositoryTest {
    @Autowired
    GenreRepository genreRepository;

    @Test
    public void shouldSaveAndFindGenreInDatabase() {
        //given
        Genre genre = Genre.builder().genreType("Test Genre").build();
        //when
        Genre savedGenre = genreRepository.save(genre);
        //then
        Optional<Genre> foundGenre = genreRepository.findById(savedGenre.getId());
        assertThat(foundGenre).isPresent();
        assertThat(foundGenre.get().getGenreType()).isEqualTo(genre.getGenreType());
    }

    @Test
    public void shouldLoadAllGenres() {
        //given
        GenreType[] genres = GenreType.values();
        //when
        Iterable<Genre> all = genreRepository.findAll();
        List<Genre> foundGenres = new ArrayList<>();
        all.forEach(foundGenres::add);
        //then
        assertThat(foundGenres.size()).isEqualTo(genres.length);
    }
}