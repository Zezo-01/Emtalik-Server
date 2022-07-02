package org.emtalik.service;

import java.util.List;

import org.emtalik.Repositroy.ApartmentRepo;
import org.emtalik.Repositroy.EstateRepo;
import org.emtalik.Repositroy.HouseRepo;
import org.emtalik.Repositroy.LandRepo;
import org.emtalik.Repositroy.ParkingRepo;
import org.emtalik.Repositroy.StoreRepo;
import org.emtalik.model.Apartment;
import org.emtalik.model.Estate;
import org.emtalik.model.House;
import org.emtalik.model.Land;
import org.emtalik.model.Parking;
import org.emtalik.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstateService {
    
    private final EstateRepo apartmentRepo;
    private final EstateRepo houseRepo;
    private final EstateRepo landRepo;
    private final EstateRepo parkingRepo;
    private final EstateRepo storeRepo;
    private final EstateRepo estateRepo;
    @Autowired
    public EstateService(ApartmentRepo apartmentRepo,HouseRepo houseRepo, LandRepo landRepo,ParkingRepo parkingRepo,StoreRepo storeRepo,EstateRepo estateRepo){
        this.apartmentRepo = apartmentRepo;
        this.houseRepo = houseRepo;
        this.landRepo = landRepo;
        this.parkingRepo = parkingRepo;
        this.storeRepo = storeRepo;
        this.estateRepo = estateRepo;
    }

    public Estate getEstateById(int id){
        return estateRepo.findById(id).get();
    }

    public void saveApartment(Apartment apartment){
        apartmentRepo.save(apartment);
    }
    public void saveHouse(House house){
        houseRepo.save(house);
    }
    public void saveLand(Land land){
        landRepo.save(land);
    }
    public void saveParking(Parking parking){
        parkingRepo.save(parking);
    }
    public void saveStore(Store store){
        storeRepo.save(store);
    }

    public List<Estate> getApprovedEstates(){
        return estateRepo.findByApprovedTrue();
    }
    public List<Estate> getUnApprovedEstates(){
        return estateRepo.findByApprovedFalse();
    }
    public List<Estate> getEstates(){
        return estateRepo.findAll();
    }

    public void deleteEstateById(int id){
        estateRepo.deleteById(id);
    }




}
