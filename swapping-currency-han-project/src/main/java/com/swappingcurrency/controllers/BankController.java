package com.swappingcurrency.controllers;

import com.swappingcurrency.dto.BankAccountDto;
import com.swappingcurrency.dto.ResponseDto;
import com.swappingcurrency.dto.WalletDto;
import com.swappingcurrency.models.Wallet;
import com.swappingcurrency.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("createBankAccount")
    public ResponseEntity<ResponseDto> createBankAccount(@RequestBody BankAccountDto bankAccount){
        bankService.createBankAccount(bankAccount);
        return new ResponseEntity<>(new ResponseDto(HttpStatus.CREATED, "Bank account is created"), HttpStatus.CREATED);
    }

    @PostMapping("createWallet")
    public ResponseEntity<ResponseDto> createWallet(@RequestBody WalletDto walletDto){
        bankService.createWallet(walletDto);
        return new ResponseEntity<>(new ResponseDto(HttpStatus.CREATED, "Wallet is created"), HttpStatus.CREATED);
    }

    @GetMapping("getWalletInfo")
    public ResponseEntity<Wallet> getWalletInfo(){
        Wallet wallet = bankService.getWalletInfo();
        return new ResponseEntity<>(wallet, HttpStatus.FOUND);
    }
}
