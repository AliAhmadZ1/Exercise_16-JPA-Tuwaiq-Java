package com.example.capstone01_tuwaiqjavabootcamp.Model;

import jakarta.annotation.sql.DataSourceDefinitions;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class MerchantStock {

    //4- Create MerchantStock Class:
    //• id (must not be empty).
    //• productid (must not be empty)
    //• merchantid (must not be empty).
    //• stock (must not be empty, have to be more than 10 at start).

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "int , foreign key (product_id) references product(id)")
    private Integer product_id;
    @Column(columnDefinition = "int , foreign key (merchant_id) references merchant(id)")
    private Integer merchant_id;
    @Min(value = 10,message = "stock have to be at least 10 in first sign")
    private Integer stock;

}