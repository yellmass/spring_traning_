package com.cydeo.repository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to read a user with an email?
    List<User> getAllByEmail(String email);

    //Write a derived query to read a user with a username?
    List<User> getAllByUsername(String username);

    //Write a derived query to list all users that contain a specific name?
    List<User> getAllByAccount_NameContaining(String accountName); // is it true?

    //Write a derived query to list all users that contain a specific name in the ignore case mode?
    List<User> getAllByAccount_NameContainingIgnoreCase(String accountName); // ???

    //Write a derived query to list all users with an age greater than a specified age?
    List<User> getAllByAccount_AgeGreaterThan(Integer age);

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query that returns a user read by email?
    @Query("select u from User u where u.email=?1")
    Optional<User> retrieveByEmail(String email);

    //Write a JPQL query that returns a user read by username?
    @Query("select u from User u where u.username=?1")
    Optional<User> retrieveByUsername(String username);

    //Write a JPQL query that returns all users?
    @Query("select u from User u")
    Optional<User> retrieveAll();

    // ------------------- Native QUERIES ------------------- //

    //Write a native query that returns all users that contain a specific name?
    @Query(value = "select * from user_account u left join account_details a on u.account_datails_id=a.id where a.name ilike concat('%',?1,'%')",nativeQuery = true)
    List<User> retrieveAllByAccountNameContaining(String accountName);

    //Write a native query that returns all users?
    @Query(value = "select * from user_account",nativeQuery = true)
    List<User> fetchAll();

    //Write a native query that returns all users in the range of ages?
    @Query(value = "select * from user_account u left join account_details a on u.account_datails_id=a.id where a.age in (?1)",nativeQuery = true)
    List<User> retrieveAllAgesIn(List<Integer> ages);


    //Write a native query to read a user by email?
    @Query(value = "select * from user_account where email = ?1",nativeQuery = true)
    List<User> fetchByEmail(String email);

}
