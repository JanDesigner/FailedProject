package com.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.model.Customer;
import com.springboot.model.User;
import com.springboot.service.CustomerService;
import com.springboot.service.UserService;

@Controller
public class JavaWebController {
	
	@Autowired
	UserService userService;
	@Autowired
	CustomerService customerService;
	
	  @InitBinder
	    public void initBinder(WebDataBinder binder) {
	  
	          binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	  
	      }

	
	@RequestMapping( value = {"/" , "/login"} )
	public ModelAndView defaultLogin() {
		ModelAndView model = new ModelAndView();
		model.addObject("msg" , "Invalid Email/Password");
		model.setViewName("/login/login");
		return model;
	}
	@RequestMapping( value = {"/info"} )
	public ModelAndView info() {
		ModelAndView model = new ModelAndView();
		model.addObject("msg" , "Invalid Email/Password");
		model.setViewName("/login/info");
		return model;
	}
	@RequestMapping( value = {"/signup"} , method=RequestMethod.GET)
	public ModelAndView registerForm() {
		ModelAndView model = new ModelAndView();
		User user = new User();
		model.addObject("users",user);
		model.setViewName("/register/register");
		return model;
	}
	@RequestMapping( value = {"/signup"} , method=RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("users")@Valid User user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		User userExist = userService.getUserByEmail(user.getEmail());
		if( userExist != null ) {
			bindingResult.rejectValue("email", "error.user", "Email AlreadyExist");
		}
		if( bindingResult.hasErrors()) {
			model.addObject("emsg","Email Already Exist");
			model.setViewName("/register/register");
		} else {
			userService.getSaveUser(user);
			model.addObject("msg" , "Account Created");
			model.addObject("users", new User());
			model.setViewName("/register/register");
		}
		return model;
		
	}
	
	@RequestMapping( value = {"/home"} , method=RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		model.addObject("myemail" , user.getFirstName() + " " + user.getLastName());
		model.setViewName("/web/home");
		return model;
		
		
	}
	
	@RequestMapping( value = {"/access_denied"})
	public ModelAndView denied() {
		return new ModelAndView("/error/home");
	}
	
	@RequestMapping( value = {"/customer/list"})
	public ModelAndView denieds() {
		ModelAndView model = new ModelAndView();
		List<Customer> customer = customerService.getListCustomer();
		model.addObject("customers" , customer);
		model.setViewName("/customer/list");
		return model;
	}
	
}
