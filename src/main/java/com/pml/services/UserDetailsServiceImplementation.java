package com.pml.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pml.domain.User;
import com.pml.security.UserSS;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

	@Autowired 
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.findByEmail(email);
		if(user == null) 
			throw new UsernameNotFoundException(email);
		
		return new UserSS(user.getId(), user.getEmail(), user.getPassword(), user.getProfiles());
	}

}
