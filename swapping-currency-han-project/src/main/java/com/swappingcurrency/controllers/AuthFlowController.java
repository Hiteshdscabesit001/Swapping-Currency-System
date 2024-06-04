package com.swappingcurrency.controllers;

import com.swappingcurrency.dto.LoginDto;
import com.swappingcurrency.dto.ResponseDto;
import com.swappingcurrency.dto.SignUpDto;
import com.swappingcurrency.services.UserService;
import com.swappingcurrency.services.impl.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

@RestController
@RequestMapping("swappingcurrency")
@Validated
public class AuthFlowController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("signup")
    public ResponseEntity<ResponseDto> signUp(@Valid @RequestBody SignUpDto signup){
        if(!signup.getPassword().equals(signup.getConfirmPassword())) throw new BadCredentialsException("Password and Confirm password must be same");
        userService.signUp(signup);
        return new ResponseEntity<>(new ResponseDto(HttpStatus.CREATED, "Otp sent successfully."), HttpStatus.CREATED);
    }

    /* This api login with user's username or password and generate token if these details are valid. */
    @PostMapping("login")
    public String authenticateAndGetToken(@RequestBody LoginDto authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getEmail());
        } else {
            throw new UsernameNotFoundException("Email or password is incorrect!!");
        }
    }

    /* It sent otp to the email id before registering the user if otp is valid then it register the user. */
    @GetMapping("validateOtp")
    public ResponseEntity<ResponseDto> validateOtp(@RequestHeader String otp) {
        userService.validateOtp(otp);
        return new ResponseEntity<>(new ResponseDto(HttpStatus.CREATED, "User created successfully."), HttpStatus.CREATED);
    }

    /* Logout api */
    @GetMapping("logout")
    public ResponseEntity<ResponseDto> logout(){
        if(SecurityContextHolder.getContext() == null) throw new BadCredentialsException("There is no logged-In user");
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK, "Logged Out successfully"), HttpStatus.OK);
    }

    /* This is the test method */
    @PostMapping(value = "testMethod")
    public ResponseEntity<ResponseDto> testMethod(){
        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK,
                "This is test method created for testing JWT Token"), HttpStatus.OK);
    }
}
