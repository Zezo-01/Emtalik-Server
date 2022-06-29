package org.emtalik.model;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
	@Id
	@SequenceGenerator
	(name = "user_sequence",
	sequenceName = "user_sequence",
	allocationSize = 1,
	initialValue = 1)
	@GeneratedValue
	(strategy = GenerationType.SEQUENCE, 
	generator = "user_sequence")
	@Column(name = "id")
	private int id;
	@Column(name = "username", length = 25)
	private String username;
	@Column(name = "first_name", length = 15)
	private String firstName;
	@Column(name = "fathers_name", length = 15)
	private String fathersName;
	@Column(name = "grandfathers_name", length = 15)
	private String grandfathersName;
	@Column(name = "sur_name", length = 15)
	private String surName;
	@Column(name = "email", length = 45)
	private String email;
	@Column(name = "password", length = 45)
	private String password;
	@Column(name = "made_on", insertable = false , updatable = false,columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP()")
	private Timestamp madeOn;
	@Column(name = "contact_number", length = 15)
	private String contactNumber;
	@Enumerated(EnumType.STRING)
	@Column(name = "role", columnDefinition = "enum(\"admin\",\"buyer\",\"seller\")")
	private Role role;
	@Column(name = "interests",columnDefinition = "SET(\"apartment\",\"house\",\"parking\",\"store\",\"land\")")
	private String interests;
	@OneToOne(
		cascade = CascadeType.ALL,
		fetch = FetchType.LAZY,
		orphanRemoval = true
	)
	@JoinColumn(
		name = "picture_id",
		referencedColumnName = "id"

	)
	private ProfilePicture picture;
	@OneToMany(
		fetch = FetchType.LAZY,
		cascade = CascadeType.ALL,
		mappedBy = "owner"

	)
	private List<Estate> ownedEstates;

	// public List<String> getInterests() {
	// 	if (interests == null) {
	// 		return null;
	// 	} else {
	// 		return List.of(interests.split(","));
	// 	}
	// }

	public void setInterests(List<String> interests) {
		String finalValue = "";
		for (int i = 0; i < interests.size(); i++) {
			if (i != interests.size() - 1) {
				finalValue += interests.get(i) + ",";
			} else {
				finalValue += interests.get(i);
			}
		}
		this.interests = finalValue;
		

	}

	public void setPictureWithFile(MultipartFile picture) throws IOException {
		if(picture == null) { 
			this.picture = null;  
		} else {
			this.picture = new ProfilePicture(picture);
		}

	}
	public void setPicture(ProfilePicture picture) throws IOException {
		if(picture == null) { 
			this.picture = null;  
		} else {
			this.picture = picture;
		}

	}


}
