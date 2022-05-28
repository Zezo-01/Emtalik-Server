package org.emtalik.controllers;

import java.util.NoSuchElementException;

import org.emtalik.exception.ApiRequestException;
import org.emtalik.model.ProfilePicture;
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
