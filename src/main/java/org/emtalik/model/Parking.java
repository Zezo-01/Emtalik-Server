package org.emtalik.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Parking extends Estate {
    @Column(name = "cars_allowed", columnDefinition = "SET(\"automobile\",\"bus\",\"truck\",\"bike\")")
    private String carsAllowed;
    @Column(name = "vehicle_capacity")
    private int vehicleCapacity;

    public void setCarsAllowed(List<String> carsAllowed) {
		String finalValue = "";
		for (int i = 0; i < carsAllowed.size(); i++) {
			if (i != carsAllowed.size() - 1) {
				finalValue += carsAllowed.get(i) + ",";
			} else {
				finalValue += carsAllowed.get(i);
			}
		}
		this.carsAllowed = finalValue;
		

	}
}
