package com.niit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.niit.dao.UserDAOImpl;
import com.niit.model.User;

@RestController
public class UserController {
	private static Logger log=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserDAOImpl userDAOImpl;
	@Autowired
	User user;
	
	@RequestMapping(value="/login/",method=RequestMethod.POST)
	public ResponseEntity<User> validateUser(@RequestBody User user,HttpServletRequest request,HttpSession session)
	{
		log.debug("Starting of the Method isValidUser");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		boolean value=userDAOImpl.validate(username, password);
		if(value)
		{
			session.setAttribute("username", username);
			user.setIsonline('Y');
			log.debug("Ending of the Method isValidUser");
			return new ResponseEntity<User>(user,HttpStatus.OK) ;
		}
		else
		{
			//Error error=new Error(1,"Username and password doesnt exists...");
			return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}
	
	}
	
	@RequestMapping(value="/register/", method=RequestMethod.POST)
	public ResponseEntity<?>registerUser(@RequestBody User user)
	{
		try{
			log.debug("USERCONTROLLER=>REGISTER " + user);
			user.setStatus('N');
			user.setIsonline('N');
			String username=userDAOImpl.insertUser(user);
			
			if(username==null){
				Error error=new Error("Couldnt insert user details ");
				return new ResponseEntity<Error>(error , HttpStatus.CONFLICT);
			}
			else
				return new ResponseEntity<User>(user,HttpStatus.OK);
			}catch(Exception e){
				e.printStackTrace();
				Error error1=new Error("Couldnt insert user details. Cannot have null/duplicate values " + e.getMessage());
				return new ResponseEntity<Error>(error1 , HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
	}
	
	@RequestMapping(value="/logout/",method=RequestMethod.PUT)
	public ResponseEntity<User> logout(HttpSession session)
	{
		User user=(User)session.getAttribute("user");
		if(user!=null)
		{
			user.setIsonline('N');
			userDAOImpl.updateUser(user);
		}
		
		session.removeAttribute("user");
		session.invalidate();
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	

}
