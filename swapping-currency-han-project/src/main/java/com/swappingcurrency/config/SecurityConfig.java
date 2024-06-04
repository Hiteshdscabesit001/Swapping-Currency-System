package com.swappingcurrency.config;

import com.swappingcurrency.models.User;
import com.swappingcurrency.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserRepository userRepo;

    /*This is user details service, it returns User for authentication*/
    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                User user = userRepo.findByEmail(email);
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("user"));
                return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
            }
        };
    }

    /*It returns AuthFilter class object*/
    @Bean
    JwtAuthFilter authFilter() {
        return new JwtAuthFilter();
    }

    /* It is a filterChain where we can configure our security.*/
    @Bean
    SecurityFilterChain getFilter(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth -> auth.requestMatchers("swappingcurrency/signup", "swappingcurrency/validateOtp",
                                "swappingcurrency/login", "swappingcurrency/user/forgotPasswordOtp",
                                "swappingcurrency/user/forgotPassword", "swappingcurrency/logout", "swappingcurrency/user/validateOtp",
                                "swappingcurrency/user/resetPassword", "static/**")
                .permitAll().anyRequest().authenticated())
                .logout(logout -> logout.logoutUrl("swappingcurrency/logout").invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID").permitAll())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
