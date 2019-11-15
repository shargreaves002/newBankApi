package com.newBankApi.newBankApi.controllers;

import com.newBankApi.newBankApi.models.*;
import com.newBankApi.newBankApi.services.AccountService;
import com.newBankApi.newBankApi.services.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class WithdrawalController {

    @Autowired
    private WithdrawalService withdrawalService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/withdrawals/{id}")
    public ResponseEntity<?> getWithdrawalById(@PathVariable long id){
        Response response = new Response();
        HttpStatus statusCode;
        Optional<Withdrawal> d = withdrawalService.findById(id);
        if (d.isPresent()) {
            response.setCode(200);
            response.setData(new ArrayList<>(Collections.singleton(d)));
            statusCode = HttpStatus.OK;
        } else {
            response.setCode(404);
            response.setMessage("Withdrawal with ID " + id + " not found.");
            statusCode = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @GetMapping("/accounts/{id}/withdrawals")
    public ResponseEntity<?> getWithdrawalsForAccount(@PathVariable Long id){
        Response response = new Response();
        HttpStatus statusCode;
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()) {
            response.setCode(201);
            List<Withdrawal> d = withdrawalService.findAllByAccountId(id);
            response.setData(d);
            statusCode = HttpStatus.OK;
        } else {
            response.setCode(404);
            response.setMessage("Account with ID " + id + " not found.");
            statusCode = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @PostMapping("/accounts/{id}/withdrawals")
    public ResponseEntity<?> createWithdrawal(@RequestBody Withdrawal withdrawal, @PathVariable Long id) {
        Response response = new Response();
        HttpStatus statusCode;
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()) {
            response.setCode(201);
            Withdrawal createdWithdrawal = withdrawalService.createWithdrawal(withdrawal, id);
            response.setData(new ArrayList<>(Collections.singleton(createdWithdrawal)));
            statusCode = HttpStatus.CREATED;
        } else {
            response.setCode(404);
            response.setMessage("Account with ID " + id + " not found.");
            statusCode = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @PutMapping("/withdrawals/{id}")
    public ResponseEntity<?> updateWithdrawal(@RequestBody Withdrawal withdrawal, @PathVariable Long id) {
        Response response = new Response();
        HttpStatus statusCode;
        if (withdrawalService.existsById(id)) {
            Withdrawal updatedWithdrawal = withdrawalService.updateWithdrawal(withdrawal, id);
            response.setCode(202);
            response.setData(Collections.singletonList(updatedWithdrawal));
            statusCode = HttpStatus.ACCEPTED;
        } else {
            response.setMessage("Withdrawal with ID " + id + " not found.");
            response.setCode(404);
            statusCode = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @DeleteMapping("/withdrawals/{id}")
    public ResponseEntity<?> deleteWithdrawal(@PathVariable Long id) {
        Response response = new Response();
        HttpStatus statusCode;
        if (withdrawalService.existsById(id)) {
            withdrawalService.deleteById(id);
            statusCode = HttpStatus.NO_CONTENT;
        } else {
            response.setMessage("Withdrawal with ID " + id + " not found.");
            response.setCode(404);
            statusCode = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(response, statusCode);
    }
}
