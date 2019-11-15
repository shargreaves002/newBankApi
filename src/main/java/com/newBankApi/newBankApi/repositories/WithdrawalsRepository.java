package com.newBankApi.newBankApi.repositories;

import com.newBankApi.newBankApi.models.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawalsRepository extends JpaRepository<Withdrawal, Long> {
}
