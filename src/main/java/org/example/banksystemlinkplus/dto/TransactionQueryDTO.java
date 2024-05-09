package org.example.banksystemlinkplus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionQueryDTO {
    public double amount;
    public String sentId;
    public String receiverId;
    public String reason;
    public String bankName;
}
