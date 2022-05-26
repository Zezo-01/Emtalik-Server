package org.emtalik.service;

import java.util.List;
import java.util.Optional;

import org.emtalik.dao.UsersRepo;
import org.emtalik.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	private final UsersRepo userRepo;

	@Autowired
	AdminService(UsersRepo userRepo) {
		this.userRepo = userRepo;
	}

	public List<User> getUsers() {
		return userRepo.findAll();
	}

	public Optional<User> getUserFromId(int id) {
		return userRepo.findById(id);
	}
	public Optional<User> getUserFromUsername(String username){
		return userRepo.findByUsername(username);
	}
	public Optional<User> getUserFromContactNumber(String contactNumber){
		return userRepo.findByContactNumber(contactNumber);
	}
	public Optional<User> getUserFromEmail(String email){
		return userRepo.findByEmail(email);
	}
	public boolean validateUser(int id,String password){
		return userRepo.findByIdAndPassword(id,password).isPresent();
	}
	
	public void saveUser(User user){
		userRepo.save(user);
	}
}
