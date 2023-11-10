package com.example.Fiverr.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Fiverr.Model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
    Users findByUserName(String userName);
    Users findByEmail(String email);
}



