package org.emtalik.model;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProvider 
{
    private int id;
    private Role role;
    private List<String> interests;

    public void copyUser(User user){
        this.id = user.getId();
        this.role = user.getRole();
        this.interests = user.getInterests();
    }
}
