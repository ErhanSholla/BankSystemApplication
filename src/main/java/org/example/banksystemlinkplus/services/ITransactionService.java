package org.example.banksystemlinkplus.services;

import org.example.banksystemlinkplus.dto.TransactionDTO;
import org.example.banksystemlinkplus.dto.TransactionQueryDTO;
import org.example.banksystemlinkplus.entity.Transaction;

import java.util.List;

public interface ITransactionService {
    List<TransactionQueryDTO> checkTransactionOfAccount(Long id);
    void saveTransaction(TransactionDTO transactionDTO);
}
