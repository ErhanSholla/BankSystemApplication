package org.example.banksystemlinkplus.services;

import org.example.banksystemlinkplus.dto.BankDTO;
import org.example.banksystemlinkplus.entity.Account;
import org.example.banksystemlinkplus.entity.Bank;

import java.util.List;

public interface IBankService {
    void createBank(Bank bank);
    List<BankDTO> getAllBanks();
    double checkTotalTransactionFeeAmount(Long bankId);
    double checkBankTotalTransferAmount(Long bankId);
}
