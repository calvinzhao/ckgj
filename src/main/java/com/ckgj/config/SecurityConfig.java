package com.ckgj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
    //@Autowired
    //private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/admin/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .httpBasic();
//        http.authorizeRequests()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/login?error")
//                .usernameParameter("phone")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//                .permitAll();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(new BCryptPasswordEncoder());
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

}
