package com.cnpr.homologation.security;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class WebSecurityConfig {

//    private final CustomUserDetailsService userDetailsService;
//    private final PasswordEncoder passwordEncoder;
//    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
//
//    public WebSecurityConfig(CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder,CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
//        this.userDetailsService = userDetailsService;
//        this.passwordEncoder = passwordEncoder;
//        this.customAuthenticationFailureHandler=customAuthenticationFailureHandler;
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//            .authorizeRequests()
//            .antMatchers("/login").permitAll()
//            .antMatchers("/img/**").permitAll()
//            .antMatchers("/vendor/bootstrap/css/**").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin().loginPage("/login").defaultSuccessUrl("/dashboard", true).failureHandler(customAuthenticationFailureHandler)
//            .failureUrl("/login?error=true") 
//            .and()
//            .logout().logoutSuccessUrl("/login");
//        return http.build();
//    }
    
    

//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//    }

}
