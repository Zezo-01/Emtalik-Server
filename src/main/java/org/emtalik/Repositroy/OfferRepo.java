package org.emtalik.Repositroy;

import org.springframework.stereotype.Repository;
import org.emtalik.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OfferRepo extends JpaRepository <Offer,Integer> {
    
}
