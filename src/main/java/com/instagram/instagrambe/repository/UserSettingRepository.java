package com.instagram.instagrambe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instagram.instagrambe.entity.UserSetting;

public interface UserSettingRepository extends JpaRepository<UserSetting,String>{

    
}