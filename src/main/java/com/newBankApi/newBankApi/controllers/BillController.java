package com.newBankApi.newBankApi.controllers;

import com.newBankApi.newBankApi.models.Account;
import com.newBankApi.newBankApi.models.Bill;
import com.newBankApi.newBankApi.models.Response;
import com.newBankApi.newBankApi.services.AccountService;
import com.newBankApi.newBankApi.services.BillService;
import com.newBankApi.newBankApi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/accounts/{accountID}/bills")
    public ResponseEntity<?> getAllBillsForAcc(@PathVariable("accountID") Long id) {
        Response response = new Response();
        HttpStatus statusCode;
        if (!accountService.existsById(id)) {
            response.setCode(404);
            response.setMessage("Account with ID " + id + " not found.");
            statusCode = HttpStatus.NOT_FOUND;
        }else{
            List<Bill> bills = billService.findAllByAccountId(id);
            response.setCode(200);
            response.setData(bills);
            statusCode = HttpStatus.OK;
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @GetMapping("/bills/{billId}")
    public ResponseEntity<?> getBillById(@PathVariable("billId") Long id) {
        HttpStatus statusCode;
        Response response = new Response();
        Optional<Bill> bill = billService.findById(id);
        if (!bill.isPresent()) {
            response.setCode(404);
            response.setMessage("Bill with ID " + id + " not found.");
            statusCode = HttpStatus.NOT_FOUND;
        } else {
            response.setCode(200);
            response.setData(new ArrayList<>(Collections.singleton(bill)));
            statusCode = HttpStatus.OK;
        }
        return new ResponseEntity<>(response, statusCode);
    }



    @GetMapping("/customers/{customerId}/bills")
    public ResponseEntity<?> getAllBillsForCus(@PathVariable("customerId") Long id) {
        Response response = new Response();
        HttpStatus statusCode;
        if (!customerService.existsById(id)) {
            response.setCode(404);
            response.setMessage("Customer with ID " + id + " not found.");
            statusCode = HttpStatus.NOT_FOUND;
        }else{
            List<Account> accounts = accountService.findAllByCustomerId(id);
            List<Bill> bills = new ArrayList<>();
            for (Account i : accounts) {
                bills.addAll(billService.findAllByAccountId(i.getId()));
            }
            response.setCode(200);
            response.setData(bills);
            statusCode = HttpStatus.OK;
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> createBill(@RequestBody Bill bill, @PathVariable("accountId") Long id) {
        Response response = new Response();
        HttpStatus statusCode;
        if (!accountService.existsById(id)) {
            response.setCode(404);
            response.setMessage("Account with ID " + id + " not found.");
            statusCode = HttpStatus.NOT_FOUND;
        }else{
            response.setCode(201);
            response.setData(new ArrayList<>(Collections.singleton(bill)));
            billService.createBill(bill);
            statusCode = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(response, statusCode);
    }


    @PutMapping("/bills/{billId}")
    public ResponseEntity<?> updateBill(@PathVariable("billId") Long id, @RequestBody Bill bill) {
        Response response = new Response();
        HttpStatus statusCode;
        if (!billService.existsById(id)) {
            response.setCode(404);
            response.setMessage("Bill with ID " + id + " not found.");
            statusCode = HttpStatus.NOT_FOUND;
        }else{
            Bill updatedBill = billService.updateBill(id, bill);
            response.setCode(202);
            response.setData(Collections.singletonList(updatedBill));
            statusCode = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<?> deleteBill(@PathVariable("billId") Long id) {
        Response response = new Response();
        HttpStatus statusCode;
        if (!billService.existsById(id)) {
            response.setCode(404);
            response.setMessage("Bill with ID " + id + " not found.");
            statusCode = HttpStatus.NOT_FOUND;
        }else{
            billService.deleteBillById(id);
            statusCode = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity<>(response, statusCode);
    }
}
