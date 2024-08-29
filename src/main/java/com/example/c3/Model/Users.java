package com.example.c3.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(20) not null unique")
    @NotNull(message = "username can not be null")
    @Length(min = 4,message = "username Length must be more than 4")
    private String username;

    @Column(columnDefinition = "varchar(20) not null unique")
    @NotNull(message = "email can not be null")
    @Email(message = "must be valid email ")
    private String email;

    @Column(columnDefinition = "varchar(20) not null")
    @NotNull(message = "password can not be null")
    private String password;

    @Column(columnDefinition = "varchar(20) not null")
    @NotNull(message = "preferences can not be null")
    private String preferences;

    @Column(columnDefinition = "varchar(20) not null")
    @NotNull(message = "address can not be null")
    private String address;

    @NotNull(message = "Phone number is required")
    @Size(max = 10, message = "Phone number must be 10 digits")
    private String phone_number;

    @Column(columnDefinition = "datetime default (current_timestamp)")
    private LocalDate registrationDate;

// Indicates that the User has confirmed the end of the session.
    private Boolean clientConfirmed;
    //Many to many
    @ManyToMany(mappedBy ="users")
    private Set<Plans>plans;


    @OneToMany(mappedBy = "users" , cascade = CascadeType.ALL)
    private Set<Orders> orders;

    //One user has many Consultations
    @OneToMany(mappedBy = "users",cascade = CascadeType.ALL)
    private Set<Consultation> consultations;


    //Many to many
    @ManyToMany(mappedBy ="users")
    private Set<Nurseries> nurseries;
}
