package com.example.c3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
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
//@Table(name = "plants")
public class Plants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String name;

    @NotEmpty(message = "description shouldn't be empty")
    @Column(columnDefinition = "varchar(500) not null")
    private String description;



    @NotEmpty(message = "Light Intensity shouldn't be empty")
    @Column(columnDefinition = "varchar(500) not null")
    @Pattern(regexp = "^(sun|partial|shade)$", message = "watering Needs has 3 valid inputs only(Sun,Partial,Shade).")
    private String lightIntensity;

    @NotEmpty(message = "lightRequirements shouldn't be empty")
    @Column(columnDefinition = "varchar(500) not null")
    private String lightRequirements;



    @NotEmpty(message = "Watering Needs shouldn't be empty")
    @Column(columnDefinition = "varchar(500) not null")
    @Pattern(regexp = "^(low|moderate|high)$", message = "watering Needs has 3 valid inputs only(Low,Moderate,High).")
    private String wateringNeeds;

    @NotEmpty(message = "Water Requirements shouldn't be empty")
    @Column(columnDefinition = "varchar(500) not null")
    private String waterRequirements;



    @NotEmpty(message = "Season Plants shouldn't be empty")
    @Column(columnDefinition = "varchar(500) not null")
    @Pattern(regexp = "^(cool|warm|tropical|subtropical)$", message = "Season Plants has 3 valid inputs only(cool,warm,tropical,subtropical).")
    private String seasonPlants;

    @NotEmpty(message = "Temperature Requirements shouldn't be empty")
    @Column(columnDefinition = "varchar(500) not null")
    private String temperatureRequirements;



    @NotEmpty(message = "soilTypeRequirements shouldn't be empty")
    @Column(columnDefinition = "varchar(500) not null")
    @Pattern(regexp = "^(sandy|clay|silt|loam|peaty|saline)$", message = "Soil Type has 6 valid inputs only(sandy, clay, silt,loam,peaty and saline).")
    private String soilType;

    @NotEmpty(message = "soilTypeRequirements shouldn't be empty")
    @Column(columnDefinition = "varchar(500) not null")
    private String soilTypeRequirements;



    @NotEmpty(message = "Season shouldn't be empty")
    @Column(columnDefinition = "varchar(500) not null")
    @Pattern(regexp = "^(cool|warm|tropical)$", message = "Season has 3 valid inputs only(Cool,Warm,Tropical).")
    private String season;

    @NotEmpty(message = "plantingSeason shouldn't be empty")
    @Column(columnDefinition = "varchar(500) not null")
    private String plantingSeason;



    @NotEmpty(message = "growth shouldn't be empty")
    @Column(columnDefinition = "varchar(500) not null")
    @Pattern(regexp = "^(cool|warm|tropical)$", message = "growth Season Needs has 3 valid inputs only(Cool,Warm,Tropical).")
    private String growthSeason;

    @NotEmpty(message = "growthTime shouldn't be empty")
    @Column(columnDefinition = "varchar(500) not null")
    private String growthTime;




    @NotEmpty(message = "category shouldn't be empty")
    @Column(columnDefinition = "varchar(500) not null")
    private String category;

    @NotNull(message = "price shouldn't be null")
    @Column(columnDefinition = "double not null")
    private Double price;

    @PositiveOrZero
    @Column(columnDefinition = "int not null")
    private Integer stock;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "farmers_id" , referencedColumnName = "id")
    private Farmers farmers;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "nurseries_id" , referencedColumnName = "id")
    private Nurseries nurseries;

//    @ManyToMany(mappedBy = "plants")
//    private Set<Orders> orders;


    //Many plants to  many orders
    @ManyToMany
    @JsonIgnore
    private Set<Orders> orders;
}
