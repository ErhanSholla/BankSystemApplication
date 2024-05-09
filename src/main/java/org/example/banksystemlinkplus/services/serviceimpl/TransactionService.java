package org.example.banksystemlinkplus.services.serviceimpl;

import org.example.banksystemlinkplus.dto.TransactionDTO;
import org.example.banksystemlinkplus.dto.TransactionQueryDTO;
import org.example.banksystemlinkplus.entity.Account;
import org.example.banksystemlinkplus.entity.Bank;
import org.example.banksystemlinkplus.entity.Transaction;
import org.example.banksystemlinkplus.exception.EntityNotFoundException;
import org.example.banksystemlinkplus.exception.InvalidParameterException;
import org.example.banksystemlinkplus.repository.AccountRepository;
import org.example.banksystemlinkplus.repository.BankRepository;
import org.example.banksystemlinkplus.repository.TransactionRepository;
import org.example.banksystemlinkplus.services.ITransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {
    private final BankRepository bankRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(BankRepository bankRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.bankRepository = bankRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }


    @Override
    public List<TransactionQueryDTO> checkTransactionOfAccount(Long id) {
        return transactionRepository.findTransactionsForAccount(id);
    }

    @Override
    public void saveTransaction(TransactionDTO transactionDTO) {
        Optional<Account> senderAccountOptional = accountRepository.findById(transactionDTO.getSentId());
        Optional<Account> receiverAccountOptional = accountRepository.findById(transactionDTO.getReceiverId());

        if (senderAccountOptional.isEmpty() || receiverAccountOptional.isEmpty()) {
            throw new EntityNotFoundException("Sender account, receiver account, or bank not found.");
        }
        Account senderAccount = senderAccountOptional.get();
        Account receiverAccount = receiverAccountOptional.get();
        Bank bank = senderAccount.getBank();
        double flatFee = bank.getTransactionflatfee();
        double percentFee = (transactionDTO.getAmount() / 100.0) * bank.getTransactionpercentfee();
        double totalFee = flatFee + percentFee;
        if (transactionDTO.getAmount() + totalFee > senderAccount.getBalance()) {
            throw new InvalidParameterException("Insufficient funds in the origin account.");
        }
        if (transactionDTO.getAmount() > bank.getTransferamount()) {
            throw new InvalidParameterException("Transaction amount exceeds bank's transfer limit.");
        }
        senderAccount.setBalance(senderAccount.getBalance() - (transactionDTO.getAmount() + totalFee));
        receiverAccount.setBalance(receiverAccount.getBalance() + transactionDTO.getAmount());
        Transaction transaction = new Transaction();
        transaction.setBank(bank);
        transaction.setSenderAccount(senderAccount);
        transaction.setReceiverAccount(receiverAccount);
        transaction.setReason(transactionDTO.getReason());
        transaction.setAmount(transactionDTO.getAmount());
        transactionRepository.save(transaction);
        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);
    }

}


