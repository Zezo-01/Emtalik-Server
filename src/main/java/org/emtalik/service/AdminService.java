package org.emtalik.service;

import java.util.List;
import java.util.Optional;

import org.emtalik.Repositroy.EstateRepo;
import org.emtalik.Repositroy.UsersRepo;
import org.emtalik.model.Estate;
import org.emtalik.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	private final UsersRepo userRepo;
	private final EstateRepo estateRepo;

	@Autowired
	AdminService(UsersRepo userRepo, EstateRepo estateRepo) {
		this.userRepo = userRepo;
		this.estateRepo = estateRepo;
	}

	public void toggleEstateApprovalById(int id){
		Estate estate = estateRepo.findById(id).get();
		estate.setApproved( estate.isApproved() ? false : true);
		estateRepo.save(estate);
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
	
	public User saveUser(User user){
		return userRepo.save(user);
	}
	
	

	
}
