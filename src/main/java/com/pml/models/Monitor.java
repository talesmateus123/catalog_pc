/** 
 * This is the class "Monitor", extended by the abstract class "Machine". Which will be to represent a monitor.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class Monitor extends Machine{
	private static final long serialVersionUID = 1L;
	@OneToOne(mappedBy = "monitor", cascade = CascadeType.ALL,
    fetch = FetchType.LAZY, optional = false)
	private Computer computer;
	
	public Monitor() {
		super();
		this.setMachineType("monitor");
	}

	public Computer getComputer() {
		return computer;
	}
	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	
	
}
