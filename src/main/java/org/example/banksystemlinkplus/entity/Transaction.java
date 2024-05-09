package org.example.banksystemlinkplus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    @ManyToOne
    @JoinColumn(name = "sent_id")
    private Account senderAccount;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Account receiverAccount;

    private String reason;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
    public Transaction(Long id, double amount,Account senderAccount, String reason){
        this.id = id;
        this.amount = amount;
        this.reason = reason;
        this.senderAccount = senderAccount;
    }
}
