package org.emtalik.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.emtalik.exception.ApiRequestException;
import org.emtalik.model.Apartment;
import org.emtalik.model.Estate;
import org.emtalik.model.EstateMainPicture;
import org.emtalik.model.EstateMedia;
import org.emtalik.model.EstateResponse;
import org.emtalik.model.House;
import org.emtalik.model.Land;
import org.emtalik.model.MediaResponse;
import org.emtalik.model.Parking;
import org.emtalik.model.Store;
import org.emtalik.service.AdminService;
import org.emtalik.service.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
   

    @GetMapping(path = "media/{estateId}/{mediaId}")
    public ResponseEntity<byte[]> displayEstateMedia(@PathVariable int estateId,@PathVariable int mediaId){
        try
		{
            HttpHeaders header = new HttpHeaders();
			List<EstateMedia> estateMedia = estateService.getEstateById(estateId).getMedia();
            EstateMedia targetMedia = new EstateMedia();
            for(EstateMedia em : estateMedia){
                if(em.getId() == mediaId){
                    targetMedia = em;
                }
            }
            if(targetMedia.getContentType() == null){
                throw new ApiRequestException("No picture found",HttpStatus.NOT_FOUND);
            }

			header.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=profilepicture");
			header.setContentType(MediaType.parseMediaType(targetMedia.getContentType()));
			return new ResponseEntity<byte[]>(targetMedia.getContent(), header, HttpStatus.OK);
		}
		catch(NoSuchElementException exception)
        {
			throw new ApiRequestException("No picture found",HttpStatus.NOT_FOUND);
		}
    }



    @GetMapping(path = "media/{id}")
    public List<MediaResponse> getEstateMediaPath(@PathVariable(name = "id") int estateId){
        try
        {
            List<EstateMedia> media = estateService.getEstateById(estateId).getMedia();
            List<MediaResponse> mediaResponse = new ArrayList<MediaResponse>();
            for(EstateMedia em : media){
                mediaResponse.add(MediaResponse.copy(em));
            }
            return mediaResponse;
        }
        catch(NoSuchElementException exception)
        {
            throw new ApiRequestException("No estate found",HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(path = "/mainpicture/{id}")
    public ResponseEntity<byte[]> displayEstateMainPicture(@PathVariable int id){
        try
		{
			EstateMainPicture picture = estateService.getEstateById(id).getMainPicture();
			HttpHeaders header = new HttpHeaders();
			header.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=profilepicture");
			header.setContentType(MediaType.parseMediaType(picture.getContentType()));
			return new ResponseEntity<byte[]>(picture.getContent(), header, HttpStatus.OK);
		}
		catch(NoSuchElementException exception){
			throw new ApiRequestException("No picture found",HttpStatus.NOT_FOUND);
		}
    }

   

    @PostMapping(path= "/create")
    public void createEstate(@RequestParam String estateJson,@RequestParam String type ,@RequestParam int userId, @RequestParam MultipartFile estateMainPicture, @RequestParam List<MultipartFile> estateMedia)
    {
        
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            if(type.equals("house")){

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
            throw new ApiRequestException("Internal Error" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

   
    @GetMapping()
    public List<EstateResponse> getAllEstates(){
        List<Estate> estates = estateService.getEstates();  
        List<EstateResponse> response = new ArrayList<EstateResponse>();
        for(Estate e: estates){
            response.add(EstateResponse.copyEstate(e));
        }
        return response;
    }
    @GetMapping(path = "/approved")
    public List<EstateResponse> getApprovedEstates(){
        List<Estate> estates = estateService.getApprovedEstates();  
        List<EstateResponse> response = new ArrayList<EstateResponse>();
        for(Estate e: estates){
            response.add(EstateResponse.copyEstate(e));
        }
        return response;
    }
}
