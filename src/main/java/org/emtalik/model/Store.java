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
public class Store extends Estate{
    @Column(length = 2)
    private int fridges;
    @Column(name = "storage_room")
    private boolean storageRoom;
}
