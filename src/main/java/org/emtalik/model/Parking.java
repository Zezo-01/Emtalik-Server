package org.emtalik.model;


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
    @Column(name = "cars_allowed")
    private String carsAllowed;
    @Column(name = "vehicle_capacity")
    private int vehicleCapacity;
}
