package org.emtalik.service;


import java.util.NoSuchElementException;

import org.emtalik.Repositroy.ProfilePictureRepo;
import org.emtalik.Repositroy.UsersRepo;
import org.emtalik.model.ProfilePicture;
import org.emtalik.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService 
{
    ProfilePictureRepo pictureRepo;
    UsersRepo userRepo;
    @Autowired
    UserService(ProfilePictureRepo pictureRepo, UsersRepo userRepo){
        this.pictureRepo = pictureRepo;
        this.userRepo = userRepo;
    }

    public ProfilePicture getProfilePictureByUserId(int id){
		return userRepo.findById(id).get().getPicture();
	}

    public User getUserById(int id) throws NoSuchElementException{
        return userRepo.findById(id).get();
    }
}
