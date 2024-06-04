package com.swappingcurrency.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String email;
    @NotEmpty(message = "FirstName can't be empty")
    @Size(min = 3)
    private String firstName;
    @NotEmpty(message = "LastName can't be empty")
    @Size(min = 3)
    private String lastName;
    @NotEmpty(message = "Username can't be empty")
    @Size(min = 4)
    private String username;
    @NotEmpty(message = "Gender is required")
    private String gender;
    @NotNull(message = "Date of Birth is required")
    private Date dob;
    @NotEmpty(message = "MobileNumber can't be empty")
    @Pattern(regexp = "^\\+?[0-9\\s-]+$")
    private String mobileNumber;
    @NotEmpty(message = "Country Name is required")
    private String country;
    @NotEmpty(message = "State is required")
    private String state;
    @NotEmpty(message = "City is required")
    private String city;
    @NotNull(message = "Postal Code is required")
    private int postalCode;
    @NotEmpty(message = "Address can't be empty")
    private String address;
}
