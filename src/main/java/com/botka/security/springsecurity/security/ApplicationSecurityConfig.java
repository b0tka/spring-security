package com.botka.security.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.botka.security.springsecurity.security.UserRoles.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // required to enable @PreAuthorize annotations
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/", "index").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/test").permitAll()
//                .antMatchers("/api/v1/students/**").hasRole(UserRoles.ADMIN.name())
//                .antMatchers(HttpMethod.DELETE,"/api/v1/students/**").hasAuthority(UserPermissions.STUDENT_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT,"/api/v1/students/**").hasAuthority(UserPermissions.STUDENT_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST,"/api/v1/students/**").hasAuthority(UserPermissions.STUDENT_WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails anna = User.builder()
                .username("anna")
                .password(passwordEncoder.encode("test"))
//                .roles(UserRoles.ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails linda = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("test"))
//                .roles(UserRoles.STUDENT.name())
                .authorities(STUDENT.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(anna, linda);
    }



}
