/** 
 * This is the class "DBService". Which will be able to instantiate all data domain and send it to database through their respective repositories.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pml.domain.Computer;
import com.pml.domain.ComputerUser;
import com.pml.domain.Monitor;
import com.pml.domain.Processor;
import com.pml.domain.RamMemory;
import com.pml.domain.StorageDevice;
import com.pml.domain.User;
import com.pml.domain.enums.ArchitectureType;
import com.pml.domain.enums.OperatingSystem;
import com.pml.domain.enums.RamMemoryArchitecture;
import com.pml.domain.enums.Sector;
import com.pml.domain.enums.StorageDeviceArchitecture;
import com.pml.domain.enums.StorageDeviceType;
import com.pml.domain.enums.UserProfile;
import com.pml.repositories.ComputerRepository;
import com.pml.repositories.ComputerUserRepository;
import com.pml.repositories.MonitorRepository;
import com.pml.repositories.ProcessorRepository;
import com.pml.repositories.RamMemoryRepository;
import com.pml.repositories.StorageDeviceRepository;
import com.pml.repositories.UserRepository;

@Service
public class DBService {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ComputerRepository computerRepository;
	@Autowired
	private MonitorRepository monitorRepository;
	@Autowired
	private RamMemoryRepository ramMemoryRepository;
	@Autowired
	private StorageDeviceRepository storageDeviceRepository;
	@Autowired
	private ProcessorRepository processorRepository;
	@Autowired
	private ComputerUserRepository computerUserRepository;
	
	public void instantiateTestDatabase() {
		// Users creation
		User user1 = new User(null, "tales123",  bCryptPasswordEncoder.encode("1234"));
		user1.addProfile(UserProfile.ADMIN);
		User user2 = new User(null, "renato123",  bCryptPasswordEncoder.encode("1234"));
		userRepository.saveAll(Arrays.asList(user1, user2));
		
		// Monitors creation
		Monitor monitor1 = new Monitor(null, "00012", new Date(), new Date(), null, null, null, Sector.ADMIN, true, null);
		Monitor monitor2 = new Monitor(null, "00013", new Date(), new Date(), null, null, null, Sector.ADMIN, true, null);
		
		monitor1 = monitorRepository.save(monitor1);
		monitor2 = monitorRepository.save(monitor2);
		
		// Ram memories creation
		RamMemory ramMemory1 = new RamMemory(null, new Date(), new Date(), null, null, null, true, 1024, RamMemoryArchitecture.DDR3, null);
		RamMemory ramMemory2 = new RamMemory(null, new Date(), new Date(), null, null, null, true, 1024, RamMemoryArchitecture.DDR3, null);
		RamMemory ramMemory3 = new RamMemory(null, new Date(), new Date(), null, null, null, true, 1024, RamMemoryArchitecture.DDR3, null);
		RamMemory ramMemory4 = new RamMemory(null, new Date(), new Date(), null, null, null, true, 1024, RamMemoryArchitecture.DDR3, null);
		ramMemory1 = ramMemoryRepository.save(ramMemory1);
		ramMemory2 = ramMemoryRepository.save(ramMemory2);
		ramMemory3 = ramMemoryRepository.save(ramMemory3);
		ramMemory4 = ramMemoryRepository.save(ramMemory4);
		
		// Storage devices creation
		StorageDevice storageDevice1 = new StorageDevice(null, new Date(), new Date(), null, null, null, true, 102400, StorageDeviceArchitecture.SATA, StorageDeviceType.HD, null);
		StorageDevice storageDevice2 = new StorageDevice(null, new Date(), new Date(), null, null, null, true, 102400, StorageDeviceArchitecture.SATA, StorageDeviceType.HD, null);
		StorageDevice storageDevice3 = new StorageDevice(null, new Date(), new Date(), null, null, null, true, 102400, StorageDeviceArchitecture.SATA, StorageDeviceType.HD, null);
		StorageDevice storageDevice4 = new StorageDevice(null, new Date(), new Date(), null, null, null, true, 102400, StorageDeviceArchitecture.SATA, StorageDeviceType.HD, null);
		storageDevice1 = storageDeviceRepository.save(storageDevice1);
		storageDevice2 = storageDeviceRepository.save(storageDevice2);
		storageDevice3 = storageDeviceRepository.save(storageDevice3);
		storageDevice4 = storageDeviceRepository.save(storageDevice4);
		
		// Processors creation
		Processor processor1 = new Processor(null, new Date(), new Date(), "Intel", "model", "description", true, 
				"i7 7000U ", ArchitectureType.AMD64, null);
		Processor processor2 = new Processor(null, new Date(), new Date(), "Intel", "model", "description", true, 
				"i7 7000U ", ArchitectureType.AMD64, null);
		processorRepository.save(processor1);
		processorRepository.save(processor2);
		
		// Computer users creation
		ComputerUser computerUser1 = new ComputerUser(null, "José Rodrigues", "Gonzales", Sector.ADMIN, "jose.rodriges@ladario.gov.br");
		ComputerUser computerUser2 = new ComputerUser(null, "José Rodrigues", "Gonzales", Sector.ADMIN, "jose.rodriges@ladario.gov.br");
		ComputerUser computerUser3 = new ComputerUser(null, "José Rodrigues", "Gonzales", Sector.ADMIN, "jose.rodriges@ladario.gov.br");
		ComputerUser computerUser4 = new ComputerUser(null, "José Rodrigues", "Gonzales", Sector.ADMIN, "jose.rodriges@ladario.gov.br");
		computerUserRepository.saveAll(Arrays.asList(computerUser1, computerUser2, computerUser3, computerUser4));
		
		// Computers creation		
		Computer computer1 = new Computer(null, "06770", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor1, true, null, OperatingSystem.WINDOWS_7, ArchitectureType.I386, false, monitor1);
		Computer computer2 = new Computer(null, "07178", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor2, true, null, OperatingSystem.WINDOWS_10, ArchitectureType.I386, false, monitor2);
		
		// Setting computer attributes
		computer1.setMonitor(monitor1);
		computer1.addRamMemory(ramMemory1);
		computer1.addRamMemory(ramMemory2);
		computer1.addStorageDevice(storageDevice1);
		computer1.addStorageDevice(storageDevice2);
		computer1.addComputerUser(computerUser1);
		computer1.addComputerUser(computerUser2);
		
		computer2.addRamMemory(ramMemory3);
		computer2.addRamMemory(ramMemory4);
		computer2.addStorageDevice(storageDevice3);
		computer2.addStorageDevice(storageDevice4);
		computer2.addComputerUser(computerUser3);
		computer2.addComputerUser(computerUser4);		
		
		computerRepository.saveAll(Arrays.asList(computer1, computer2));
		
	
	}
	
	
}
