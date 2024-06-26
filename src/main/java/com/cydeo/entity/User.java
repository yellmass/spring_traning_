package com.cydeo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "user_account")
public class User extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "account_details_id")
    private Account account;

    private String email;
    private String password;
    private String username;
}
