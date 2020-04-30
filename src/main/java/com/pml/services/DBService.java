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
import com.pml.domain.Printer;
import com.pml.domain.Processor;
import com.pml.domain.RamMemory;
import com.pml.domain.StorageDevice;
import com.pml.domain.Client;
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
import com.pml.repositories.PrinterRepository;
import com.pml.repositories.ProcessorRepository;
import com.pml.repositories.RamMemoryRepository;
import com.pml.repositories.StorageDeviceRepository;
import com.pml.repositories.ClientRepository;

@Service
public class DBService {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private ClientRepository userRepository;
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
		
		// Monitors creation
		Monitor monitor1 = new Monitor(null, "00012", new Date(), new Date(), null, null, null, Sector.ADMIN, true, null);
		Monitor monitor2 = new Monitor(null, "02013", new Date(), new Date(), null, null, null, Sector.ADMIN, true, null);
		Monitor monitor3 = new Monitor(null, "01012", new Date(), new Date(), null, null, null, Sector.ADMIN, true, null);
		Monitor monitor4 = new Monitor(null, "03013", new Date(), new Date(), null, null, null, Sector.RH, true, null);
		Monitor monitor5 = new Monitor(null, "05012", new Date(), new Date(), null, null, null, Sector.ADMIN, true, null);
		Monitor monitor6 = new Monitor(null, "06013", new Date(), new Date(), null, null, null, Sector.ADMIN, true, null);
		Monitor monitor7 = new Monitor(null, "0012", new Date(), new Date(), null, null, null, Sector.ADMIN, true, null);
		Monitor monitor8 = new Monitor(null, "08001323", new Date(), new Date(), null, null, null, Sector.RH, true, null);
		Monitor monitor9 = new Monitor(null, "07012", new Date(), new Date(), null, null, null, Sector.ADMIN, true, null);
		Monitor monitor10 = new Monitor(null, "080013", new Date(), new Date(), null, null, null, Sector.ADMIN, true, null);
		Monitor monitor11 = new Monitor(null, "009012", new Date(), new Date(), null, null, null, Sector.ADMIN, true, null);
		Monitor monitor12 = new Monitor(null, "000013", new Date(), new Date(), null, null, null, Sector.RH, true, null);
		Monitor monitor13 = new Monitor(null, "000112", new Date(), new Date(), null, null, null, Sector.ADMIN, true, null);
		Monitor monitor14 = new Monitor(null, "000113", new Date(), new Date(), null, null, null, Sector.ADMIN, true, null);
		Monitor monitor15 = new Monitor(null, "20012", new Date(), new Date(), null, null, null, Sector.RH, true, null);
		Monitor monitor16 = new Monitor(null, "30013", new Date(), new Date(), null, null, null, Sector.ADMIN, true, null);
		Monitor monitor17 = new Monitor(null, "40012", new Date(), new Date(), null, null, null, Sector.ADMIN, true, null);
		Monitor monitor18 = new Monitor(null, "50013", new Date(), new Date(), null, null, null, Sector.ADMIN, true, null);
		Monitor monitor19 = new Monitor(null, "60012", new Date(), new Date(), null, null, null, Sector.RH, true, null);
		Monitor monitor20 = new Monitor(null, "120013", new Date(), new Date(), null, null, null, Sector.ADMIN, true, null);
		
		// Processors creation
		Processor processor1 = new Processor(null, new Date(), new Date(), "Intel", "model", "description", true, 
				"i7 7000U ", ArchitectureType.AMD64, null);
		Processor processor2 = new Processor(null, new Date(), new Date(), "Intel", "model", "description", true, 
				"i7 7000U ", ArchitectureType.AMD64, null);
		
		// Computer users creation
		ComputerUser computerUser1 = new ComputerUser(null, "José Rodrigues", "Gonzales", Sector.ADMIN, "jose.rodriges@ladario.gov.br");
		ComputerUser computerUser2 = new ComputerUser(null, "José Rodrigues", "Gonzales", Sector.ADMIN, "jose.rodriges@ladario.gov.br");
		ComputerUser computerUser3 = new ComputerUser(null, "José Rodrigues", "Gonzales", Sector.ADMIN, "jose.rodriges@ladario.gov.br");
		ComputerUser computerUser4 = new ComputerUser(null, "José Rodrigues", "Gonzales", Sector.ADMIN, "jose.rodriges@ladario.gov.br");
				
