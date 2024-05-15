package com.cydeo.repository;

import com.cydeo.entity.Movie;
import com.cydeo.enums.MovieState;
import com.cydeo.enums.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to read a movie with a name
    List<Movie> getAllByName(String name);

    //Write a derived query to list all movies between a range of prices
    List<Movie> getAllByPriceBetween(BigDecimal price1,BigDecimal price2);

    //Write a derived query to list all movies where duration exists in the specific list of duration
    List<Movie> getAllByDurationIn(List<Integer> durationList);

    //Write a derived query to list all movies with higher than a specific release date
    List<Movie> getAllByReleaseDateAfter(LocalDateTime releaseDate);

    //Write a derived query to list all movies with a specific state and type
    List<Movie> getAllByStateAndType(MovieState movieState, MovieType movieType);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query to list all movies between a range of prices
    @Query("select m from Movie m where m.price between ?1 and ?2")
    List<Movie> retrieveAllByPriceBetween(BigDecimal price1, BigDecimal price2);

    //Write a JPQL query that returns all movie names
    @Query("select m.name from Movie m")
    List<String> retrieveAllMovieNames();

    // ------------------- Native QUERIES ------------------- //

    //Write a native query that returns a movie by name
    @Query(value = "select * from movie where name = ?1", nativeQuery = true)
    Optional<Movie> retrieveAllByName(String name);

    //Write a native query that return the list of movies in a specific range of prices
    @Query(value = "select * from movie where price between ?1 and ?2",nativeQuery = true)
    List<Movie> fetchAllByPriceBetween(BigDecimal price1,BigDecimal price2);

    //Write a native query to return all movies where duration exists in the range of duration
    @Query(value = "select * from movie where duration between ?1 and ?2",nativeQuery = true)
    List<Movie> retrieveAllByDurationBetween(Integer duration1, Integer duration2);

    //Write a native query to list the top 5 most expensive movies
    @Query(value = "select * from movie order by price limit 5",nativeQuery = true)
    List<Movie> retrieveTop5ExpensiveMovies();

}
