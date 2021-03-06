package org.emtalik.controllers;

import java.util.List;
import java.util.Optional;

import org.emtalik.exception.ApiRequestException;
import org.emtalik.model.ProfilePicture;
import org.emtalik.model.Role;
import org.emtalik.model.User;
import org.emtalik.model.UserProvider;
import org.emtalik.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = "/admin", produces = "application/json")
public class AdminController {
	@Autowired
	AdminService adminService;

	@PutMapping("/approve")
	public void toggleEstateApprovalById(int estateId){
		adminService.toggleEstateApprovalById(estateId);
	}

	@PutMapping("/edit/{userId}")
	public UserProvider updateUserInfo(@PathVariable int userId, @RequestParam boolean removePicture, @RequestParam String userJson, @RequestParam(required = false) MultipartFile picture){
		ObjectMapper objectMapper = new ObjectMapper();
		User newUser;
		try
		{

			newUser = objectMapper.readValue(userJson, User.class);
			User user = adminService.getUserFromId(userId).get();
			user.setUsername(newUser.getUsername());
			user.setEmail(newUser.getEmail());
			user.setContactNumber(newUser.getContactNumber());
			user.setFirstName(newUser.getFirstName());
			user.setFathersName(newUser.getFathersName());
			user.setGrandfathersName(newUser.getGrandfathersName());
			user.setSurName(newUser.getSurName());
			user.setInterests(List.of(newUser.getInterests().split(",")));
			user.setPassword(newUser.getPassword());

			if(removePicture == true){
				ProfilePicture pfp = user.getPicture();
				user.setPicture(null);
				adminService.deleteProfilePicture(pfp);
			}
			else if(picture != null && picture.getContentType().split("/")[0].equals("image"))
			{
				adminService.deleteProfilePicture(user.getPicture());
				user.setPictureWithFile(picture);
			}
			if(user.getFirstName() == null ||user.getFathersName() == null ||user.getGrandfathersName() == null ||user.getSurName() == null )
			{
				user.setRole(Role.buyer);
			} else {
				user.setRole(Role.seller);
			}

			return UserProvider.copyUser(adminService.saveUser(user));


		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			throw new ApiRequestException("Internal Error" , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@PostMapping("/register")
	public UserProvider registerUser(@RequestParam String userJson ,@RequestParam(required = false) MultipartFile picture)
	{
		ObjectMapper objectMapper = new ObjectMapper();
		User user;
		try
		{
			user = objectMapper.readValue(userJson, User.class);
			if(picture != null && picture.getContentType().split("/")[0].equals("image"))
			{
				System.out.println(picture.getBytes());
				user.setPictureWithFile(picture);
			} 
			if(user.getFirstName() == null ||user.getFathersName() == null ||user.getGrandfathersName() == null ||user.getSurName() == null )
			{
				user.setRole(Role.buyer);
			} else {
				user.setRole(Role.seller);
			}
			
			return UserProvider.copyUser(adminService.saveUser(user));
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			throw new ApiRequestException("Internal Error" , HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@PostMapping("/unique/username")
	public Object checkUserNameValide(@RequestParam String username) {
		List<User> users = adminService.getUsers();
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				throw new ApiRequestException("Username Taken", HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return null;
	}

	@PostMapping("/unique/email")
	public Object checkEmailValide(@RequestParam String email) {
		List<User> users = adminService.getUsers();
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				throw new ApiRequestException("Email Taken", HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return null;
	}

	@PostMapping("/unique/contactnumber")
	public Object checkContactNumberValide(@RequestParam String contactNumber) {
		List<User> users = adminService.getUsers();
		for (User user : users) {
			if (user.getContactNumber().equals(contactNumber)) {
				throw new ApiRequestException("Contact Number Taken", HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return null;
	}

	@PostMapping("/validate")
	public UserProvider validate(@RequestParam String id, @RequestParam String password) {
		if (id == null || password == null || id.isEmpty() || password.isEmpty()) {
			throw new ApiRequestException("Empty Parameters", HttpStatus.BAD_REQUEST);

		} else if (Pattern.compile("[0-9]{9,15}").matcher(id).matches()) {
			User user = adminService.getUserFromContactNumber(id).orElse(null);
			if (user == null) {
				throw new ApiRequestException("Invalid Contact Number", HttpStatus.NOT_FOUND);
			} else {
				if (adminService.validateUser(user.getId(), password)) {
					return UserProvider.copyUser(user);
				} else {
					throw new ApiRequestException("Invalid Password", HttpStatus.UNAUTHORIZED);
				}
			}
		}
		// EMAIL CASE
		else if (Pattern.compile("^(.+)@(\\S+)$").matcher(id).matches()) {
			User user = adminService.getUserFromEmail(id).orElse(null);
			if (user == null) {
				throw new ApiRequestException("Invalid Email", HttpStatus.NOT_FOUND);
			} else {
				if (adminService.validateUser(user.getId(), password)) {
					return UserProvider.copyUser(user);
				} else {
					throw new ApiRequestException("Invalid Password", HttpStatus.UNAUTHORIZED);
				}
			}
		}
		// USERNAME CASE
		else {
			User user = adminService.getUserFromUsername(id).orElse(null);
			if (user == null) {
				throw new ApiRequestException("Invalid Username", HttpStatus.NOT_FOUND);
			} else {
				if (adminService.validateUser(user.getId(), password)) {
					return UserProvider.copyUser(user);
				} else {
					throw new ApiRequestException("Invalid Password", HttpStatus.UNAUTHORIZED);
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
