package com.web.entity;

import com.web.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tai_khoan")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String email;

    private String password;

    private Boolean actived;

    private String fullName;

    @Enumerated(EnumType.STRING)
    private Role role;
}
