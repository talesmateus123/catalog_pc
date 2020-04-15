/** 
 * This is the class "Monitor", extended by the abstract class "Machine". Which will be to represent a monitor.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.pml.domain.enums.MachineType;

@Entity
public class Monitor extends Machine{
	private static final long serialVersionUID = 1L;

	public Monitor() {
		super();
		this.setMachineType(MachineType.MONITOR);
	}

	public Monitor(@Size(max = 10) String patrimonyId, @Size(max = 100) String manufacturer, 
			@NotEmpty @Size(max = 100) String model, String description, Integer location) {
		super(patrimonyId, MachineType.MONITOR, manufacturer, model, description, location);
	}
	
	

	
}
