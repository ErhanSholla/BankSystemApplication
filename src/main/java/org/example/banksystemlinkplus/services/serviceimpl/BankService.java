package org.example.banksystemlinkplus.services.serviceimpl;

import org.example.banksystemlinkplus.dto.BankDTO;
import org.example.banksystemlinkplus.entity.Account;
import org.example.banksystemlinkplus.entity.Bank;
import org.example.banksystemlinkplus.exception.EntityAlreadyExistsException;
import org.example.banksystemlinkplus.exception.EntityNotFoundException;
import org.example.banksystemlinkplus.repository.AccountRepository;
import org.example.banksystemlinkplus.repository.BankRepository;
import org.example.banksystemlinkplus.services.IBankService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankService implements IBankService {
    private final BankRepository bankRepository;
    private final AccountRepository accountRepository;

    public BankService(BankRepository bankRepository, AccountRepository accountRepository) {
        this.bankRepository = bankRepository;
        this.accountRepository = accountRepository;
    }


    @Override
    public void createBank(Bank bank) {
        Bank bank1 = bankRepository.findByName(bank.getName());

        if (bank1 == null) {
            bankRepository.save(bank);
        } else {
            throw new EntityAlreadyExistsException("Bank already Exist");
        }
    }

    @Override
    public List<BankDTO> getAllBanks() {
        List<Bank> bank = bankRepository.findAll();
        return bank.stream().map(bank1 -> new BankDTO(bank1.getName(), bank1.getTransactionfee(),
                bank1.getTransferamount(), bank1.getTransactionflatfee(), bank1.getTransactionpercentfee())).collect(Collectors.toList());

    }

    @Override
    public double checkTotalTransactionFeeAmount(Long bankId) {
        Bank bank = bankRepository.findById(bankId).get();
        if (bank == null) {
            throw new EntityNotFoundException("Bank Not Found");
        } else {
            return bank.getTransferamount();
        }
    }

    @Override
    public double checkBankTotalTransferAmount(Long bankId) {
        Bank bank = bankRepository.findById(bankId).get();
        if (bank == null) {
            throw new EntityNotFoundException("Bank Not Found");
        } else {
            return bank.getTransactionfee();
        }
    }
}
