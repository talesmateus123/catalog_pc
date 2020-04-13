/** 
 * This is the class "Monitor", extended by the abstract class "Machine". Which will be to represent a monitor.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.models;

import javax.persistence.Entity;

@Entity
public class Monitor extends Machine{
	private static final long serialVersionUID = 1L;

	public Monitor() {
		super();
		this.setMachineType("monitor");
	}

	
}
