package kz.muminov.iitu.library.config;

import kz.muminov.iitu.library.config.jwt.JwtTokenAuthenticationFilter;
import kz.muminov.iitu.library.config.jwt.JwtTokenGeneratorFilter;
import kz.muminov.iitu.library.serivce.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**", "/users/register", "/authors/**").permitAll()
                .antMatchers("/users/api/**").hasAuthority("ADMIN")
                .antMatchers("/issued/all").hasAuthority("ADMIN")
                .antMatchers("/users/issue", "/users/return").hasAnyAuthority("MEMBER")
                .antMatchers(HttpMethod.GET, "/books").permitAll()
                .antMatchers(HttpMethod.POST, "/books").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/books/**").hasAuthority("ADMIN")
                .antMatchers("/books/**", "/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtTokenGeneratorFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoderInConfig() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoderInConfig());
    }




}
