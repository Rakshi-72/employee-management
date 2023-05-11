package com.rakshi.employeemanagement.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.rakshi.employeemanagement.exceptions.EmployeeNotFoundException;
import com.rakshi.employeemanagement.models.Employee;
import com.rakshi.employeemanagement.repositories.EmployeeRepo;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class MysecurityConfig {

    private final EmployeeRepo repo;

    /**
     * This function configures security settings for HTTP requests, including CORS,
     * CSRF, and
     * authorization rules based on user roles.
     * 
     * @param security The `security` parameter is an instance of `HttpSecurity`,
     *                 which is a class in
     *                 Spring Security that allows you to configure security for
     *                 your application. It provides methods
     *                 to configure things like authentication, authorization, CORS,
     *                 CSRF protection, and more.
     * @return The method is returning a SecurityFilterChain object.
     */
    @Bean
    SecurityFilterChain getConfig(HttpSecurity security) throws Exception {
        security.cors(withDefaults())
                .csrf(withDefaults())
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/employees/**").hasRole("ADMIN").anyRequest().authenticated());

        security.httpBasic(withDefaults());

        security.authenticationProvider(getProvider());

        return security.build();
    }

    /**
     * This function returns a new instance of the BCryptPasswordEncoder class,
     * which is used for
     * password encoding.
     * 
     * @return A new instance of the BCryptPasswordEncoder class is being returned
     *         as a PasswordEncoder
     *         bean.
     */
    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * This function returns an instance of AuthenticationManager using the provided
     * AuthenticationConfiguration.
     */
    @Bean
    AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * This function returns an instance of an authentication provider with a
     * password encoder and user
     * details service.
     * 
     * @return A bean of type AuthenticationProvider is being returned.
     */
    @Bean
    AuthenticationProvider getProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(getEncoder());
        provider.setUserDetailsService(getService());
        return provider;

    }

    /**
     * This Java function returns a UserDetailsService that loads user details by
     * username from an
     * EmployeeRepo and creates a new MyUserDetails object.
     * 
     * @return A UserDetailsService object is being returned.
     */
    @Bean
    UserDetailsService getService() {
        return new UserDetailsService() {

            @Autowired
            private EmployeeRepo repo;

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                Employee emp = repo.findByEmail(username).orElseThrow(() -> new EmployeeNotFoundException(username));
                return new MyUserDetails(emp);
            }

        };
    }
}
