package org.emtalik.controllers;

import java.util.ArrayList;
import java.util.List;

import org.emtalik.Repositroy.EstateRepo;
import org.emtalik.exception.ApiRequestException;
import org.emtalik.model.Estate;
import org.emtalik.model.Offer;
import org.emtalik.model.OfferResponse;
import org.emtalik.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/offer")
public class OfferController {
    @Autowired
    OfferService offerService;
    @Autowired
    EstateRepo estateRepo;

    

    @GetMapping
    public List<OfferResponse> getOffers(){
        List<OfferResponse> offers = new ArrayList<OfferResponse>();
        for(Offer offer: offerService.getOffers()){
            offers.add(OfferResponse.copy(offer));
        }
        return offers;
    }

    @PostMapping(path = "/save")
    public void saveOffer(Offer offer,int estateId){
        try
        {
            Estate estate = estateRepo.findById(estateId).get();
            offer.setEstate(estate);
            offerService.saveOffer(offer);
        }
        catch(Exception e)
        {
            throw new ApiRequestException("No estate with this id found",HttpStatus.NOT_FOUND);
        }

    }

    
}
