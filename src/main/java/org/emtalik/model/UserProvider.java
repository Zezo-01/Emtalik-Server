package org.emtalik.model;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProvider 
{
    private int id;
    private Role role;
    private List<String> interests;

    public static UserProvider copyUser(User user){
        return new UserProvider(user.getId(), user.getRole(), user.getInterests());
    }
}
