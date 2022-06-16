package org.emtalik.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HouseResponse {

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
    private int numberOfFloors;
    private int rooms;
    private boolean swimmingPool;    

    public static HouseResponse copyHouse(House house){
        return new HouseResponse(house.getId(),house.getName(),house.getAddress(),house.getProvince(),house.getOwner().getUsername(),house.getOwner().getId(),house.getDescription(),house.getSize(),house.getMadeOn(),house.isApproved(),house.getNumberOfFloors(),house.getRooms(),house.isSwimmingPool());
    }
}
