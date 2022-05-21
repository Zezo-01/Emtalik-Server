package org.emtalik.controllers;

import java.util.List;
import java.util.Optional;

import org.emtalik.exception.ApiRequestException;
import org.emtalik.model.User;
import org.emtalik.model.UserProvider;
import org.emtalik.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.regex.Pattern;

@RestController
@RequestMapping(path = "/admin", produces = "application/json")
@ResponseBody
public class AdminController {
	@Autowired
	AdminService adminService;

    @PostMapping("/validate")
    public UserProvider validate(@RequestParam String id,@RequestParam String password) {
		if(id == null || password == null || id.isEmpty() || password.isEmpty())
		{
			throw new ApiRequestException("Empty Parameters",HttpStatus.BAD_REQUEST);
		
		} 
		else if (Pattern.compile("[0-9]{9,15}").matcher(id).matches())
		{
			User user = adminService.getUserFromContactNumber(id).orElse(null);
			if(user == null)
			{
				throw new ApiRequestException("Invalid Contact Number",HttpStatus.NOT_FOUND);
			}
			else 
			{
				 if(adminService.validateUser(user.getId(), password))
				 {
					 return UserProvider.copyUser(user);
				 } 
				 else 
				 {
					throw new ApiRequestException("Invalid Password",HttpStatus.UNAUTHORIZED);
				 }
			}
		} 
		// EMAIL CASE
		else if(Pattern.compile("^(.+)@(\\S+)$").matcher(id).matches())
		{
			User user = adminService.getUserFromEmail(id).orElse(null);
			if(user == null)
			{
				throw new ApiRequestException("Invalid Email",HttpStatus.NOT_FOUND);
			}
			else 
			{
				 if(adminService.validateUser(user.getId(), password))
				 {
					return UserProvider.copyUser(user);
				 } 
				 else 
				 {
					throw new ApiRequestException("Invalid Password",HttpStatus.UNAUTHORIZED);
				 }
			}
		}
		//  USERNAME CASE
		else 
		{
			User user = adminService.getUserFromUsername(id).orElse(null);
			if(user == null)
			{
				throw new ApiRequestException("Invalid Username",HttpStatus.NOT_FOUND);
			}
			else 
			{
				 if(adminService.validateUser(user.getId(), password))
				 {
					 return UserProvider.copyUser(user);
				 } 
				 else 
				 {
					throw new ApiRequestException("Invalid Password",HttpStatus.UNAUTHORIZED);
				 }
			}	
		}
    }


	@GetMapping("/users")
	public List<User> getUsers() {

		return adminService.getUsers();
	}

	@GetMapping("/user/{id}")
	public Optional<User> getUser(@PathVariable int id) {

		return adminService.getUserFromId(id);
	}
}
