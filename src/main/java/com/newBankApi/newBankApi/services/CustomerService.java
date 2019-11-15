package com.newBankApi.newBankApi.services;

import com.newBankApi.newBankApi.models.Customer;
import com.newBankApi.newBankApi.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomersRepository customersRepository;

    public boolean existsById(Long id) {
        return customersRepository.existsById(id);
    }

    public Optional<Customer> findById(Long id) {
        return customersRepository.findById(id);
    }
}
