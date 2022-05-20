package org.emtalik.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public List<String> getInterests() {
		if(interests == null) {
			return null;
		} else {
			System.out.println("The interests are NOT NULL\n" + interests);
			return List.of(interests.split(","));
		}
		
	}

}
