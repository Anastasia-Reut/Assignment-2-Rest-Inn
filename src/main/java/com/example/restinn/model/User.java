package com.example.restinn.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("users")
public class User {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true, sparse = true)
    private String email;
    private String password;
}
