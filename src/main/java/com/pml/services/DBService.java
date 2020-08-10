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

import com.pml.domain.Client;
import com.pml.domain.Computer;
import com.pml.domain.ComputerUser;
import com.pml.domain.Monitor;
import com.pml.domain.Printer;
import com.pml.domain.Processor;
import com.pml.domain.RamMemory;
import com.pml.domain.Sector;
import com.pml.domain.StorageDevice;
import com.pml.domain.enums.ArchitectureType;
import com.pml.domain.enums.OperatingSystem;
import com.pml.domain.enums.RamMemoryArchitecture;
import com.pml.domain.enums.StorageDeviceArchitecture;
import com.pml.domain.enums.StorageDeviceType;
import com.pml.domain.enums.UserProfile;
import com.pml.repositories.ClientRepository;
import com.pml.repositories.ComputerRepository;
import com.pml.repositories.ComputerUserRepository;
import com.pml.repositories.MonitorRepository;
import com.pml.repositories.PrinterRepository;
import com.pml.repositories.ProcessorRepository;
import com.pml.repositories.RamMemoryRepository;
import com.pml.repositories.SectorRepository;
import com.pml.repositories.StorageDeviceRepository;

@Service
public class DBService {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private ClientRepository userRepository;
	@Autowired
	private SectorRepository sectorRepository;
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
	@Autowired
	private PrinterRepository printerRepository;
	
