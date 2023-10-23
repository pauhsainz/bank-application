package com.rabobank.bankapplication.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BankAccount {
    @Id
    private String iban;
    private long balance;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
//ADD HERE 'Create a method that extracts the email field from the associated User' //Send that field to BAController

}
