package com.tangtang.polingo.user.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue
    @Getter
    private Long id;
    private String username;
    @Getter
    private String password;
    private String name;

    @Builder
    public Admin(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

}
