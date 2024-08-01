package com.studyeasy.springBlog.security;

import com.studyeasy.springBlog.utils.constants.Privileges;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig   {

        private static final String[] WHITELIST = {
                "/", "/login", "/register","/js/**", "/css/**", "/fonts/**", "/images/**",
                "/resources/static/**",
                "/forgot-password","/reset-password", "/change-password", // added for forgot password
            "/db-console/**"
    };


    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize.
                requestMatchers(WHITELIST)
                .permitAll()
                .requestMatchers(
                "/profile/**")
                .authenticated()
                .requestMatchers("/admin/**")
                .hasRole("ADMIN")
                .requestMatchers("/editor/**")
                .hasRole("EDITOR")
                .requestMatchers("/test/**")
                .hasAuthority(Privileges.ACCESS_ADMIN_PANEL.getPrivilegeName())
                .anyRequest()
                .authenticated())
                .formLogin(form -> form.
                        loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error")
                        .permitAll()).logout(lOut -> lOut
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login"))
                .rememberMe(rememberMe->rememberMe.rememberMeParameter("remember-me"));





        //remove after upgrading the db from h2 file db
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable); // cross site request forgery

        http.headers(AbstractHttpConfigurer::disable);

        return http.build();
    }


}



