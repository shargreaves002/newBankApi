package com.newBankApi.newBankApi.controllers;

import com.newBankApi.newBankApi.models.Account;
import com.newBankApi.newBankApi.models.Deposit;
import com.newBankApi.newBankApi.models.Response;
import com.newBankApi.newBankApi.services.AccountService;
import com.newBankApi.newBankApi.services.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class DepositController {

    @Autowired
    private DepositService depositService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/deposits/{id}")
    public ResponseEntity<?> getDepositById(@PathVariable long id){
        Response response = new Response();
        HttpStatus statusCode;
        Optional<Deposit> d = depositService.findById(id);
        if (d.isPresent()) {
            response.setCode(200);
            response.setData(new ArrayList<>(Collections.singleton(d)));
            statusCode = HttpStatus.OK;
        } else {
            response.setCode(404);
            response.setMessage("Deposit with ID " + id + " not found.");
            statusCode = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @GetMapping("/accounts/{id}/deposits")
    public ResponseEntity<?> getDepositsForAccount(@PathVariable Long id){
        Response response = new Response();
        HttpStatus statusCode;
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()) {
            response.setCode(201);
            List<Deposit> d = depositService.findAllByAccountId(id);
            response.setData(d);
            statusCode = HttpStatus.OK;
        } else {
            response.setCode(404);
            response.setMessage("Account with ID " + id + " not found.");
            statusCode = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @PostMapping("/accounts/{id}/deposits")
    public ResponseEntity<?> createDeposit(@RequestBody Deposit deposit, @PathVariable Long id) {
        Response response = new Response();
        HttpStatus statusCode;
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()) {
            response.setCode(201);
            Deposit createdDeposit = depositService.createDeposit(deposit, id);
            response.setData(new ArrayList<>(Collections.singleton(createdDeposit)));
            statusCode = HttpStatus.CREATED;
        } else {
            response.setCode(404);
            response.setMessage("Account with ID " + id + " not found.");
            statusCode = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @PutMapping("/deposits/{id}")
    public ResponseEntity<?> updateDeposit(@RequestBody Deposit deposit, @PathVariable Long id) {
        Response response = new Response();
        HttpStatus statusCode;
        if (depositService.existsById(id)) {
            Deposit updatedDeposit = depositService.updateDeposit(deposit, id);
            response.setCode(202);
            response.setData(Collections.singletonList(updatedDeposit));
            statusCode = HttpStatus.ACCEPTED;
        } else {
            response.setMessage("Deposit with ID " + id + " not found.");
            response.setCode(404);
            statusCode = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @DeleteMapping("/deposits/{id}")
    public ResponseEntity<?> deleteDeposit(@PathVariable Long id) {
        Response response = new Response();
        HttpStatus statusCode;
        if (depositService.existsById(id)) {
            depositService.deleteById(id);
            statusCode = HttpStatus.NO_CONTENT;
        } else {
            response.setMessage("Deposit with ID " + id + " not found.");
            response.setCode(404);
            statusCode = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(response, statusCode);
    }
}
