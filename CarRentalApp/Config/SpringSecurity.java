package com.CarRentalapp.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity  {

    @Autowired
    private UserDetailsService userDetailsService ;


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


            return http.authorizeHttpRequests(request -> request
                            .requestMatchers("/public/**").permitAll()
                        //    .requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll() // Allow access to Swagger UI and API docs
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .requestMatchers("/user/**").authenticated()

                            .anyRequest().permitAll())
                    .httpBasic(Customizer.withDefaults())
                    .csrf(AbstractHttpConfigurer::disable)
                    .build();
        }


        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder() ;
    }

}
