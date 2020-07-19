/** 
 * This is the "ServiceUtil" class. Which will be responsible for implementing some generalized methods in the service layer.
 * 
 * @author Tales Mateus de Oliveira Ferreira <talesmateus1999@hotmail.com>
 */
package com.pml.util;

import java.lang.reflect.Field;

public class ServiceUtil {

	/**
	 * Verify if the parameter exists in the requested class
	 * @param parameter String
	 * @param currentClass Class<?>
	 * @return
	 */
	public static boolean parameterExistsInTheClass(String parameter, Class<?> currentClass) {
		while(currentClass.getSuperclass() != null){
            for (Field field : currentClass.getDeclaredFields()) {            	
        		if(field.getName().toString().equals(parameter))
        			return true;
        	}
            currentClass = currentClass.getSuperclass();
        }
		return false;
    }
	
	
	
}
