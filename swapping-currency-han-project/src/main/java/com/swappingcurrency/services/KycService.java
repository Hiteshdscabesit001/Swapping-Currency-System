package com.swappingcurrency.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface KycService {
    void uploadPassport(MultipartFile frontSide, MultipartFile backSide) throws IOException;

    void uploadDrivingLicence(MultipartFile drivingLicence) throws IOException;

    void uploadSelfie(MultipartFile photo) throws IOException;
}
