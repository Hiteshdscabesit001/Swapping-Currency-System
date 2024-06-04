package com.swappingcurrency.config;

import lombok.Getter;

@Getter
public enum CloudinaryConfig {

    CLOUD_NAME("doouvrykj"),
    API_KEY("494261567998531"),
    API_SECRET("1mj-Z2f02iyIVkaqLINuJHXD9Ec");

    private final String code;

    CloudinaryConfig(String code) {
        this.code = code;
    }
}