	public void instantiateTestDatabase() {
		
		// Users creation
		Client user1 = new Client(null, "talesmateus1999@hotmail.com", "Tales Mateus", bCryptPasswordEncoder.encode("1234"));
		user1.addProfile(UserProfile.ADMIN);
		Client user2 = new Client(null, "renato123@gmail.com", "Renato Campos", bCryptPasswordEncoder.encode("1234"));
		userRepository.saveAll(Arrays.asList(user1, user2));
		/*
		// Sectors creation
		Sector sector1 = new Sector(null, "Administração", "(67)32328000");
		Sector sector2 = new Sector(null, "Recursos humanos", "(67)32328000");
		Sector sector3 = new Sector(null, "Contabilidade", "(67)32328000");
		Sector sector4 = new Sector(null, "Licitação", "(67)32328000");
		Sector sector5 = new Sector(null, "Gabinete do prefeito", "(67)32328000");
		Sector sector6 = new Sector(null, "CRAS", "(67)32328000");
		
		// Computer users creation
		ComputerUser computerUser1 = new ComputerUser(null, "José Rodrigues", "Gonzales", sector2, "jose.rodriges@ladario.gov.br");
		ComputerUser computerUser2 = new ComputerUser(null, "José Rodrigues", "Gonzales", sector1, "jose.rodriges@ladario.gov.br");
		ComputerUser computerUser3 = new ComputerUser(null, "José Rodrigues", "Gonzales", sector3, "jose.rodriges@ladario.gov.br");
		ComputerUser computerUser4 = new ComputerUser(null, "José Rodrigues", "Gonzales", sector6, "jose.rodriges@ladario.gov.br");

		// Monitors creation
		Monitor monitor1 = new Monitor(null, "00012", new Date(), new Date(), null, null, null, sector2, true, null);
		Monitor monitor2 = new Monitor(null, "02013", new Date(), new Date(), null, null, null, sector6, true, null);
		
		// Computers creation		
		Computer computer1 = new Computer(null, "02832", new Date(), new Date(), "Fabricante", "Modelo", "Descrição", sector2, 
				true, "ip", "mac", "hostName", "motherBoardName", null, true, false, "cabinetModel", OperatingSystem.WINDOWS_7, 
				ArchitectureType.AMD64, true, 0.0, 0.0, monitor1);
		
		Computer computer2 = new Computer(null, "138354", new Date(), new Date(), "Fabricante", "Modelo", "Descrição", sector2, 
				true, "ip", "mac", "hostName", "motherBoardName", null, true, false, "cabinetModel", OperatingSystem.WINDOWS_7, 
				ArchitectureType.AMD64, true, 0.0, 0.0, monitor1);
		
		computer1.addComputerUser(computerUser1);
		computer1.addComputerUser(computerUser2);
		computer2.addComputerUser(computerUser3);
		computer2.addComputerUser(computerUser4);	
		
		// Processors creation
		Processor processor1 = new Processor(null, new Date(), new Date(), "Intel", "model", "description", true, 
				"i7 7000U ", ArchitectureType.AMD64, computer1);
		Processor processor2 = new Processor(null, new Date(), new Date(), "Intel", "model", "description", true, 
				"i7 7000U ", ArchitectureType.AMD64, computer2);
				
		// Ram memories creation
		RamMemory ramMemory1 = new RamMemory(null, new Date(), new Date(), null, null, null, true, 1.0, RamMemoryArchitecture.DDR3, computer1);
		RamMemory ramMemory2 = new RamMemory(null, new Date(), new Date(), null, null, null, true, 2.0, RamMemoryArchitecture.DDR3, computer1);
		RamMemory ramMemory3 = new RamMemory(null, new Date(), new Date(), null, null, null, true, 0.5, RamMemoryArchitecture.DDR2, computer2);
		RamMemory ramMemory4 = new RamMemory(null, new Date(), new Date(), null, null, null, true, 1.0, RamMemoryArchitecture.DDR3, computer2);
		
		// Storage devices creation
		StorageDevice storageDevice1 = new StorageDevice(null, new Date(), new Date(), null, null, null, true, 1000.0, StorageDeviceArchitecture.SATA, StorageDeviceType.HD, computer1);
		StorageDevice storageDevice2 = new StorageDevice(null, new Date(), new Date(), null, null, null, true, 1000.0, StorageDeviceArchitecture.SATA, StorageDeviceType.HD, computer1);
		StorageDevice storageDevice3 = new StorageDevice(null, new Date(), new Date(), null, null, null, true, 2000.0, StorageDeviceArchitecture.SATA, StorageDeviceType.HD, computer2);
		StorageDevice storageDevice4 = new StorageDevice(null, new Date(), new Date(), null, null, null, true, 500.0, StorageDeviceArchitecture.SATA, StorageDeviceType.HD, computer2);
		
		// Setting all computer attributes
		computer1.addRamMemory(ramMemory1);
		computer1.addRamMemory(ramMemory2);
		computer1.addStorageDevice(storageDevice1);
		computer1.addStorageDevice(storageDevice2);
		
		computer2.addRamMemory(ramMemory3);
		computer2.addRamMemory(ramMemory4);
		computer2.addStorageDevice(storageDevice3);
		computer2.addStorageDevice(storageDevice4);
		
		// Printers creation
		Printer printer1 = new Printer(null, "PML0000", new Date(), new Date(), "manufacturer", "model", 
				"description", sector1, true, "ipAddress", "macAddres", "hostName");
		
		// Setting data on database
		userRepository.saveAll(Arrays.asList(
				user1, user2
				));
		
		sectorRepository.saveAll(Arrays.asList(
				sector1, sector2, sector3, sector4, sector5, sector6
				));
		computerUserRepository.saveAll(Arrays.asList(computerUser1, computerUser2, computerUser3, computerUser4));
		monitorRepository.saveAll(Arrays.asList(
				monitor1, monitor2
				));
		computerRepository.saveAll(Arrays.asList(
				computer1, computer2
				));
		
		processorRepository.saveAll(Arrays.asList(processor1, processor2));
		ramMemoryRepository.saveAll(Arrays.asList(ramMemory1, ramMemory2, ramMemory3, ramMemory4));
		storageDeviceRepository.saveAll(Arrays.asList(storageDevice1, storageDevice2, storageDevice3, storageDevice4));
		printerRepository.saveAll(Arrays.asList(printer1));
		
		
		//userRepository.saveAll(Arrays.asList(user1, user2));
		 */
	}
	
	
	
}
