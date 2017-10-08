package com.sada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.sada.web.auth.CPBAuthenticationFilter;
import com.sada.web.auth.CustomAuthenticationFailureHandler;
import com.sada.web.auth.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

	/*@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
	}*/
	
	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
                .authorizeRequests().antMatchers("/resources/**", "/","/home","/error").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll().failureHandler(customAuthenticationFailureHandler)
                .and()
                .logout().permitAll()
                .and()
                .addFilterBefore(customCPBAuthenticationFilter(), CPBAuthenticationFilter.class)
                .csrf().disable();
                
	}
	@Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.eraseCredentials(false)
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
