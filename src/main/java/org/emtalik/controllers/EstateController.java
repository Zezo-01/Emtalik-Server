package org.emtalik.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.emtalik.exception.ApiRequestException;
import org.emtalik.model.*;
import org.emtalik.service.AdminService;
import org.emtalik.service.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = "/estate", produces = "application/json")
public class EstateController {


    @Autowired
    AdminService adminService;
    @Autowired
    EstateService estateService;
   
    
    @DeleteMapping
    public void deleteEstateById(int estateId){
        estateService.deleteEstateById(estateId);
    }




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

    @PutMapping(path= "/modify")
    public void modifyEstate(String estateJson, String type , int estateId)
    {

        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            if(type.equals("house")){
                House estate = (House) estateService.getEstateById(estateId);
                System.out.println("House rooms : " + estate.getRooms());
                House newEstate = objectMapper.readValue(estateJson, House.class);
                estate.setName(newEstate.getName());
                estate.setProvince(newEstate.getProvince());
                estate.setAddress(newEstate.getAddress());
                estate.setSize(newEstate.getSize());
                estate.setDescription(newEstate.getDescription());
                estate.setNumberOfFloors(newEstate.getNumberOfFloors());
                estate.setRooms(newEstate.getRooms());
                estate.setSwimmingPool(newEstate.isSwimmingPool());
                estateService.saveHouse(estate);

            } else if(type.equals("apartment")){

                Apartment estate = (Apartment) estateService.getEstateById(estateId);
                System.out.println(estate.getApartmentFloorNumber());
                Apartment newEstate = objectMapper.readValue(estateJson, Apartment.class);
                estate.setName(newEstate.getName());
                estate.setProvince(newEstate.getProvince());
                estate.setAddress(newEstate.getAddress());
                estate.setSize(newEstate.getSize());
                estate.setDescription(newEstate.getDescription());
                estate.setApartmentNumber(newEstate.getApartmentNumber());
                estate.setApartmentFloorNumber(newEstate.getApartmentFloorNumber());
                estateService.saveApartment(estate);

            }
            else if(type.equals("parking")){

                Parking estate = (Parking) estateService.getEstateById(estateId);
                System.out.println("Parking : " + estate.getVehicleCapacity());
                Parking newEstate = objectMapper.readValue(estateJson, Parking.class);
                estate.setName(newEstate.getName());
                estate.setProvince(newEstate.getProvince());
                estate.setAddress(newEstate.getAddress());
                estate.setSize(newEstate.getSize());
                estate.setDescription(newEstate.getDescription());
                estate.setVehicleCapacity(newEstate.getVehicleCapacity());
                estate.setCarsAllowed(List.of(newEstate.getCarsAllowed().split(",")));
                estateService.saveParking(estate);


            }
            else if(type.equals("land")){

                Land estate = (Land) estateService.getEstateById(estateId);
                System.out.println(estate.isCityHallElectricitySupport());
                Land newEstate = objectMapper.readValue(estateJson, Land.class);
                estate.setName(newEstate.getName());
                estate.setProvince(newEstate.getProvince());
                estate.setAddress(newEstate.getAddress());
                estate.setSize(newEstate.getSize());
                estate.setDescription(newEstate.getDescription());
                estate.setCityHallElectricitySupport(newEstate.isCityHallElectricitySupport());
                estateService.saveLand(estate);

            }else if(type.equals("store")){

                Store estate = (Store) estateService.getEstateById(estateId);
                System.out.println(estate.getFridges());
                Store newEstate = objectMapper.readValue(estateJson, Store.class);
                estate.setName(newEstate.getName());
                estate.setProvince(newEstate.getProvince());
                estate.setAddress(newEstate.getAddress());
                estate.setSize(newEstate.getSize());
                estate.setDescription(newEstate.getDescription());
                estate.setFridges(newEstate.getFridges());
                estate.setStorageRoom(newEstate.isStorageRoom());
                estateService.saveStore(estate);
            }
        }
        catch(Exception e)
        {
            throw new ApiRequestException("Internal Error" , HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("parking/{id}")
    public ParkingResponse getParkingById(@PathVariable int id){
        return ParkingResponse.copyParking((Parking) estateService.getEstateById(id)) ;
    }
    @GetMapping("store/{id}")
    public StoreResponse getStoreById(@PathVariable int id){
        return StoreResponse.copyStore((Store) estateService.getEstateById(id)) ;
    }
    @GetMapping("apartment/{id}")
    public ApartmentResponse getApartmentById(@PathVariable int id){
        return ApartmentResponse.copyApartment((Apartment) estateService.getEstateById(id)) ;
    }
    @GetMapping("house/{id}")
    public HouseResponse getHouseById(@PathVariable int id){
        return HouseResponse.copyHouse((House) estateService.getEstateById(id)) ;
    }
    @GetMapping("land/{id}")
    public LandResponse getLandById(@PathVariable int id){
        return LandResponse.copyLand((Land) estateService.getEstateById(id)) ;
    }

    @GetMapping("/unapproved")
    public List<EstateResponse> getUnApprovedEstates(){
        List<Estate> estates = estateService.getUnApprovedEstates();
        List<EstateResponse> response = new ArrayList<EstateResponse>();
        for(Estate e: estates){
            response.add(EstateResponse.copyEstate(e));
        }
        return response;
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
