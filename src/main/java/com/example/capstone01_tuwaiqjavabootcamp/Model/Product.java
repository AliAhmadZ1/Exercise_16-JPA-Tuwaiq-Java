package com.example.capstone01_tuwaiqjavabootcamp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Product {
    //1- Create Product Class:
    //• id (must not be empty).
    //• name (must not be empty, have to be more than 3 length long).
    //• price (must not be empty, must be positive number).
    //• categoryID (must not be empty).

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "product name cannot be empty")
    @Size(min = 3,message = "product name should be more than 3 in length size")
    private String name;
    @NotNull(message = "price cannot be null")
    @Positive(message = "price shouldn't be negative")
    private Double price;
    private Double offerPrice = 0.0;
    private Double withTax = 0.0;
    @Column(columnDefinition = "int not null, foreign key (category_id) references category(id)")
    private Integer category_id;

}
