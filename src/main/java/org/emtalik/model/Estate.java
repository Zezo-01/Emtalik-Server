package org.emtalik.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estate {
    @Id
    @SequenceGenerator
	(name = "estate_sequence",
	sequenceName = "estate_sequence",
	allocationSize = 1,
	initialValue = 1)
	@GeneratedValue
	(strategy = GenerationType.SEQUENCE, 
	generator = "estate_sequence")
    private int id;
    // @ManyToOne
    // private User user;
    @Column(length = 35)
    private String name;
    @Column(length = 45)
    private String address;
    @Column(length = 255)
    private String description;
    @Column(length = 3)
    private Double size;
    @Column(name = "made_on", insertable = false)
	private Timestamp madeOn;

}
