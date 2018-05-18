package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.model.Customer;
import com.springboot.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class JavaWebCustomerController {

	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value= { "/form" } , method=RequestMethod.GET )
	public ModelAndView getForm() {
		ModelAndView model = new ModelAndView();
		Customer customer = new Customer();
		model.addObject("customers", customer);
		model.setViewName("/customer/form");
		return model;
		
	}
	
	@RequestMapping(value = { "/form" } , method=RequestMethod.POST)
	public ModelAndView saveCustomer(@ModelAttribute("customers") Customer customer , BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		Customer existCustomer = customerService.getCustomerByEmail(customer.getEmail());
		if( existCustomer != null) {
			bindingResult.rejectValue("email", "customer.error","email taken");
		}
		if( bindingResult.hasErrors()) {
			model.addObject("emsg","Email Already Exist");
			model.setViewName("/customer/form");
			
		} else {
			customerService.getAddCustomer(customer);
			model.addObject("customers", new Customer() );
			model.addObject("msg", "Account Created");
			model.setViewName("/customer/form");
		}
		return model;
	}
	
	@RequestMapping(value = {"/edit/{id}"} )
	public ModelAndView editCustomer(@PathVariable("id")Integer id) {
		ModelAndView model = new ModelAndView();
		Customer customer = customerService.getCustomerById(id);
		model.addObject("customers",customer);
		model.setViewName("/customer/form");
		return model;
	}
	
	
	
	
}
