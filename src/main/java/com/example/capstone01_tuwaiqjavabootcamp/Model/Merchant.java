package com.example.capstone01_tuwaiqjavabootcamp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Merchant {

    //3- Create Merchant Class:
        //• id (must not be empty).
        //• name (must not be empty, have to be more than 3 length long).

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "merchant name cannot be empty")
    @Size(min = 3,message = "merchant name should be more than 3 in length size")
    private String name;
}
