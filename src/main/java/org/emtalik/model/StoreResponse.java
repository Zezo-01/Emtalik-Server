package org.emtalik.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class StoreResponse {
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
    private int fridges;
    private boolean storageRoom;

    public static StoreResponse copyStore(Store store){
        return new StoreResponse(store.getId(),store.getName(),store.getAddress(),store.getProvince(),store.getOwner().getUsername(),store.getOwner().getId(),store.getDescription(),store.getSize(),store.getMadeOn(),store.isApproved(),store.getFridges(),store.isStorageRoom());
    }
    
}
