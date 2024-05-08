package com.cydeo.entity;

import com.cydeo.enums.MovieState;
import com.cydeo.enums.MovieType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Movie extends BaseEntity{

    private String name;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private MovieType type;
    @Enumerated(EnumType.STRING)
    private MovieState state;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime releaseDate;
    private Integer duration;
    @Column(columnDefinition = "text")
    private String summary;

    @ManyToMany(fetch = FetchType.LAZY)  // lazy by default
    @JoinTable(name = "movie_genre_rel",
    joinColumns = @JoinColumn(name = "movie_id"),
    inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genreList;
}


