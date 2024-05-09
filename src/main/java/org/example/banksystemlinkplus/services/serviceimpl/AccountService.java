package org.example.banksystemlinkplus.services.serviceimpl;

import org.example.banksystemlinkplus.dto.AccountDTO;
import org.example.banksystemlinkplus.entity.Account;
import org.example.banksystemlinkplus.entity.Bank;
import org.example.banksystemlinkplus.exception.EntityAlreadyExistsException;
import org.example.banksystemlinkplus.exception.EntityNotFoundException;
import org.example.banksystemlinkplus.exception.InvalidParameterException;
import org.example.banksystemlinkplus.repository.AccountRepository;
import org.example.banksystemlinkplus.repository.BankRepository;
import org.example.banksystemlinkplus.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IUserService {
    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;

    public AccountService(AccountRepository accountRepository, BankRepository bankRepository) {
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
    }

    public Account getAccountById(Long accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        return optionalAccount.orElse(null);
    }

    @Override
    public ResponseEntity<String> saveAccount(AccountDTO accountDTO) {
        if (accountDTO.getName() == null || accountDTO.getBalance() == 0 || accountDTO.getBankName().isEmpty()) {
            throw new InvalidParameterException("Name or Account Balance or Bank nameshould be not null");
        } else if (bankRepository.findByName(accountDTO.getBankName()) == null) {
            throw new EntityNotFoundException("Bank Not Found");
        } else {
            Account account = new Account();
            account.setName(accountDTO.getName());
            account.setBalance(accountDTO.getBalance());
            Bank bank = bankRepository.findByName(accountDTO.getBankName());
            account.setBank(bank);
            accountRepository.save(account);
        }
        return ResponseEntity.ok("Congratulations your account has been created");
    }

    @Override
    public ResponseEntity<String> withdrawMoney(Long accountId, double amount) {
        Optional<Account> account1 = accountRepository.findById(accountId);
        if (account1.isEmpty()) {
            throw new EntityNotFoundException("Check id maybe you typed wrong id");
        } else if (amount > account1.get().balance) {
            throw new InvalidParameterException("Withdrawal amount exceeds account balance.");
        } else if (amount <= 0) {
            throw new InvalidParameterException("Withdrawal amount must be greater than zero");
        } else {
            account1.get().setBalance(checkBalance(accountId) - amount);
            accountRepository.save(account1.get());
        }
        return ResponseEntity.ok(amount + " amount have been withdrawn from " +account1.get().getName());
    }

    @Override
    public ResponseEntity<String> depositMoney(Long accountId, double amount) {
        Optional<Account> account1 = accountRepository.findById(accountId);
        if (account1.isEmpty()) {
            throw new EntityNotFoundException("Check id maybe you typed wrong id");
        } else if (amount <= 0) {
            throw new InvalidParameterException("Deposit amount must be greater than zero");
        } else {
            account1.get().setBalance(checkBalance(accountId) - amount);
            accountRepository.save(account1.get());
        }
        return ResponseEntity.ok(amount + " Successfully added to " + account1.get().getName());
    }

    @Override
    public double checkBalance(Long accountId) {
        Optional<Account> account1 = accountRepository.findById(accountId);
        return account1.get().getBalance();
    }
}
