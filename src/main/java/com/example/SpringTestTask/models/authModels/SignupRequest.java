package com.example.SpringTestTask.models.authModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
	
	private String username;
	private String email;
	private Set<String> roles;
	private String password;
	private Date dateCreate;
}
