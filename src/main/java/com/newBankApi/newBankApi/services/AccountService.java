package com.newBankApi.newBankApi.services;

import com.newBankApi.newBankApi.models.Account;
import com.newBankApi.newBankApi.repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountsRepository accountsRepository;

    public Optional<Account> findById(Long id) {
        return accountsRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return accountsRepository.existsById(id);
    }

    public List<Account> findAllByCustomerId(Long id) {
        return accountsRepository.findAllByCustomerId(id);
    }
}
