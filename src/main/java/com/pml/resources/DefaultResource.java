package com.pml.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/")
public class DefaultResource {
	
	/**
	 * Redirects to swagger API documentation 
	 * @return ModelAndView
	 */
	@GetMapping
	public ModelAndView redirectToSwaggerAPIDocumentation() {
		ModelAndView modelAndView = new ModelAndView("main/index");
		modelAndView.addObject("title", "Restful API for computer cataloging");
		return modelAndView;
	}
	
	
	
}
