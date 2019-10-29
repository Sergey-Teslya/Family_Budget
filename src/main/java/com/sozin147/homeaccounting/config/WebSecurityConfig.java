package com.sozin147.homeaccounting.config;

import com.sozin147.homeaccounting.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Autowired
//    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                //Этому объекту назначаем сервис для работы с учетными записями
//                .userDetailsService(userDetailsService)
//                //Назначаем хеширования по алгоритму для паролей
//                //Создаст объект который может производить хеширование по переданому алгориму
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//        }

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select login, password, active from custom_user where login=lower(?)")
                .authoritiesByUsernameQuery("select login, role from custom_user where login=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/registration", "/login", "/user_registration",
                            "/completeRegistration").permitAll()
                    .antMatchers("/activate/*").permitAll()
                    .antMatchers( "/css/**", "/bootstrap/**" , "/image/**", "/static/**").permitAll()
                    .anyRequest().authenticated()
//                    .antMatchers("/index").authenticated()
                .and()

                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/user_authorization")
                    .defaultSuccessUrl("/hello")
                //Это названия input в нашей форме
                    .usernameParameter("user_login")
                    .passwordParameter("password")
                    .permitAll()
                .and()

                    .logout()
                    .logoutSuccessUrl("/")
                    .permitAll()
                    .invalidateHttpSession(true);
    }

}
