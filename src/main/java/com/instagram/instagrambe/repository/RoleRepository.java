package com.instagram.instagrambe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.Role;

public interface RoleRepository extends JpaRepository<Role,String>{
    Role findByRoleName(String roleName);
    
}