package com.example.capstone01_tuwaiqjavabootcamp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jmx.export.annotation.ManagedAttribute;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Category {

    //2- Create Category Class:
    // • id (must not be empty).
    // • name (must not be empty, have to be more than 3 length long).

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "category name cannot be empty")
    @Size(min = 3,message = "category name should be more than 3 in length size")
    private String name;

}
