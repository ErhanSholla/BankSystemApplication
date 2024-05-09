package org.example.banksystemlinkplus.controller;

import org.example.banksystemlinkplus.dto.AccountDTO;
import org.example.banksystemlinkplus.entity.Account;
import org.example.banksystemlinkplus.exception.EntityNotFoundException;
import org.example.banksystemlinkplus.services.serviceimpl.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;

@RestController
@RequestMapping("/v1/api/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public void saveAccount(@RequestBody AccountDTO accountDTO){
        if (accountDTO.balance < 0){
            throw new InvalidParameterException("Amount should be more then 0");
        }
        accountService.saveAccount(accountDTO);
    }
    @PostMapping("/{accountId}/withdraw={amount}")
    public void withdrawMoney(@PathVariable Long accountId, @PathVariable double amount){
        accountService.withdrawMoney(accountId,amount);
    }
    @PostMapping("/{accountId}/deposit={amount}")
    public ResponseEntity<String> depositMoney(@PathVariable Long accountId, @PathVariable double amount){
        return accountService.depositMoney(accountId,amount);
    }
    @GetMapping("/balance/{id}")
    public ResponseEntity<String> checkBalance(@PathVariable Long id){
        try {
            double balance = accountService.checkBalance(id);
            return ResponseEntity.ok("Your balance is = " + balance);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }    }
}
