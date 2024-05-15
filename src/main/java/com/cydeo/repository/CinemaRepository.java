package com.cydeo.repository;

import com.cydeo.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to get a cinema with a specific name
    Optional<Cinema> getAllByName(String name);

    //Write a derived query to read sorted the top 3 cinemas that contains a specific sponsored name
    List<Cinema> getTop3BySponsoredNameContainsOrderBySponsoredName(String sponsoredName);

    //Write a derived query to list all cinemas in a specific country
    List<Cinema> getAllByLocation_Country(String country);

    //Write a derived query to list all cinemas with a specific name or sponsored name
    List<Cinema> getAllByNameOrSponsoredName(String name, String sponsoredName);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query to read the cinema name with a specific id
    @Query("select c.name from Cinema c where c.id=?1")
    String fetchCinemaNameById(Long Id);

    // ------------------- Native QUERIES ------------------- //

    //Write a native query to read all cinemas by location country
    @Query(value = "select * from cinema c left join location l where l.country=?1",nativeQuery = true)
    List<Cinema> fetchCinemasByCountry(String country);
    //Write a native query to read all cinemas by name or sponsored name contains a specific pattern
    @Query(value = "select * from cinema where name ilike concat('%',?1,'%') or sponsored_name ilike concat('%',?1,'%')",nativeQuery = true)
    List<Cinema> fetchAllCinemaNameAndSponsoredNameContains(String pattern);
    //Write a native query to sort all cinemas by name
    @Query(value = "select * from cinema order by name",nativeQuery = true)
    List<Cinema> sortAllCinemasByName();

    //Write a native query to distinct all cinemas by sponsored name
    @Query(value = "select * from cinema group by sponsored_name",nativeQuery = true)
    List<Cinema> fetchDistinctCinemasGroupBySponsoredName();

}
