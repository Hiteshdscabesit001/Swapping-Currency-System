package com.swappingcurrency.services;

import com.swappingcurrency.dto.BankAccountDto;
import com.swappingcurrency.dto.WalletDto;
import com.swappingcurrency.models.BankAccount;
import com.swappingcurrency.models.Wallet;

public interface BankService {
    void createBankAccount(BankAccountDto bankAccountDto);

    void createWallet(WalletDto walletDto);

    Wallet getWalletInfo();
}
