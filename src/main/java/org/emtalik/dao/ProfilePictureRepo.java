package org.emtalik.dao;

import org.emtalik.model.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePictureRepo extends JpaRepository <ProfilePicture, Integer>
{
    
}
