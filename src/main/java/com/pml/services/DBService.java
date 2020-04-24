/** 
 * This is the class "DBService". Which will be able to instantiate all data domain and send it to database through their respective repositories.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.services;

import org.springframework.stereotype.Service;

import com.pml.repositories.ComputerRepository;
import com.pml.repositories.ComputerUserRepository;
import com.pml.repositories.MonitorRepository;

@Service
public class DBService {
	private ComputerRepository computerRepository;
	private ComputerUserRepository computerUserRepository;
	private MonitorRepository monitorRepository;
	
	public void instantiateTestDatabase() {
		/*
		//	Computer 1
		Computer computer1 = new Computer(null, "patrimonyId", new Date(), new Date(), "manufacturer", "model", 
				"description", Sector.ADMIN, true, "ipAddress", "hostName", "motherBoardName", null, true, 
				"cabinetModel", OperatingSystemType.WINDOWS_10, ArchitectureType.AMD_64, true, null);
		
		// Computer basic attributes
		Processor processor1 = new Processor(null, new Date(), new Date(), "manufacturer", 
				"model", "description", true, "processorNumber", null);
		
		StorageDevice storage11 = new StorageDevice(null, new Date(), new Date(), "manufacturer", "model", 
				"description", true, 2048.0, StorageDeviceArchitecture.SATA, null);
		StorageDevice storage12 = new StorageDevice(null, new Date(), new Date(), "manufacturer", "model", 
				"description", true, 2048.0, StorageDeviceArchitecture.SATA, null);
		
		RamMemory ramMemory11 = new RamMemory(null, new Date(), new Date(), "manufacturer", "model", 
				"description", true, 2048.0, RamMemoryArchitecture.DDR2, null);
		RamMemory ramMemory12 = new RamMemory(null, new Date(), new Date(), "manufacturer", "model", 
				"description", true, 2048.0, RamMemoryArchitecture.DDR2, null);
		
		Monitor monitor1 = new Monitor(null, "patrimonyId", new Date(), new Date(), 
				"manufacturer", "model", "description", Sector.ADMIN, true, null);
		
		ComputerUser computerUser1 = new ComputerUser(null, "name", "lastName", Sector.RH, "email@mail.com");
		
		computer1.setProcessor(processor1);
		computer1.setMonitor(monitor1);
		computer1.setStorageDevices(Arrays.asList(storage11, storage12));
		computer1.setRamMemories(Arrays.asList(ramMemory11, ramMemory12));
		computer1.addComputerUser(computerUser1);
	
		//	Computer 2
		Computer computer2 = new Computer(null, "patrimonyId", new Date(), new Date(), "manufacturer", "model", 
				"description", Sector.ADMIN, true, "ipAddress", "hostName", "motherBoardName", null, true, 
				"cabinetModel", OperatingSystemType.WINDOWS_10, ArchitectureType.AMD_64, true, null);
		
		// Computer basic attributes
		Processor processor2 = new Processor(null, new Date(), new Date(), "manufacturer", 
				"model", "description", true, "processorNumber", null);
		
		StorageDevice storage21 = new StorageDevice(null, new Date(), new Date(), "manufacturer", "model", 
				"description", true, 2048.0, StorageDeviceArchitecture.SATA, null);
		StorageDevice storage22 = new StorageDevice(null, new Date(), new Date(), "manufacturer", "model", 
				"description", true, 2048.0, StorageDeviceArchitecture.SATA, null);
		
		RamMemory ramMemory21 = new RamMemory(null, new Date(), new Date(), "manufacturer", "model", 
				"description", true, 2048.0, RamMemoryArchitecture.DDR2, null);
		RamMemory ramMemory22 = new RamMemory(null, new Date(), new Date(), "manufacturer", "model", 
				"description", true, 2048.0, RamMemoryArchitecture.DDR2, null);
		
		Monitor monitor2 = new Monitor(null, "patrimonyId", new Date(), new Date(), 
				"manufacturer", "model", "description", Sector.ADMIN, true, null);
		//
		ComputerUser computerUser2 = new ComputerUser(null, "name", "lastName", Sector.RH, "email@mail.com");
		
		computer2.setProcessor(processor2);
		computer2.setMonitor(monitor2);
		computer2.setStorageDevices(Arrays.asList(storage21, storage22));
		computer2.setRamMemories(Arrays.asList(ramMemory21, ramMemory22));
		computer2.addComputerUser(computerUser2);
		*/
		
	}
}
