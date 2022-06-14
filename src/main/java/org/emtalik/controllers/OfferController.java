package org.emtalik.controllers;

import java.util.ArrayList;
import java.util.List;

import org.emtalik.exception.ApiRequestException;
import org.emtalik.model.Estate;
import org.emtalik.model.Offer;
import org.emtalik.model.OfferResponse;
import org.emtalik.service.EstateService;
import org.emtalik.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = "/offer")
public class OfferController {
    @Autowired
    OfferService offerService;
    @Autowired
    EstateService estateService; 
    

    @GetMapping
    public List<OfferResponse> getOffers(){
        List<OfferResponse> offers = new ArrayList<OfferResponse>();
        for(Offer offer: offerService.getOffers()){
            offers.add(OfferResponse.copy(offer));
        }
        return offers;
    }

    @PostMapping(path = "/save")
    public void saveOffer(String offerJson, int estateId){
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(offerJson);
        try
        {
            Offer offer = objectMapper.readValue(offerJson, Offer.class);
            Estate estate = estateService.getEstateById(estateId);
            offer.setEstate(estate);
            offerService.saveOffer(offer);            
            
        }
        catch(Exception e)
        {
            e.getMessage();
            System.out.println("\n\n");
            e.printStackTrace();
            throw new ApiRequestException("No estate with this id found",HttpStatus.NOT_FOUND);
        }

    }

    
}
