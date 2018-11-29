package com.exercise.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import com.exercise.util.Constants;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
  
  @Autowired
  private AccessDeniedHandler accessDeniedHandler;
  
  @Autowired
  private DataSource dataSource;
  
  @Autowired
  private UserServiceImpl userServiceImpl;
  
  @Value("${spring.queries.users-query}")
  private String usersQuery;
  
  @Value("${spring.queries.roles-query}")
  private String rolesQuery;
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
          throws Exception {
//      auth.
//              jdbcAuthentication()
//              .usersByUsernameQuery(usersQuery)
//              .authoritiesByUsernameQuery(rolesQuery)
//              .dataSource(dataSource);
    auth.userDetailsService(userServiceImpl).passwordEncoder(passwordEncoder());
  }
  
  @Bean
  public PasswordEncoder passwordEncoder() {
      PasswordEncoder encoder = new BCryptPasswordEncoder();
      return encoder;
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable()
    .authorizeRequests()
        .antMatchers("/", "/home", "/about").permitAll()
        .antMatchers("/posts/**").hasAnyAuthority("ADMIN", "EDITOR")
        .antMatchers("/users/**").hasAnyAuthority("ADMIN")
        .anyRequest().authenticated()
    .and()
    .formLogin()
        .loginPage(Constants.URL_USER_LOGIN)
        .defaultSuccessUrl("/")
        .usernameParameter("user_name")
        .passwordParameter("password")
        .permitAll()
        .and()
    .logout()
        .logoutSuccessUrl("/")
        .logoutUrl(Constants.URL_USER_LOGOUT)
        .permitAll()
        .and()
    .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
      web
              .ignoring()
              .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/img/**", "/ckeditor/**");
  }
}
