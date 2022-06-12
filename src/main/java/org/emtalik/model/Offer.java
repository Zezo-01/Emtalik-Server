package org.emtalik.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
    @Id
    @SequenceGenerator
	(name = "offer_sequence",
	sequenceName = "offer_sequence",
	allocationSize = 1,
	initialValue = 1)
	@GeneratedValue
	(strategy = GenerationType.SEQUENCE, 
	generator = "offer_sequence")
    private int id;
	@Column(length = 25)
	private String name;
	@Enumerated
	@Column(columnDefinition = "enum(\"sell\",\"rent\")")
	private OfferType type;
	@Column(name = "sell_price")
	private double sellPrice;
	@Column(name = "rent_price_per_month")
	private double rentPricePerMonth;
	@Column(name = "rent_price_per_year")
	private double rentPricePerYear;
	@Column(name = "rent_price_per_seasson")
	private double rentPricePerSeasson;
	private boolean negotiable;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "for_estate",referencedColumnName = "id" )
    private Estate estate;



}
