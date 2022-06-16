package org.emtalik.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LandResponse {
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
    private boolean cityHallElectricitySupport;

    public static LandResponse copyLand(Land land){
        return new LandResponse(land.getId(),land.getName(),land.getAddress(),land.getProvince(),land.getOwner().getUsername(),land.getOwner().getId(),land.getDescription(),land.getSize(),land.getMadeOn(),land.isApproved(),land.isCityHallElectricitySupport());
    }
}
