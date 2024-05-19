package com.cydeo.repository;

import com.cydeo.entity.Movie;
import com.cydeo.entity.Ticket;
import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to count how many tickets a user bought
    Integer countAllByUser_Id(Long userId);

    //Write a derived query to list all tickets by specific email
    List<Ticket> getAllByUser_Email(String email);

    //Write a derived query to count how many tickets are sold for a specific movie
    Integer countAllByMovieCinema_Movie(Movie movie);

    //Write a derived query to list all tickets between a range of dates
    List<Ticket> getAllByDateTimeBetween(LocalDateTime dateTime1, LocalDateTime dateTime2);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query that returns all tickets are bought from a specific user
    @Query("select t from Ticket t where t.user=?1")
    List<Ticket> retrieveAllByUser(User user);

    //Write a JPQL query that returns all tickets between a range of dates
    @Query("select t from Ticket t where t.dateTime between ?1 and ?2")
    List<Ticket> retrieveAllByDatesBetween(LocalDateTime dateTime1,LocalDateTime dateTime2);

    // ------------------- Native QUERIES ------------------- //

    //Write a native query to count the number of tickets a user bought
    @Query(value = "select count(*) from ticket where user_account_id=?1",nativeQuery = true)
    Integer fetchCountByUserId(Long userId);

    //Write a native query to count the number of tickets a user bought in a specific range of dates
    @Query(value = "select count(*) from ticket where user_account_id=?1 and date_time between ?2 and ?3",nativeQuery = true)
    Integer fetchCountOfTicketsByUserIdAndDateRange(Long userId, LocalDateTime dateTime1,LocalDateTime dateTime2);

    //Write a native query to distinct all tickets by movie name  //select? by movie name
    @Query(value = "select * from ticket t left join movie_cinema mc on t.movie_cinema_id=mc.id left join movie m on mc.movie_id=m.id where m.name like '%'||?1||'%'",nativeQuery = true)
    Optional<Ticket> retrieveAllByMovieName(String movieName);

    //Write a native query to find all tickets by user email
    @Query(value = "select * from ticket t left join user_account u on t.user_account_id=u.id where u.email=?1",nativeQuery = true)
    List<Ticket> retrieveByUserEmail(String email);

    //Write a native query that returns all tickets
    @Query(value = "select * from ticket",nativeQuery = true)
    List<Ticket> retrieveAll();

    //Write a native query to list all tickets where a specific value should be containable in the username or account name or movie name
    @Query(value = "select t.* from ticket t " +
            "left join movie_cinema mc on t.movie_cinema_id=mc.id " +
            "left join movie m on mc.movie_id=m.id " +
            "left join user_account u on t.user_account_id=u.id " +
            "left join account_details a on u.account_details_id=a.id " +
            "where u.username ilike concat('%',?1,'%') " +
            "or a.name ilike concat('%',?1,'%') " +
            "or m.name ilike concat('%',?1,'%')",nativeQuery = true)
    List<Ticket> retrieveAllByUsernameOrAccountNameOrMovieNameLike(String pattern);


}
