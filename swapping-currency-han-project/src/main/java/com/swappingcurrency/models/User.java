package com.swappingcurrency.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String confirmPassword;
    private String gender;
    private Date dob;
    private String mobileNumber;
    private String country;
    private String state;
    private String city;
    private int postalCode;
    private String address;
    private String passportFrontSide;
    private String passportBackSide;
    private String drivingLicence;
    private String selfie;
}
