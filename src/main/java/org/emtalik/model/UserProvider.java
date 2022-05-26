package org.emtalik.model;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProvider 
{
    private int id;
    private String username;
    private Role role;
    private List<String> interests;
    private byte[] picture;
    

    public static UserProvider copyUser(User user){
        return new UserProvider(user.getId(), user.getUsername(),user.getRole(), List.of(user.getInterests().split(",")) , user.getPicture().getContent());
    }
}
