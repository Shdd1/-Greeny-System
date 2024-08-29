package com.example.c3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(columnDefinition = "int not null")
//    @NotNull(message = "userId can not be null")
//    private Integer userId;
//
//    @Column(columnDefinition = "int not null")
//    @NotNull(message = "plantId can not be null")
//    private Integer plantId;

//    @Column(columnDefinition = "int not null")
//    @NotNull(message = "consultantId can not be null")
//    private Integer consultantId;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "rating can not be null")
    private Integer rating;

    @NotEmpty(message = "comment can not be empty")
    private String comment;


    //Many Reviews to one consultant
    @ManyToOne
    @JoinColumn(name= "Consultant_id", referencedColumnName="id")
    @JsonIgnore
    private Consultant consultant;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders orders;

}
