package com.swappingcurrency.services;

import com.swappingcurrency.dto.SignUpDto;
import com.swappingcurrency.dto.UserDto;

public interface UserService {
    void signUp(SignUpDto signup);

    void validateOtp(String otp);

    void forgotPasswordOtp(String email);

    void validateForPassword(String otp);

    void resetPassword(String newPassword);

    void updateUserDetails(UserDto userDto);

    void changePassword(String oldPassword, String newPassword);

    void changePasswordOtp(String otp);
}
