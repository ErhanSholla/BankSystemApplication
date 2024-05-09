package org.example.banksystemlinkplus.repository;

import org.example.banksystemlinkplus.dto.TransactionQueryDTO;
import org.example.banksystemlinkplus.entity.Account;
import org.example.banksystemlinkplus.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    @Query("SELECT DISTINCT new org.example.banksystemlinkplus.dto.TransactionQueryDTO(" +
            "t.amount, t.senderAccount.name, t.receiverAccount.name, t.reason, t.bank.name) " +
            "FROM Transaction t " +
            "WHERE t.senderAccount.id = :accountId OR t.receiverAccount.id = :accountId")
    List<TransactionQueryDTO> findTransactionsForAccount(Long accountId);
}
