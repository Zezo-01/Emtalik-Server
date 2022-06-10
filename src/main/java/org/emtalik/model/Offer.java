package org.emtalik.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id",referencedColumnName = "for_estate" )
    private Estate estate;



}
