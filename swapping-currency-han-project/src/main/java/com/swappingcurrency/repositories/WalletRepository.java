package com.swappingcurrency.repositories;

import com.swappingcurrency.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByUsername(String username);
}
