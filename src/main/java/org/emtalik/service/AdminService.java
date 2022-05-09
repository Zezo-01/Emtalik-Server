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
}
