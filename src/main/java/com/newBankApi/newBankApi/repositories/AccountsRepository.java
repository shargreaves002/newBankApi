package com.newBankApi.newBankApi.repositories;

import com.newBankApi.newBankApi.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {
}
