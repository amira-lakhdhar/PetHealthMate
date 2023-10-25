package com.example.userms.repo;

import com.example.userms.entity.Role;
import com.example.userms.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("select r from Role r where r.role = ?1")
    Set<Role> findByRole(RoleEnum role);
}