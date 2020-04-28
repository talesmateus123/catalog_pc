package com.pml.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pml.domain.Client;
import com.pml.repositories.UserRepository;
import com.pml.security.UserSS;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

	@Autowired 
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {	
		Optional<Client> optionalUser = userRepository.findByEmail(email);
		if(optionalUser.isEmpty()) 
			throw new UsernameNotFoundException(email);
		Client user = optionalUser.get();
		return new UserSS(user.getId(), user.getEmail(), user.getPassword(), user.getProfiles());
	}
	

}
