package org.emtalik.controllers;

import java.util.List;
import java.util.Optional;

import org.emtalik.exception.ApiRequestException;
import org.emtalik.model.ProfilePicture;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.regex.Pattern;

@RestController
@RequestMapping(path = "/admin", produces = "application/json")
@ResponseBody
public class AdminController {
	@Autowired
	AdminService adminService;

	@PostMapping("/register")
	public UserProvider registerUser(@RequestParam String user ,@RequestParam(required = false) MultipartFile picture)
	{
		System.out.println(user);
		if(picture != null){
			System.out.println(picture.getContentType());
		}
		return null;
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

	@GetMapping("/profilepictures")
	public List<ProfilePicture> getProfilePictures(){
		return adminService.getProfilePictures();
	}
	@PostMapping("/save/profilepicture")
	public void saveProfilePicture(@RequestParam MultipartFile picture){
		try
		{
			adminService.saveProfilePicture(new ProfilePicture (picture));
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			throw new ApiRequestException("Error occured",HttpStatus.BAD_REQUEST);
			
		}
		 
	}
}
