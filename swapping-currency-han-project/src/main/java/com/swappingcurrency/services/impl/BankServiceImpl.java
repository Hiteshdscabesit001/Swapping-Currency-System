package com.swappingcurrency.services.impl;

import com.swappingcurrency.dto.BankAccountDto;
import com.swappingcurrency.dto.WalletDto;
import com.swappingcurrency.models.BankAccount;
import com.swappingcurrency.models.User;
import com.swappingcurrency.models.Wallet;
import com.swappingcurrency.repositories.BankRepository;
import com.swappingcurrency.repositories.UserRepository;
import com.swappingcurrency.repositories.WalletRepository;
import com.swappingcurrency.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void createBankAccount(BankAccountDto bankAccountDto) {
        User usr = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(bankAccountDto.getAccountNumber());
        bankAccount.setBranchLocation(bankAccountDto.getBranchLocation());
        bankAccount.setAccountHolderName(usr.getFirstName()+" "+usr.getLastName());
        bankAccount.setEmail(usr.getEmail());
        bankAccount.setUsername(usr.getUsername());
        bankRepository.save(bankAccount);
    }

    @Override
    public void createWallet(WalletDto walletDto) {
        Wallet wallet = new Wallet();
        wallet.setAvailableCrypto(walletDto.getAvailableCrypto());
        wallet.setCryptoType(walletDto.getCryptoType());
        wallet.setCurrentPrice(walletDto.getCurrentPrice());
        wallet.setLockedFund(walletDto.getLockedFund());
        User usr = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        wallet.setUsername(usr.getUsername());
        walletRepository.save(wallet);
    }

    @Override
    public Wallet getWalletInfo() {
        User usr = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return walletRepository.findByUsername(usr.getUsername());
    }
}
