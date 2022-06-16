package org.emtalik.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApartmentResponse {
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
    private int apartmentFloorNumber;
    private int apartmentNumber;

    public static ApartmentResponse copyApartment(Apartment apartment){
        return new ApartmentResponse(apartment.getId(),apartment.getName(),apartment.getAddress(),apartment.getProvince(),apartment.getOwner().getUsername(),apartment.getOwner().getId(),apartment.getDescription(),apartment.getSize(),apartment.getMadeOn(),apartment.isApproved(),apartment.getApartmentFloorNumber(),apartment.getApartmentNumber());
    }
}
