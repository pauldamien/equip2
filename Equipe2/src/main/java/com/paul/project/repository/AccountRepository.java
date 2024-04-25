package com.paul.project.repository;

import com.paul.project.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository< Account, Integer> {
    Optional<Account> findByIban(String iban);
}
