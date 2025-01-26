package dev.jlipka.cinemasystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auditoriums")
@Builder
public class Auditorium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auditorium_id")
    private Long id;
    private String name;
    private int capacity;
    @Enumerated(EnumType.STRING)
    @Column(name = "auditorium_type")
    private AuditoriumType auditoriumType;
    @Enumerated(EnumType.STRING)
    @Column(name = "screen_type")
    private ScreenType screenType;
}
