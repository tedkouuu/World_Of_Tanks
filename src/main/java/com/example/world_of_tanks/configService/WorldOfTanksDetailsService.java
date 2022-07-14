package com.example.world_of_tanks.configService;

import com.example.world_of_tanks.models.UserEntity;
import com.example.world_of_tanks.models.UserRoleEntity;
import com.example.world_of_tanks.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

public class WorldOfTanksDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public WorldOfTanksDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(this::map).orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found!"));
    }

    private UserDetails map(UserEntity userEntity) {
        return User.builder().username(userEntity.getUsername()).password(userEntity.getPassword()).authorities(userEntity.getRoles().stream().map(this::map).toList()).build();
    }

    private GrantedAuthority map(UserRoleEntity userRole) {
        return new SimpleGrantedAuthority("ROLE_" + userRole.getUserRole().name());
    }
}

