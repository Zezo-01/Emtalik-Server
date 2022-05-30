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
    int fridges;
    @Column(name = "storage_room")
    boolean storageRoom;
}
