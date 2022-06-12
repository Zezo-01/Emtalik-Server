package org.emtalik.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.emtalik.exception.ApiRequestException;
import org.emtalik.model.Estate;
import org.emtalik.model.EstateResponse;
import org.emtalik.model.ProfilePicture;
import org.emtalik.model.User;
import org.emtalik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController 
{
    @Autowired
    UserService userService;

	@GetMapping(path = "/hasestates/{id}")
	public boolean userHasEstates(@PathVariable int id){
		User user = userService.getUserById(id);
		if(user.getOwnedEstates().isEmpty()){
			return false;
		} else {
			return true;
		}
	}


	@GetMapping(path = "/estates/{id}")
	public List<EstateResponse> getUserEstates(@PathVariable int id){
		User user = userService.getUserById(id);
		
		if(user.getOwnedEstates().isEmpty()){
			return null;
		} else {
			List<EstateResponse> response = new ArrayList<EstateResponse>();
			for(Estate estate: user.getOwnedEstates() ){
				response.add(EstateResponse.copyEstate(estate));
			}
			return response;
		}
	}
	@GetMapping(path = "/estates/approved/{id}")
	public List<EstateResponse> getUserApprovedEstates(@PathVariable int id){
		User user = userService.getUserById(id);
		
		if(user.getOwnedEstates().isEmpty()){
			return null;
		} else {
			List<EstateResponse> response = new ArrayList<EstateResponse>();
			for(Estate estate: user.getOwnedEstates() ){
				if(estate.isApproved()){
					response.add(EstateResponse.copyEstate(estate));
				}
			}
			return response;
		}
	}

    @GetMapping("/picture/{id}")
	public ResponseEntity<byte[]> getPictureForUser(@PathVariable int id){
		try
		{
			ProfilePicture picture = userService.getProfilePictureByUserId(id);
			HttpHeaders header = new HttpHeaders();
			header.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=profilepicture");
			header.setContentType(MediaType.parseMediaType(picture.getContentType()));
			return new ResponseEntity<byte[]>(picture.getContent(), header, HttpStatus.OK);
		}
		catch(NoSuchElementException exception){
			throw new ApiRequestException("No picture found",HttpStatus.NOT_FOUND);
		}
    }
}
