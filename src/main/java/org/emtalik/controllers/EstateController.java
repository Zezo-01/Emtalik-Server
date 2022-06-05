package org.emtalik.controllers;

import java.util.ArrayList;
import java.util.List;

import org.emtalik.exception.ApiRequestException;
import org.emtalik.model.Apartment;
import org.emtalik.model.Estate;
import org.emtalik.model.EstateMedia;
import org.emtalik.model.House;
import org.emtalik.model.Land;
import org.emtalik.model.Parking;
import org.emtalik.model.Store;
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
    public void createEstate(@RequestParam String estateJson,@RequestParam String type ,@RequestParam int userId, @RequestParam MultipartFile estateMainPicture, @RequestParam List<MultipartFile> estateMedia)
    {
        
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            if(type.equals("house")){
                System.out.println("REACHED : HOUSE");
                House estate = objectMapper.readValue(estateJson, House.class);
                estate.setMainPictureWithFile(estateMainPicture);
                List<EstateMedia> media = new ArrayList<EstateMedia>();
                for(MultipartFile mf: estateMedia){
                    EstateMedia em = new EstateMedia(mf);
                    em.setEstate(estate);
                    media.add(em);
                }
                estate.setMedia(media);
                estate.setOwner(adminService.getUserFromId(userId).get());
                estateService.saveHouse(estate);
               
            } else if(type.equals("apartment")){
                System.out.println("REACHED : APARTMENT");
                Apartment estate = objectMapper.readValue(estateJson, Apartment.class);
                estate.setMainPictureWithFile(estateMainPicture);
                List<EstateMedia> media = new ArrayList<EstateMedia>();
                for(MultipartFile mf: estateMedia){
                    EstateMedia em = new EstateMedia(mf);
                    em.setEstate(estate);
                    media.add(em);
                }
                estate.setMedia(media);
                estate.setOwner(adminService.getUserFromId(userId).get());
                estateService.saveApartment(estate);

            }
            else if(type.equals("parking")){
                System.out.println("REACHED : PARKING");
                Parking estate = objectMapper.readValue(estateJson, Parking.class);
                estate.setMainPictureWithFile(estateMainPicture);
                List<EstateMedia> media = new ArrayList<EstateMedia>();
                for(MultipartFile mf: estateMedia){
                    EstateMedia em = new EstateMedia(mf);
                    em.setEstate(estate);
                    media.add(em);
                }
                estate.setMedia(media);
                estate.setOwner(adminService.getUserFromId(userId).get());
                estateService.saveParking(estate);
                
                
            }
            else if(type.equals("land")){
                System.out.println("REACHED : LAND");
                Land estate = objectMapper.readValue(estateJson, Land.class);
                estate.setMainPictureWithFile(estateMainPicture);
                List<EstateMedia> media = new ArrayList<EstateMedia>();
                for(MultipartFile mf: estateMedia){
                    EstateMedia em = new EstateMedia(mf);
                    em.setEstate(estate);
                    media.add(em);
                }
                estate.setMedia(media);
                estate.setOwner(adminService.getUserFromId(userId).get());
                estateService.saveLand(estate);
            }else if(type.equals("store")){
                System.out.println("REACHED : STORE");
                Store estate = objectMapper.readValue(estateJson, Store.class);
                estate.setMainPictureWithFile(estateMainPicture);
                List<EstateMedia> media = new ArrayList<EstateMedia>();
                for(MultipartFile mf: estateMedia){
                    EstateMedia em = new EstateMedia(mf);
                    em.setEstate(estate);
                    media.add(em);
                }
                estate.setMedia(media);
                estate.setOwner(adminService.getUserFromId(userId).get());
                estateService.saveStore(estate);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
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
    @GetMapping(path = "/fake/apartment")
    public Estate getNullApartment() {
        return new Apartment();
    }
    @GetMapping(path = "/fake/store")
    public Estate getNullStore() {
        return new Store();
    }
    @GetMapping(path = "/fake/land")
    public Estate getNullLand() {
        return new Land();
    }
    @GetMapping(path = "")
    public List<Estate> getEstates(){
        return estateService.getEstates();
    }
}
