/** 
 * This is the class "UserDTO". That class will be to represent a computer client dto.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pml.domain.Client;

public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String email;
	private String name;
	@JsonIgnore
	private String password;
	
	public ClientDTO() {		
	}
	
	public ClientDTO(Client client) {
		this.id = client.getId();
		this.email = client.getEmail();
		this.name = client.getName();
		this.password = client.getPassword();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
