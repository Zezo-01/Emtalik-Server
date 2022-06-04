package org.emtalik.controllers;

import org.emtalik.exception.ApiRequestException;
import org.emtalik.model.Estate;
import org.emtalik.model.House;
import org.emtalik.model.Parking;
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
    // Estate JSON FIRST
    @PostMapping(path= "/create")
    public void createEstate(@RequestParam String estateJson,@RequestParam String type ,@RequestParam int id, @RequestParam(required = false) MultipartFile estateMainPicture, @RequestParam(required = false) MultipartFile[] estateMedia)
    {
       
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            if(type.equals("house")){
                House house = objectMapper.readValue(estateJson, House.class);
                System.out.println("Estate :\n" +  "Content Type : " + estateMainPicture.getContentType() + "\t Name : "  + estateMainPicture.getOriginalFilename());
               
                // HOUSE BS
            } 
            
        //  System.out.println("Estate Main Picture :\n" + estateMainPicture.getContentType());
        //  for(MultipartFile media : estateMedia){
        //         System.out.println("Estate Media : \n" + media.getContentType());
        //    }
        }
        catch(Exception e)
        {
            System.out.println("Mapping Error Occured : " + e.getMessage());
            throw new ApiRequestException("Internal Error" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @GetMapping(path = "/fake")
    public Estate getNullEstate(){
        return new Estate();
    }
    @GetMapping(path = "/fake/parking")
    public Estate getNullParking(){
        return new Parking();
    }
    @GetMapping(path = "/fake/house")
    public Estate getNullHouse(){
        return new House();
    }
}
