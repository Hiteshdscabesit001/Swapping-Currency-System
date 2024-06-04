package com.swappingcurrency.controllers;

import com.swappingcurrency.dto.ResponseDto;
import com.swappingcurrency.services.KycService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("kyc")
public class KycController {

    @Autowired
    private KycService kycService;

    @PostMapping("upload/passport")
    public ResponseEntity<ResponseDto> uploadPassport(@RequestParam MultipartFile frontSide, @RequestParam MultipartFile backSide) throws Exception {
        kycService.uploadPassport(frontSide, backSide);
        return new ResponseEntity<>(new ResponseDto(HttpStatus.ACCEPTED, "Passport submitted successfully"), HttpStatus.ACCEPTED);
    }

    @PostMapping("upload/drivingLicence")
    public ResponseEntity<ResponseDto> uploadDrivingLicence(@RequestParam MultipartFile drivingLicence) throws Exception {
        kycService.uploadDrivingLicence(drivingLicence);
        return new ResponseEntity<>(new ResponseDto(HttpStatus.ACCEPTED, "Driving Licence submitted successfully"), HttpStatus.ACCEPTED);
    }

    @PostMapping("upload/photo")
    public ResponseEntity<ResponseDto> uploadSelfie(@RequestParam MultipartFile photo) throws Exception {
        kycService.uploadSelfie(photo);
        return new ResponseEntity<>(new ResponseDto(HttpStatus.ACCEPTED, "Selfie submitted successfully"), HttpStatus.ACCEPTED);
    }
}
