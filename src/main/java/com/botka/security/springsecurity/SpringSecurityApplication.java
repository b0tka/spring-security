package com.botka.security.springsecurity;

import com.botka.security.springsecurity.security.UserPermissions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
		new HashSet<>(Arrays.asList(UserPermissions.ADMIN_READ, UserPermissions.ADMIN_WRITE));
	}

}
