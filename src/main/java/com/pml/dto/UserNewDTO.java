/** 
 * This is the class "UserDTO". That class will be to represent a computer user dto.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.pml.domain.User;

public class UserNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "This field cannot be empty")
	private String email;
	@NotEmpty(message = "This field cannot be empty")
	private String password;
	
	public UserNewDTO() {		
	}
	
	public UserNewDTO(User user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
	}
	
	public UserNewDTO(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
