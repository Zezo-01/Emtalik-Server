package org.emtalik.controllers;

import java.util.ArrayList;
import java.util.List;

import org.emtalik.exception.ApiRequestException;
import org.emtalik.model.Estate;
import org.emtalik.model.EstateMedia;
import org.emtalik.model.House;
import org.emtalik.model.Parking;
import org.emtalik.service.AdminService;
import org.emtalik.service.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = "/estate", produces = "application/json")
public class EstateController {


    @Autowired
    AdminService adminService;
    @Autowired
    EstateService estateService;
   

   

    @PostMapping(path= "/create")
    public void createEstate(@RequestParam String estateJson,@RequestParam String type ,@RequestParam int userId, @RequestParam MultipartFile estateMainPicture, @RequestParam(required = false) List<MultipartFile> estateMedia)
    {
        
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            if(type.equals("house")){
                House house = objectMapper.readValue(estateJson, House.class);
                house.setMainPictureWithFile(estateMainPicture);
                List<EstateMedia> media = new ArrayList<EstateMedia>();
                for(MultipartFile mf: estateMedia){
                    EstateMedia em = new EstateMedia(mf);
                    em.setEstate(house);
                    media.add(em);
                }
                house.setMedia(media);
                house.setOwner(adminService.getUserFromId(userId).get());
                estateService.saveHouse(house);
               
            } 
        }
        catch(Exception e)
        {
            throw new ApiRequestException("Internal Error" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @GetMapping(path = "/fake")
    public Estate getNullEstate() {
        return new Estate();
    }

    @GetMapping(path = "/fake/parking")
    public Estate getNullParking() {
        return new Parking();
    }

    @GetMapping(path = "/fake/house")
    public Estate getNullHouse() {
        return new House();
    }
}
