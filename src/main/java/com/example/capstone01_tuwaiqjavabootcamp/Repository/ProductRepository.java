package com.example.capstone01_tuwaiqjavabootcamp.Repository;

import com.example.capstone01_tuwaiqjavabootcamp.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

}
