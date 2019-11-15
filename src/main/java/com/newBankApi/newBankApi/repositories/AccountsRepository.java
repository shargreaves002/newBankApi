package com.newBankApi.newBankApi.repositories;

import com.newBankApi.newBankApi.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByCustomerId(Long id);
}
