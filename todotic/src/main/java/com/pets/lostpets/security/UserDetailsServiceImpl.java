package com.pets.lostpets.security;

import com.pets.lostpets.entity.Admin;
import com.pets.lostpets.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    AdminRepository adminRepository;

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            Admin admin = adminRepository
                    .findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("El usuario no ha sido encontrado para: " + email));

            return new AppUserDetails(admin);
        }
}
