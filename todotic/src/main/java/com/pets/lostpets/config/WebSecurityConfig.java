package com.pets.lostpets.config;

import com.pets.lostpets.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                // habilitación del inicio de sesión
                .formLogin()
                .loginPage("/login")
                .permitAll()

                .and()
                // definir los permisos a rutas específicas
                .authorizeRequests()
                .antMatchers("/admin/**", "/registro")
                .hasRole("ADMIN")

                .anyRequest()
                .permitAll()

                .and()

                .rememberMe().key("rememberMeKey").tokenValiditySeconds(3600) // 1 hora
                .userDetailsService(userDetailsServiceImpl)

                 .and()

                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).logoutSuccessUrl("/"));
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
