package com.paul.project.repository;

import com.paul.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Integer>{

    Optional<User> findByEmail(String email);

    @Query("from User where firstname = :firstname")
    List<User> searchFirstname(String firstname);

    @Query("from User where firstname = '%firstname%'")
    List<User> searchByFirstnameContaining(String firstname);
}
