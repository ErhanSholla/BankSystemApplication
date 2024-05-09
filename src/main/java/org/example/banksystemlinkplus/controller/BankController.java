package org.example.banksystemlinkplus.controller;

import org.example.banksystemlinkplus.dto.BankDTO;
import org.example.banksystemlinkplus.entity.Bank;
import org.example.banksystemlinkplus.exception.EntityAlreadyExistsException;
import org.example.banksystemlinkplus.exception.EntityNotFoundException;
import org.example.banksystemlinkplus.services.serviceimpl.BankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.List;

@RestController
@RequestMapping("/v1/api/bank")
public class BankController {
    public final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public List<BankDTO> getAllBanks() {
        return bankService.getAllBanks();
    }
    @PostMapping("/banks")
    public ResponseEntity<String> createNewBank(@RequestBody Bank bank) {

        try {
            double percentageFee = bank.getTransactionpercentfee();
            if (percentageFee < 0 || percentageFee > 100) {
                throw new InvalidParameterException("Percentage fee must be between 0 and 100 inclusive.");
            }
            double flatFee = bank.getTransactionflatfee();
            if (flatFee < 0) {
                throw new InvalidParameterException("Flat transaction fee must be a non-negative value.");
            }
            bankService.createBank(bank);
            return ResponseEntity.status(HttpStatus.CREATED).body("Bank created successfully");
        } catch (EntityAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/totaltransactionfee/{id}")
    public ResponseEntity<String> checkTotalTransactionFeeAmount(@PathVariable Long id) {
        try {
            double amount = bankService.checkTotalTransactionFeeAmount(id);
            return ResponseEntity.ok("Total Transaction Fee Amounts is + " + amount);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/totaltransferamount/{id}")
    public ResponseEntity<String> checkBankTotalTransferAmount(@PathVariable Long id) {
        try {
            double fee = bankService.checkTotalTransactionFeeAmount(id);
            return ResponseEntity.ok("Bank Total Transfer Amount " + fee);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
