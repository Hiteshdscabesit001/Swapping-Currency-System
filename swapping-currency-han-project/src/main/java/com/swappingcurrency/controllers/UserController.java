package com.swappingcurrency.controllers;

import com.swappingcurrency.dto.ResponseDto;
import com.swappingcurrency.dto.UserDto;
import com.swappingcurrency.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("swappingcurrency/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    /* It sent otp to your registered email id if you forgot your password then you can change your
        password if you enter valid otp. */
    @GetMapping("forgotPassword")
    public ResponseEntity<ResponseDto> forgotPassword(@RequestHeader @Email(message = "Provide valid email id") String email){
        userService.forgotPasswordOtp(email);
        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK, "Otp sent successfully"), HttpStatus.OK);
    }

    /* Here you can pass your otp and verify otp */
    @GetMapping("validateOtp")
    public ResponseEntity<ResponseDto> validateOtp(@RequestHeader String otp) {
        userService.validateForPassword(otp);
        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK, "Otp validated."), HttpStatus.OK);
    }

    /* This api is used to reset password of a user */
    @GetMapping("resetPassword")
    public ResponseEntity<ResponseDto> resetPassword(@RequestHeader
                                                      @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%&])[A-Za-z\\d@$!%*?&]{8,}$",
                                                              message = "Provide valid password")
                                                      String newPassword, @RequestHeader
                                                      @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%&])[A-Za-z\\d@$!%*?&]{8,}$",
                                                              message = "Provide valid password")
                                                      String confirmNewPassword) {
        if(!newPassword.equals(confirmNewPassword)) throw new BadCredentialsException("Both passwords must be same");
        userService.resetPassword(newPassword);
        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK, "Password is changed successfully"), HttpStatus.OK);
    }

    @PatchMapping("updateUserDetails")
    public ResponseEntity<ResponseDto> updateUserDetails(@Valid @RequestBody UserDto userDto){
        userService.updateUserDetails(userDto);
        return new ResponseEntity<>(new ResponseDto(HttpStatus.CREATED, "User details are updated successfully."), HttpStatus.CREATED);
    }

    @PostMapping("changePassword")
    public ResponseEntity<ResponseDto> changePassword(@RequestHeader
                                                      @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%&])[A-Za-z\\d@$!%*?&]{8,}$",
                                                              message = "Provide valid password")
                                                      String oldPassword, @RequestHeader
                                                      @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%&])[A-Za-z\\d@$!%*?&]{8,}$",
                                                              message = "Provide valid password")
                                                      String newPassword, @RequestHeader
                                                      @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%&])[A-Za-z\\d@$!%*?&]{8,}$",
                                                              message = "Provide valid password")
                                                      String confirmNewPassword){
        userService.changePassword(oldPassword, newPassword);
        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK, "Otp sent successfully."), HttpStatus.OK);
    }

    @GetMapping("validateChangePassword")
    public ResponseEntity<ResponseDto> changePasswordOtp(@RequestHeader String otp) {
        userService.changePasswordOtp(otp);
        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK, "Password is changed"), HttpStatus.OK);
    }
}
