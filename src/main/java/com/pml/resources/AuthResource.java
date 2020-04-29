/** 
 * This is the class "ComputerResource". Which will be to represent a REST controller of Computer model.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pml.dto.EmailDTO;
import com.pml.security.JWTUtil;
import com.pml.security.UserSS;
import com.pml.services.AuthService;
import com.pml.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private AuthService service;
	
	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
        // Allows to expose the "Authorization" header
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/forgot_password")
	public ResponseEntity<Void> forgotPassword(@Valid @RequestBody EmailDTO objectDTO) {
		this.service.sendNewPassword(objectDTO.getEmail());
		return ResponseEntity.noContent().build();
	}
	
	
}