		// Computers creation		
		Computer computer1 = new Computer(null, "06770", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor1, true, null, OperatingSystem.WINDOWS_7, ArchitectureType.I386, false, monitor1);
		Computer computer2 = new Computer(null, "07178", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor2, true, null, OperatingSystem.WINDOWS_10, ArchitectureType.I386, false, monitor2);
		Computer computer3 = new Computer(null, "06771", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor1, true, null, OperatingSystem.WINDOWS_7, ArchitectureType.AMD64, false, monitor3);
		Computer computer4 = new Computer(null, "74321", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor2, true, null, OperatingSystem.WINDOWS_10, ArchitectureType.I386, false, monitor4);
		Computer computer5 = new Computer(null, "8653", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor1, true, null, OperatingSystem.WINDOWS_7, ArchitectureType.I386, false, monitor5);
		Computer computer6 = new Computer(null, "2114", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor2, true, null, OperatingSystem.WINDOWS_10, ArchitectureType.I386, false, monitor6);
		Computer computer7 = new Computer(null, "06789", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor1, true, null, OperatingSystem.LINUX_DISTRO, ArchitectureType.I386, false, monitor7);
		Computer computer8 = new Computer(null, "77454", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor2, true, null, OperatingSystem.WINDOWS_10, ArchitectureType.I386, false, monitor8);
		Computer computer9 = new Computer(null, "771469", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor2, true, null, OperatingSystem.WINDOWS_10, ArchitectureType.AMD64, false, monitor9);
		Computer computer10 = new Computer(null, "21844", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor1, true, null, OperatingSystem.WINDOWS_7, ArchitectureType.I386, false, monitor10);
		Computer computer11 = new Computer(null, "217643", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor2, true, null, OperatingSystem.WINDOWS_10, ArchitectureType.I386, false, monitor11);
		Computer computer12 = new Computer(null, "5757", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor1, true, null, OperatingSystem.WINDOWS_7, ArchitectureType.I386, false, monitor12);
		Computer computer13 = new Computer(null, "75875", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor2, true, null, OperatingSystem.WINDOWS_VISTA, ArchitectureType.AMD64, false, monitor13);
		Computer computer14 = new Computer(null, "789654", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor2, true, null, OperatingSystem.WINDOWS_10, ArchitectureType.I386, false, monitor14);
		Computer computer15 = new Computer(null, "15951", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor2, true, null, OperatingSystem.WINDOWS_XP, ArchitectureType.I386, false, monitor15);
		Computer computer16 = new Computer(null, "95126", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor2, true, null, OperatingSystem.WINDOWS_10, ArchitectureType.I386, false, monitor16);
		Computer computer17 = new Computer(null, "78965", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor2, true, null, OperatingSystem.WINDOWS_7, ArchitectureType.AMD64, false, monitor17);
		Computer computer18 = new Computer(null, "78542", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor2, true, null, OperatingSystem.WINDOWS_10, ArchitectureType.I386, false, monitor18);
		Computer computer19 = new Computer(null, "745874", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor2, true, null, OperatingSystem.LINUX_DISTRO, ArchitectureType.I386, false, monitor19);
		Computer computer20 = new Computer(null, "7854", new Date(), new Date(), "Micron Technology", null, null, Sector.ADMIN, 
				true, null, null, null, processor2, true, null, OperatingSystem.WINDOWS_10, ArchitectureType.AMD64, false, monitor20);
		
		computer1.addComputerUser(computerUser1);
		computer1.addComputerUser(computerUser2);
		computer2.addComputerUser(computerUser3);
		computer2.addComputerUser(computerUser4);		
		
		// Ram memories creation
		RamMemory ramMemory1 = new RamMemory(null, new Date(), new Date(), null, null, null, true, 1024, RamMemoryArchitecture.DDR3, computer1);
		RamMemory ramMemory2 = new RamMemory(null, new Date(), new Date(), null, null, null, true, 1024, RamMemoryArchitecture.DDR3, computer1);
		RamMemory ramMemory3 = new RamMemory(null, new Date(), new Date(), null, null, null, true, 1024, RamMemoryArchitecture.DDR3, computer2);
		RamMemory ramMemory4 = new RamMemory(null, new Date(), new Date(), null, null, null, true, 1024, RamMemoryArchitecture.DDR3, computer2);
		
		// Storage devices creation
		StorageDevice storageDevice1 = new StorageDevice(null, new Date(), new Date(), null, null, null, true, 102400, StorageDeviceArchitecture.SATA, StorageDeviceType.HD, computer1);
		StorageDevice storageDevice2 = new StorageDevice(null, new Date(), new Date(), null, null, null, true, 102400, StorageDeviceArchitecture.SATA, StorageDeviceType.HD, computer1);
		StorageDevice storageDevice3 = new StorageDevice(null, new Date(), new Date(), null, null, null, true, 102400, StorageDeviceArchitecture.SATA, StorageDeviceType.HD, computer2);
		StorageDevice storageDevice4 = new StorageDevice(null, new Date(), new Date(), null, null, null, true, 102400, StorageDeviceArchitecture.SATA, StorageDeviceType.HD, computer2);
		
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
				"description", Sector.ADMIN, true, "ipAddress", "hostName");
		
		// Setting data on database
		userRepository.saveAll(Arrays.asList(user1, user2));
		monitorRepository.saveAll(Arrays.asList(
				monitor1, monitor2, monitor3, monitor4, monitor5, monitor6, monitor7, monitor8, monitor9, monitor10,
				monitor11, monitor12, monitor13, monitor14, monitor15, monitor16, monitor17, monitor18, monitor19, monitor20
				));
		processorRepository.saveAll(Arrays.asList(processor1, processor2));
		computerUserRepository.saveAll(Arrays.asList(computerUser1, computerUser2, computerUser3, computerUser4));
		computerRepository.saveAll(Arrays.asList(
				computer1, computer2, computer3, computer4, computer5, computer6, computer7, computer8, computer9, computer10,
				computer11, computer12, computer13, computer14, computer15, computer16, computer17, computer18, computer19, computer20
				));
		ramMemoryRepository.saveAll(Arrays.asList(ramMemory1, ramMemory2, ramMemory3, ramMemory4));
		storageDeviceRepository.saveAll(Arrays.asList(storageDevice1, storageDevice2, storageDevice3, storageDevice4));
		printerRepository.saveAll(Arrays.asList(printer1));
	}
	
	
}
