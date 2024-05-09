package org.example.banksystemlinkplus.services;

import org.example.banksystemlinkplus.dto.AccountDTO;
import org.example.banksystemlinkplus.entity.Account;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    ResponseEntity<String> saveAccount(AccountDTO accountDTO);
    ResponseEntity<String> withdrawMoney(Long accountId,double amount);
    ResponseEntity<String> depositMoney(Long accountId, double amount);

    double checkBalance(Long accountId);

}
