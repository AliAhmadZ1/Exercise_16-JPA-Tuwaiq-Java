package com.example.capstone01_tuwaiqjavabootcamp.Repository;

import com.example.capstone01_tuwaiqjavabootcamp.Model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant,Integer> {

}
