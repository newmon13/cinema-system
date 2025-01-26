package dev.jlipka.cinemasystem.repository;


import dev.jlipka.cinemasystem.model.Auditorium;
import dev.jlipka.cinemasystem.model.AuditoriumType;
import dev.jlipka.cinemasystem.model.ScreenType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AuditoriumRepositoryTest {

    @Autowired
    AuditoriumRepository auditoriumRepository;

    @Test
    void shouldSaveAndFindAuditoriumInDatabase() {
        //given
        Auditorium auditorium = Auditorium.builder()
                .name("Test Auditorium")
                .capacity(200)
                .auditoriumType(AuditoriumType.REGULAR)
                .screenType(ScreenType.TYPE_2D)
                .build();
        //when
        Auditorium savedAuditorium = auditoriumRepository.save(auditorium);

        //then
        Optional<Auditorium> foundAuditorium = auditoriumRepository.findById(savedAuditorium.getId());
        assertThat(foundAuditorium).isPresent();
        assertThat(foundAuditorium.get().getName()).isEqualTo("Test Auditorium");
        assertThat(foundAuditorium.get().getCapacity()).isEqualTo(200);
        assertThat(foundAuditorium.get().getAuditoriumType()).isEqualTo(AuditoriumType.REGULAR);
        assertThat(foundAuditorium.get().getScreenType()).isEqualTo(ScreenType.TYPE_2D);
    }
}
