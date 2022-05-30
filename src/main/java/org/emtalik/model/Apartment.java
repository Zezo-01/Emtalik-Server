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
public class Apartment extends Estate {
    @Column(length = 3)
    private int apartmentFloorNumber;
    @Column(length = 4)
    private int apartmentNumber;
}
