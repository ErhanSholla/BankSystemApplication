package org.example.banksystemlinkplus.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
    public String name;
    public double balance;
    private String bankName;
}
