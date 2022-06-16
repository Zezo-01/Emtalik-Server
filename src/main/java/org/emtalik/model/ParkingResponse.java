package org.emtalik.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParkingResponse {
    private int id;
    private String name;
    private String address;
    private Province province;
    private String ownerUserName;
    private int ownerId;
    private String description;
    private Double size;
    private Timestamp madeOn;
    private boolean approved;
    private String carsAllowd;
    private int vehicleCapacity;

    public static ParkingResponse copyParking(Parking parking){
        return new ParkingResponse(parking.getId(),parking.getName(),parking.getAddress(),parking.getProvince(),parking.getOwner().getUsername(),parking.getOwner().getId(),parking.getDescription(),parking.getSize(),parking.getMadeOn(),parking.isApproved(),parking.getCarsAllowed(),parking.getVehicleCapacity());
    }
    
}
