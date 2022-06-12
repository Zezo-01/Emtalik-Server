package org.emtalik.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferResponse {

    private int id;
	private String name;
	private OfferType type;
	private double sellPrice;
	private double rentPricePerMonth;
	private double rentPricePerYear;
	private double rentPricePerSeasson;
	private boolean negotiable;
    private int estateId;

    public static OfferResponse copy(Offer offer){
        return new OfferResponse(offer.getId(), offer.getName(), offer.getType(), offer.getSellPrice(), offer.getRentPricePerMonth(), offer.getRentPricePerYear(), offer.getRentPricePerSeasson(), offer.isNegotiable(),offer.getEstate().getId());
    }

}


