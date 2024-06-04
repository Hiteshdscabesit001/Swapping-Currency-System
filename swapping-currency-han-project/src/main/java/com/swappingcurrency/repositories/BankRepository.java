package com.swappingcurrency.repositories;

import com.swappingcurrency.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<BankAccount, String> {
}
