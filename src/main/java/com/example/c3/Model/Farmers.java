package com.example.c3.Model;

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
//@Table(name = "Farmers")
public class Farmers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;
    @NotEmpty(message = "Name shouldn't be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String address;
    @NotEmpty(message = "Name shouldn't be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String phone_number;

    @Column(columnDefinition = "datetime default (current_timestamp)")
    private LocalDate registration_date;

    @OneToMany(mappedBy = "farmers" ,cascade = CascadeType.ALL)
    private Set<Nurseries> nurseries;

    @OneToMany(mappedBy = "farmers",cascade = CascadeType.ALL)
    private Set<Plants> plants;

}
