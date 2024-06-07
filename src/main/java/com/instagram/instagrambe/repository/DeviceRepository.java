package com.instagram.instagrambe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.Device;

public interface DeviceRepository extends JpaRepository<Device,String>{

    
}