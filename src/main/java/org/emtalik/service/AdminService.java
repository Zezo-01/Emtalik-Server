package org.emtalik.service;

import java.util.List;
import java.util.Optional;

import org.emtalik.dao.ProfilePictureRepo;
import org.emtalik.dao.UsersRepo;
import org.emtalik.model.ProfilePicture;
import org.emtalik.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	private final UsersRepo userRepo;
	private final ProfilePictureRepo profilePictureRepo;

	@Autowired
	AdminService(UsersRepo userRepo, ProfilePictureRepo profilePictureRepo) {
		this.userRepo = userRepo;
		this.profilePictureRepo = profilePictureRepo;
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
	public List<ProfilePicture> getProfilePictures(){
		return profilePictureRepo.findAll();
	}
	public void saveProfilePicture(ProfilePicture picture){
		profilePictureRepo.save(picture);
	}
}
