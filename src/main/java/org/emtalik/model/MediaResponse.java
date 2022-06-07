package org.emtalik.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MediaResponse 
{
    private int id;
    private String contentType;

    public static MediaResponse copy(EstateMedia media){
        return new MediaResponse(media.getId(), media.getContentType());
    }
}
