package org.example.banksystemlinkplus.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.banksystemlinkplus.entity.Account;

@Getter
@Setter
public class TransactionDTO {
    public double amount;
    public Long sentId;
    public Long receiverId;
    public String reason;
    public Long bankId;

}
