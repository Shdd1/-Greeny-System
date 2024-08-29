package com.example.c3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Consultant {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY )
private Integer id;

//name: String
@NotEmpty(message = "Full name should not be empty!")
@Column(columnDefinition = "varchar(20) not null")
private String fullName;

//email: String
@Email
@NotEmpty(message = "Email should not be Empty!")
@Column(columnDefinition = "varchar(50) not null unique")
private String email;

//password: String
@NotEmpty(message = "Password should not be Empty!")
@Pattern(regexp = "^((?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{7,})$" , message = "Password must have to be more than 6 length long,characters and digits")
//@Column(columnDefinition = "varchar(50) not null check (password regexp '^((?=.*[A-Za-z])(?=.*\\\\d)[A-Za-z\\\\d]{7,})$ ' )")
private String password;

//specialization:String
@NotEmpty(message = "Specialization should not be Empty!")
@Column(columnDefinition = "varchar(50) not null")
private String specialization;

  @NotEmpty(message = "licenseNumber should not be Empty!")
 @Column(columnDefinition = "varchar(50) not null")
 private String licenseNumber;

//yearsOfExperience: int
@NotEmpty(message = "Years Of Experience should not be Empty!")
@Column(columnDefinition = "varchar(50) not null")
private String yearsOfExperience;

// registration_date
@Column(columnDefinition = "datetime default (current_timestamp)")
private LocalDate registrationDate;

@Column(columnDefinition = "datetime default (current_timestamp)")
private LocalDate updatedAt;

 // Indicates that the consultant has confirmed the end of the session.
 @Column(columnDefinition = "boolean default false")
 private Boolean consultantConfirmed;


//One consultant to many consultations
 @OneToMany(cascade = CascadeType.ALL, mappedBy = "consultant")
 private Set<Consultation> consultations;



 //One consultant has many plans
 @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL)
 private Set<Plans> plans;


 //One consultant has many reviews
 @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL)
 private Set<Reviews> reviews;



}
