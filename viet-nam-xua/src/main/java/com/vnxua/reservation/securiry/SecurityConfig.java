package com.vnxua.reservation.securiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private SimpleAuthenticationSuccessHandler successHandler;

    @Bean
    public UserDetailsService userDetailService() {
        return new UserInfoDetailService();
    }

    @Bean
    public PasswordEncoder passwordEncode() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/admin.html").hasRole("ADMIN")
                .antMatchers("/account/getaccount").authenticated()
                .antMatchers("/table/create").hasAnyRole("STAFF", "ADMIN")
                .antMatchers("/table/update").hasAnyRole("STAFF", "ADMIN")
                .antMatchers("/table/delete").hasAnyRole("STAFF", "ADMIN")
                .antMatchers("/table/gettall").authenticated()
                .antMatchers("/reservation/**").authenticated()
                .antMatchers("/staffPage.html").hasAnyRole("STAFF", "ADMIN")
                .antMatchers("/userPage.html").hasAnyRole("USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(successHandler)
//                .defaultSuccessUrl("/")
                .and()
                .logout()
                .deleteCookies("JSESSIONID").logoutSuccessUrl("/");
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncode());
        return authenticationProvider;
    }
}
