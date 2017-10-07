package com.sada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sada.web.auth.CPBAuthenticationFilter;
import com.sada.web.auth.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(customCPBAuthenticationFilter(), CPBAuthenticationFilter.class);
		http
                .authorizeRequests()
                    .antMatchers("/resources/**", "/welcome","/home").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .failureForwardUrl("/login?error=Authentication failed")
                    .defaultSuccessUrl("/welcome")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
	}
	@Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .authenticationProvider(this.customAuthenticationProvider);
    }
	
	@Bean
	public CPBAuthenticationFilter customCPBAuthenticationFilter()
	        throws Exception {
		CPBAuthenticationFilter cpbAuthentiationFilter = new CPBAuthenticationFilter();
	    cpbAuthentiationFilter.setAuthenticationManager(authenticationManagerBean());
	   return cpbAuthentiationFilter;
	}
}
