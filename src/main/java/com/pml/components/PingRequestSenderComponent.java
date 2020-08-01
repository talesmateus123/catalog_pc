/** 
 * This is the "PingRequestSenderService" class. Which will be responsible for implementing the ping request methods for the equipment.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.components;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pml.domain.Computer;
import com.pml.domain.Printer;
import com.pml.services.ComputerService;
import com.pml.services.PrinterService; 

@Component @EnableScheduling
public class PingRequestSenderComponent {
	@Autowired
    private ComputerService computerService;
	@Autowired
    private PrinterService printerService;

	// Default constants.
	private final int SECOND = 1000;
    private final int MINITE = SECOND * 60;
    private final int HOUR = MINITE * 60;
    
    // Custom constants
    private final int initialDelay = 5 * SECOND;
    private final int fixedDelay = 10 * MINITE;
    private final int pingDelay = 2 * SECOND;
    
	Logger logger = LoggerFactory.getLogger(PingRequestSenderComponent.class);
	
	/**
	 * It gets the list of equipment and its respective IP address, then sends a ping request to those IP addresses and, if it gets a response, updates the online attribute with a true value, otherwise it fills in with a false value. After 60 seconds, repeat the operation.
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @return void
	 */
    @Scheduled(initialDelay = initialDelay, fixedDelay = fixedDelay)
	private void sendPingRequestsToEquipmentsAndUpdate() throws InterruptedException, UnknownHostException, IOException {
    	List<Computer> computers = getComputersList();
    	List<Printer> printers = getPrintersList();
		logger.info("Looking for equipment online");
		for(Computer computer: computers) {
			boolean online = this.isPingRequestReceived(computer.getIpAddress());
			computer.setOnline(online);
			computerService.update(computer);
		}
		for(Printer printer: printers) {
			boolean online = this.isPingRequestReceived(printer.getIpAddress());
			printer.setOnline(online);
			printerService.update(printer);
		}
		logger.info("Online equipment search completed");
	}
    
	/**
	 * Updates the list of computers for the respective service.
	 * @return computers List
	 */
	private List<Computer> getComputersList() {
		List<Computer> computers = new ArrayList<>();

		for(Computer computer: this.computerService.findAll()) {
			if(!computer.getIpAddress().equals("0.0.0.0"))
				computers.add(computer);
		}
		return computers;
	}
	
	/**
	 * Updates the list of printers for the respective service.
	 * @return printers List
	 */
	private List<Printer> getPrintersList() {
		List<Printer> printers = new ArrayList<>();

		for(Printer printer: this.printerService.findAll()) {
			if(!printer.getIpAddress().equals("0.0.0.0"))
				printers.add(printer);
		}
		return printers;
	}
	
	/**
	 * Send a ping request for a requested ip address.
	 * @param ipAddress String
	 * @return boolean
	 */
	private boolean isPingRequestReceived(String ipAddress) throws UnknownHostException, IOException { 
		InetAddress geek = InetAddress.getByName(ipAddress); 
		logger.info("Sending ping request for " + ipAddress);
		if (geek.isReachable(pingDelay)) {
			//logger.info("Result: successful, ping request to " + ipAddress + " was received");
			return true;
		}
		else {
	        //logger.info("Result: unsuccessful, ping request to " + ipAddress + " was lost");
			return false;
		}
	}
	
	
	
}
