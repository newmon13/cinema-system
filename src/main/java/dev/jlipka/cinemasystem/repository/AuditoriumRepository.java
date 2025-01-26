package dev.jlipka.cinemasystem.repository;

import dev.jlipka.cinemasystem.model.Auditorium;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriumRepository extends CrudRepository<Auditorium, Long> {
}
