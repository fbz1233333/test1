package com.example.test1.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class User implements Serializable {

    private String id;

    private String username;

    private String password;

    private static final long serialVersionUID = 1L;
}
