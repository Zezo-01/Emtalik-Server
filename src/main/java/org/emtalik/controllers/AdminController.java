package org.emtalik.controllers;

import java.util.List;
import java.util.Optional;

import org.emtalik.model.User;
import org.emtalik.model.UserProvider;
import org.emtalik.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin", produces = "application/json")
@ResponseBody
public class AdminController {
	@Autowired
	AdminService adminService;

    @PostMapping("/validate")
    public UserProvider validate(@RequestBody String id,@RequestBody String password){
		// TODO: REG EX AND CHECKING
        return null;
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
