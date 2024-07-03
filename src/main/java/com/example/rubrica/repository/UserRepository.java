package com.example.rubrica.repository;

import com.example.rubrica.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, String> {
    public AppUser findByEmail(String email);
}
