package org.emtalik.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDetails {

    private String username;
    private String firstName;
    private String fathersName;
    private String grandfathersName;
    private String surName;
    private String email;
    private Timestamp madeOn;
    private String contactNumber;
    private Role role;
    private List<String> interests;

    public static UserDetails copy(User user){
        return new UserDetails(user.getUsername(),user.getFirstName(),user.getFathersName(),user.getGrandfathersName(),
                user.getSurName(),user.getEmail(),user.getMadeOn(),user.getContactNumber(),user.getRole(),List.of(user.getInterests().split(",")));
    }
}
