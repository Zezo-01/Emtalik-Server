package org.emtalik.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EstateResponse {
    private int id;
    private String name;
    private String type;
    private String address;

    public static EstateResponse copyEstate(Estate estate){
        return new EstateResponse(estate.getId(), estate.getName(), estate.getType(), estate.getAddress());
    }
}
