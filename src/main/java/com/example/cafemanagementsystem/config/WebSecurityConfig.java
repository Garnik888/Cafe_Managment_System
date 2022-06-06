package com.example.cafemanagementsystem.config;

import com.example.cafemanagementsystem.domain.enums.UserRole;
import com.example.cafemanagementsystem.security.JwtAuthenticationEntryPoint;
import com.example.cafemanagementsystem.security.JwtAuthenticationTokenFilter;
import com.example.cafemanagementsystem.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final JwtAuthenticationEntryPoint unauthorizedHandler;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/auth/signup").hasAnyRole(String.valueOf(UserRole.ADMIN))
                .antMatchers(HttpMethod.POST, "/api/v1/products").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/product/{id}").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/product/view").authenticated()
                .antMatchers(HttpMethod.GET, "/product/search").authenticated()
                .antMatchers(HttpMethod.GET, "/product/type").authenticated()
                .antMatchers(HttpMethod.GET, "/product/sort").authenticated()
                .antMatchers(HttpMethod.PUT, "/order/{id}").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/order/view").authenticated()
                .antMatchers(HttpMethod.DELETE, "/order/{id}").authenticated()
                .antMatchers(HttpMethod.POST, "/order/").authenticated()
                .anyRequest().permitAll();

        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {

        return new JwtAuthenticationTokenFilter();
    }

}
