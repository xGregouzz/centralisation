package com.centralisation.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String roles;
}


