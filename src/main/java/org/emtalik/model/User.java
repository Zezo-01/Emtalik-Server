package org.emtalik.model;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	@Column(name = "username")
	private String username;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "fathers_name")
	private String fathersName;
	@Column(name = "grandfathers_name")
	private String grandfathersName;
	@Column(name = "sur_name")
	private String surName;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "made_on")
	private Timestamp madeOn;
	@Column(name = "contact_number")
	private String contactNumber;
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;
	@Column(name = "reports")
	private Integer reports;
	@Column(name = "interests")
	private String interests;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "picture_id", referencedColumnName = "id")
	private ProfilePicture picture;

	public List<String> getInterests() {
		if (interests == null) {
			return null;
		} else {
			return List.of(interests.split(","));
		}

	}

	public void setInterests(List<String> interests) {
		String finalValue = "";
		for (int i = 0; i < interests.size(); i++) {
			if (i != interests.size() - 1) {
				finalValue += interests.get(i) + " ,";
			} else {
				finalValue += interests.get(i);
			}
		}
		this.interests = finalValue;

	}

	public void setPicture(MultipartFile picture) throws IOException {
		this.picture = new ProfilePicture(picture);
		

	}


}
