package com.antony.SpringBootRestWS.entrypoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
*
* @author Antony John
*
@Configuration
@EnableWebSecurity*/
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String REALM = "realm";

    @Autowired
    private CustomAuthenticationProvider authProvider; 

    /**
     * This is not required for Basic Authentication
     */
    @Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
    
    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http
            .logout()
            .and()
            // default with authentication
            .authorizeRequests()
            .antMatchers("/login/authenticatebasic").permitAll()
            .anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)                        
            .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
            // Disable CSRF for fix unauthorized access issue POST method
            /* CSRF protection for any request that could be processed by a browser by normal users. 
             * If you are only creating a service that is used by non-browser clients, 
             * you will likely want to disable CSRF protection.*/
            .and().csrf().disable();
    }

    @Bean
    BasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
        BasicAuthenticationEntryPoint basicAuth = new BasicAuthenticationEntryPoint();
        basicAuth.setRealmName(REALM);
        return basicAuth;
    }
}
