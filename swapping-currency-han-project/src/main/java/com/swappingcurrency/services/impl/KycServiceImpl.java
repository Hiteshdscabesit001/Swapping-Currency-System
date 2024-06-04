package com.swappingcurrency.services.impl;

import com.cloudinary.Cloudinary;
import com.swappingcurrency.config.CloudinaryConfig;
import com.swappingcurrency.models.User;
import com.swappingcurrency.repositories.UserRepository;
import com.swappingcurrency.services.KycService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class KycServiceImpl implements KycService {

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private UserRepository userRepository;
    User usr;
    private static final Map<String, String> configurations = new HashMap<>();
    static {
        configurations.put("cloud_name", CloudinaryConfig.CLOUD_NAME.getCode());
        configurations.put("api_key", CloudinaryConfig.API_KEY.getCode());
        configurations.put("api_secret", CloudinaryConfig.API_SECRET.getCode());
    }

    @Override
    public void uploadPassport(MultipartFile frontSide, MultipartFile backSide) throws IOException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        usr = userRepository.findByEmail(email);
        Map<?, ?> uploadedResult = cloudinary.uploader().upload(frontSide, configurations);
        usr.setPassportFrontSide((String) uploadedResult.get("url"));
        uploadedResult = cloudinary.uploader().upload(backSide, configurations);
        usr.setPassportBackSide((String) uploadedResult.get("url"));
        userRepository.save(usr);
    }

    @Override
    public void uploadDrivingLicence(MultipartFile drivingLicence) throws IOException {
        Map<?, ?> uploadedResult = cloudinary.uploader().upload(drivingLicence, configurations);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        usr = userRepository.findByEmail(email);
        usr.setDrivingLicence((String) uploadedResult.get("url"));
        userRepository.save(usr);
    }

    @Override
    public void uploadSelfie(MultipartFile photo) throws IOException {
        Map<?, ?> uploadedResult = cloudinary.uploader().upload(photo, configurations);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        usr = userRepository.findByEmail(email);
        usr.setDrivingLicence((String) uploadedResult.get("url"));
        userRepository.save(usr);
    }
}
