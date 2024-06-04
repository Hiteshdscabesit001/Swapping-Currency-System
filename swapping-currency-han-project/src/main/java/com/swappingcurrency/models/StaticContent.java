package com.swappingcurrency.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StaticContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long contentId;
    private String keyName;
    private String description;
    private Date creationDate;
    private Date updationDate;
}
