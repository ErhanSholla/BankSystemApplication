package org.example.banksystemlinkplus.repository;

import org.example.banksystemlinkplus.dto.BankDTO;
import org.example.banksystemlinkplus.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank,Long> {
    Bank findByName(String name);
}
