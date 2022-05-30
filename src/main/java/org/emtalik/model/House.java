package org.emtalik.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class House extends Estate {
    @Column(name = "number_of_floors", length = 1)
    private int numberOfFloors;
    @Column(length = 2)
    private int rooms;
    @Column(name = "swimming_pool")
    private boolean swimmingPool;
   
}
