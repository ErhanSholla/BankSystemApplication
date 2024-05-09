package org.example.banksystemlinkplus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankDTO {
    private String name;

    private double transactionfee;
    private double transferamount;
    private double transactionflatfee;
    private double transactionpercentfee;
}
