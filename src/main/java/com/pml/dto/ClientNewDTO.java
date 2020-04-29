/** 
 * This is the class "UserDTO". That class will be to represent a computer user dto.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.pml.domain.Client;

public class ClientNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "This field cannot be empty")
	private String email;
	@NotEmpty(message = "This field cannot be empty")
	private String name;
	@NotEmpty(message = "This field cannot be empty")
	private String password;
	
	public ClientNewDTO() {		
	}
	
	public ClientNewDTO(Client user) {
		this.email = user.getEmail();
		this.name = user.getName();
		this.password = user.getPassword();
	}
	
	public ClientNewDTO(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
