package com.example.capstone01_tuwaiqjavabootcamp.Repository;

import com.example.capstone01_tuwaiqjavabootcamp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}