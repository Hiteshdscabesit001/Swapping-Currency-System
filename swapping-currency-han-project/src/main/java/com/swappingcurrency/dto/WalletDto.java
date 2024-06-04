package com.swappingcurrency.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WalletDto {

    private String CryptoType;
    private String currentPrice;
    private String availableCrypto;
    private String lockedFund;
}
