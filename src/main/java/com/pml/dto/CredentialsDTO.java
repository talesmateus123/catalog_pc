/** 
 * This is the class "UserDTO". That class will be to represent a computer user dto.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.pml.domain.User;

public class CredentialsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "This field cannot be empty")
	private String login;
	@NotEmpty(message = "This field cannot be empty")
	private String password;
	
	public CredentialsDTO() {		
	}
	
	public CredentialsDTO(User user) {
		this.login = user.getLogin();
		this.password = user.getPassword();
	}
	
	public CredentialsDTO(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
