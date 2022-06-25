package org.emtalik.service;

import java.util.List;

import org.emtalik.Repositroy.OfferRepo;
import org.emtalik.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
    @Autowired
    OfferRepo offerRepo;
    public Offer getOfferById(int id){
        return offerRepo.getById(id);
    }

    public void deleteOfferById(int id){
        offerRepo.deleteById(id);
    }
    public List<Offer> getOffers(){
        return offerRepo.findAll();
    }
    public void saveOffer(Offer offer) {
        offerRepo.save(offer);
    }
}
