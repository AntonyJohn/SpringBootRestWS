package com.antony.SpringBootRestWS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(scanBasePackages="com.antony.SpringBootRestWS")
@EnableWebSecurity
@ComponentScan(basePackages={"com.antony.SpringBootRestWS"})
public class SpringBootRestWsApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestWsApplication.class, args);
	}

}
