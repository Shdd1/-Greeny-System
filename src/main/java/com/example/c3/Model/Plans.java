package com.example.c3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Plans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(columnDefinition = "varchar(30) not null")
    @NotEmpty(message = "Plants field cannot be empty")
    @Size(min = 2, max = 30, message = "Plants field must be between 2 and 30 characters")
    private String plants;

    @Column(columnDefinition = "varchar(500) not null")
    @NotEmpty(message = "Schedule field cannot be empty")
    @Size(min = 2, max = 500, message = "Schedule field must be between 2 and 500 characters")
    private String schedule;

    @Column(columnDefinition = "varchar(100) not null")
    @NotEmpty(message = "recommendation field cannot be empty")
    @Size(max = 100, message = "Recommendation field can be at most 100 characters")
    private String recommendation;

    @Column(columnDefinition = "DOUBLE not null")
    //@NotNull(message = "planFee can not be null")
    private double planFee=30;


    //Many to many
    @ManyToMany
    @JsonIgnore
    private Set<Users> users;


    //Many Plans to one consultant
    @ManyToOne
    @JoinColumn(name= "Consultant_id", referencedColumnName="id")
    @JsonIgnore
    private Consultant consultant;
}
