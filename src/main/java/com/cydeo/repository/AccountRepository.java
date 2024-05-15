package com.cydeo.repository;

import com.cydeo.entity.Account;
import com.cydeo.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to list all accounts with a specific country or state
    List<Account> getByCountryAndState(String country, String state);

    //Write a derived query to list all accounts with age lower than or equal to a specific value
    List<Account> getByAgeLessThanEqual(Integer age);

    //Write a derived query to list all accounts with a specific role
    List<Account> getByRole(UserRole userRole);

    //Write a derived query to list all accounts between a range of ages
    List<Account> getByAgeBetween(Integer age1, Integer age2);

    //Write a derived query to list all accounts where the beginning of the address contains the keyword
    List<Account> getByAddressStartingWith(String address);

    //Write a derived query to sort the list of accounts with age
    List<Account> getAccountByOrderByAge();

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query that returns all accounts
    @Query("select a from Account a")
    List<Account> retrieveAll();
    //Write a JPQL query to list all admin accounts
    @Query("select a from Account a where a.role='ADMIN'")
    List<Account> retrieveAdminAccounts();
    //Write a JPQL query to sort all accounts with age
    @Query("select a from Account a order by a.age")
    List<Account> sortByAge();
    // ------------------- Native QUERIES ------------------- //

    //Write a native query to read all accounts with an age lower than a specific value
    @Query(value = "select * from account_details where age<?1",nativeQuery = true)
    List<Account> retrieveAccountAgeLowerThan(Integer age);
    //Write a native query to read all accounts that a specific value can be containable in the name, address, country, state, city
    @Query(value = "select * from account_details where name ilike concat('%',?1,'%') or address ilike concat('%',?1,'%') or country ilike concat('%',?1,'%') or state ilike concat('%',?1,'%') or city ilike concat('%',?1,'%')",nativeQuery = true)
    List<Account> retrieveAccountWithFieldsLike(String pattern);

    // concat('%',?1,'%') or '%'||?1||'%'  or '%?1%'


    //Write a native query to read all accounts with an age higher than a specific value
    @Query(value = "select * from account_details where age>?1",nativeQuery = true)
    List<Account> retrieveAccountAgeHigherThan(Integer age);
}
