package org.emtalik.Repositroy;

import org.emtalik.model.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilePictureRepo extends JpaRepository<ProfilePicture, Integer> {
    
}
