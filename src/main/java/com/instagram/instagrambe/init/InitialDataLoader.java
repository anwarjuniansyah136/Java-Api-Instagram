package com.instagram.instagrambe.init;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.instagram.instagrambe.constant.RoleConstant;
import com.instagram.instagrambe.entity.Role;
import com.instagram.instagrambe.repository.RoleRepository;

@Component
public class InitialDataLoader implements ApplicationRunner{
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Role> roles = roleRepository.findAll();
        if(roles.isEmpty()){
            Role role = new Role(null,RoleConstant.USERS_ROLE);

            roleRepository.save(role);
        }
    }
    
}
