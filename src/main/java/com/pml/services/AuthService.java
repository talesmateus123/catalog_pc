/** 
 * This is the class "UserService". Which will be able to intermediate the repository class and the service class.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pml.domain.Client;
import com.pml.repositories.ClientRepository;
import com.pml.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private EmailService emailService;
	private Random random = new Random();
	
	public void sendNewPassword(String email) {
		Optional<Client> object = this.clientRepository.findByEmail(email);
		if(object.isPresent())
			throw new ObjectNotFoundException("Email not found");
		String newPassword = newPassword();
		Client client = object.get();
		client.setPassword(bCryptPasswordEncoder.encode(newPassword));
		this.clientRepository.save(client);
		emailService.sendNewPasswordHtmlEmail(client, newPassword);
		
	}

	private String newPassword() {
		char[] vet = new char[10];
		for(int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		if(opt == 0)
			return (char) (random.nextInt(10) + 48);
		if(opt == 1)
			return (char) (random.nextInt(26) + 65);
		else
			return (char) (random.nextInt(26) + 97);
	}

	
	
}
