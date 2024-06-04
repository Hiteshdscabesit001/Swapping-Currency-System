package com.swappingcurrency.services.impl;

import com.swappingcurrency.dto.SignUpDto;
import com.swappingcurrency.dto.UserDto;
import com.swappingcurrency.exceptions.ResourceNotFoundException;
import com.swappingcurrency.models.User;
import com.swappingcurrency.repositories.UserRepository;
import com.swappingcurrency.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String FROM;
    private String otp = "";
    private User usr;

    @Override
    public void signUp(SignUpDto signup) {
        usr = new User();
        usr.setEmail(signup.getEmail());
        String[] name = signup.getFullName().split(" ");
        usr.setFirstName(name[0]);
        usr.setLastName(name[1]);
        String encodedPassword = passwordEncoder.encode(signup.getPassword());
        usr.setPassword(encodedPassword);
        usr.setConfirmPassword(encodedPassword);

        sendMail(signup.getEmail());
    }

    private void sendMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom(FROM);
        message.setSubject("Your otp");
        int generatedOtp = new Random().nextInt(8999) + 1000;
        otp = String.valueOf(generatedOtp);
        message.setText("Your OTP(One Time Passcode) : "+otp);
        javaMailSender.send(message);
    }

    public void validateOtp(String userOtp) {
        if(!userOtp.equals(otp)) throw new BadCredentialsException("Otp is invalid. Please provide valid Otp");
        userRepository.save(usr);
    }

    @Override
    public void forgotPasswordOtp(String email) {
        usr = userRepository.findByEmail(email);
        if(usr == null) throw new ResourceNotFoundException("User is not available with this email : "+email);
        sendMail(email);
    }

    @Override
    public void validateForPassword(String userOtp) {
        if(!userOtp.equals(otp)) throw new BadCredentialsException("Otp is invalid. Please provide valid Otp");
    }

    @Override
    public void resetPassword(String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        usr.setPassword(encodedPassword);
        usr.setConfirmPassword(encodedPassword);
        userRepository.save(usr);
    }

    @Override
    public void updateUserDetails(UserDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        usr = userRepository.findByEmail(email);
        usr.setFirstName(userDto.getFirstName());
        usr.setLastName(userDto.getLastName());
        usr.setCity(userDto.getCity());
        usr.setAddress(userDto.getAddress());
        usr.setCountry(userDto.getCountry());
        usr.setState(userDto.getState());
        usr.setPostalCode(userDto.getPostalCode());
        usr.setDob(userDto.getDob());
        usr.setGender(userDto.getGender());
        usr.setMobileNumber(userDto.getMobileNumber());
        usr.setUsername(userDto.getUsername());
        userRepository.save(usr);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        usr = userRepository.findByEmail(email);
        if(!passwordEncoder.matches(oldPassword, usr.getPassword())) throw new BadCredentialsException("Your password is incorrect");
        String strong = passwordEncoder.encode(newPassword);
        usr.setPassword(strong);
        usr.setConfirmPassword(strong);
        sendMail(email);
    }

    @Override
    public void changePasswordOtp(String userOtp) {
        if(!userOtp.equals(otp)) throw new BadCredentialsException("Otp is invalid. Please provide valid Otp");
        userRepository.save(usr);
    }
}
