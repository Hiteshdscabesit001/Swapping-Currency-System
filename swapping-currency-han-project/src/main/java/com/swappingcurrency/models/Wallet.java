package com.swappingcurrency.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long walletId;
    private String username;
    private String CryptoType;
    private String currentPrice;
    private String availableCrypto;
    private String lockedFund;
}
