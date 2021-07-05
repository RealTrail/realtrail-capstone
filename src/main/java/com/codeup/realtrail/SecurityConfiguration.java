package com.codeup.realtrail;

import com.codeup.realtrail.services.UserDetailsLoader;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserDetailsLoader usersLoader;
    private LoginSuccessHandler loginSuccessHandler;

    public SecurityConfiguration(UserDetailsLoader usersLoader, LoginSuccessHandler loginSuccessHandler) {
        this.usersLoader = usersLoader;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usersLoader) // How to find users by their username
                .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /* Testing: Disable CSRF - kh */
                .csrf().disable()
                /* Login configuration */
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .defaultSuccessUrl("/") // user's home page, it can be any URL
                .successHandler(loginSuccessHandler)
                .permitAll() // Anyone can go to the login page
                /* Logout configuration */
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // append a query string value
                /* Pages that can be viewed without having to log in */
                .and()
                .authorizeRequests()
                .antMatchers("/", "/signup", "/login", "/trails") // anyone can see the home and the trails pages
                .permitAll()
                /* Pages that require authentication */
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/create", // only authenticated users can create an event
                        "/events",  // only authenticated users can see all events
                        "/events/{id}",  // only authenticated users can see an event
                        "/events/{id}/edit" // only authenticated users can edit events
                )
                .authenticated()
                .antMatchers(


                        "/profile/{id}",  // only authenticated users can see an event
                        "/profile/{id}/edit",// only authenticated users can edit events
                        "/profile/settings/{id}",
                        "/profile/delete/{id}"
                ).hasAuthority("ADMIN");
    }
}
