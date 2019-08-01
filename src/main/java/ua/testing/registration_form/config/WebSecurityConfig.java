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
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/");
        http.authorizeRequests() // Авторизация запросоа
                .antMatchers("/", "/logout", "/login_natural_person",
                             "/reg_form", "/login_juridical_person",
                             "/login_taxofficer") // , "/login/username"
                .permitAll()
                .antMatchers("/not_format/reports_officer",
                             "/officer_report_list")
                .access("hasRole('ROLE_OUSER')")
                .antMatchers("/payer_report_list",
                             "/payer_report_list/data_for_report_create",
                             "/payer_report_list/creation",
                             "not_format/reports_payer")
                .access("hasRole('ROLE_PUSER')")
                .antMatchers("/not_format/users", "/users")
                .access("hasRole('ROLE_ADMIN')")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
           .antMatchers("/resources/**", "/js/**", "/static/**");
                   //, "/static/**", "/css/**", "/js/**", "/images/**","/vendor/**","/fonts/**");
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("password")
//                .roles("USER");
//    }
}
