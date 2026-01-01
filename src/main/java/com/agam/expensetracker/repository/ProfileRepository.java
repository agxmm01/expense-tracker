package com.agam.expensetracker.repository;

import com.agam.expensetracker.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long>{
    // JPA will execute SQL Query to find the user
    // select * from tbl_users where email = ?
    Optional<ProfileEntity> findByEmail(String email);

    // select * from tbl_profile where activation_token = ?
    Optional<ProfileEntity> findByActivationToken(String activationToken);
}
