package ua.testing.registration_form.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests() // Авторизация запросоа
                .antMatchers("/", "/login_natural_person", "/reg_form", "/users",
                             "/not_format/users", "/login_juridical_person", "/login_taxofficer" )
                .permitAll()
            .and()
                .authorizeRequests()
                .anyRequest().authenticated();
//                .authenticated();  .permitAll()

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
           .antMatchers("/resources/**", "/js/**", "/static/**");
                   //, "/static/**", "/css/**", "/js/**", "/images/**","/vendor/**","/fonts/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .roles("USER");
    }
}
