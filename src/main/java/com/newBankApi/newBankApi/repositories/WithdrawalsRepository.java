package com.newBankApi.newBankApi.repositories;

import com.newBankApi.newBankApi.models.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawalsRepository extends JpaRepository<Withdrawal, Long> {

    List<Withdrawal> findAllByAccountId(Long accountId);
}
