package org.example.banksystemlinkplus.controller;

import org.example.banksystemlinkplus.dto.TransactionDTO;
import org.example.banksystemlinkplus.dto.TransactionQueryDTO;
import org.example.banksystemlinkplus.entity.Account;
import org.example.banksystemlinkplus.entity.Transaction;
import org.example.banksystemlinkplus.services.serviceimpl.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public List<TransactionQueryDTO> getTransactionById(@PathVariable Long id){
        return transactionService.checkTransactionOfAccount(id);
    }
    @PostMapping("/save")
    public void saveTransaction(@RequestBody TransactionDTO transactionDTO){
        transactionService.saveTransaction(transactionDTO);
    }
}
