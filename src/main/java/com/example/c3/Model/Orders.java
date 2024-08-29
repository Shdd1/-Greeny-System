package com.example.c3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;


    //قائمة بالنباتات والأدوات التي تم طلبها
    //items: String
    @NotEmpty(message = "Items should not be Empty!")
    @Column(columnDefinition = "varchar(500) not null")
    private String items;

    //quantity: int
    @NotNull(message = "Quantity should not be null!")
    @Column(columnDefinition = "int not null")
    private int quantity;

    //toolStatus: boolean
//    @AssertFalse
    @Column(columnDefinition = "boolean default false")
    private boolean toolStatus;

    //totalAmount: double
    @NotNull(message = "Total Amount should not be null!")
    @Column(columnDefinition = "DOUBLE not null")
    private double totalAmount;

    //orderDate: Local date
    @Column(columnDefinition = "datetime default (current_timestamp)")
    private LocalDate orderDate;

    @Column(columnDefinition = "datetime default (current_timestamp)")
    private LocalDate updatedAt;

    @Column(columnDefinition = "DOUBLE not null")
    private double discount;

    @ManyToMany
    @JsonIgnore
//    private Set<Plants> plants;
    private Set<Plants> plants = new HashSet<>();
//    @ManyToMany(mappedBy = "orders")
//    private Set<Plans> plans;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "users_id" , referencedColumnName = "id")
    private Users users;



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "orders")
    private Set<Reviews> reviews;

    @ManyToMany(mappedBy = "orders")
    private Set<GardeningTools>gardeningTools = new HashSet<>();

}
