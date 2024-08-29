package com.example.c3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
//@Table(name = "nurseries")
public class Nurseries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // farmers_id

    @NotEmpty(message = "Name shouldn't be empty ")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @NotEmpty(message = "phone_number shouldn't be empty ")
    @Column(columnDefinition = "varchar(30) not null")
    private String phone_number;

    @NotEmpty(message = "address shouldn't be empty ")
    @Column(columnDefinition = "varchar(30) not null")
    private String address;

    @NotEmpty(message = "typeOfPlants shouldn't be empty ")
    @Column(columnDefinition = "varchar(30) not null")
    private String typeOfPlants;

    @NotEmpty(message = "commercialRegisterNo should not be Empty!")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String commercialRegisterNo;

    @Column(columnDefinition = "datetime default (current_timestamp)")
    private LocalDate registration_date;

    @OneToMany(mappedBy = "nurseries", cascade = CascadeType.ALL)
    private Set<Plants> plants;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "farmers_id", referencedColumnName = "id")
    private Farmers farmers;

    //Many to many
    @ManyToMany
    @JsonIgnore
    private Set<Users> users;
}
