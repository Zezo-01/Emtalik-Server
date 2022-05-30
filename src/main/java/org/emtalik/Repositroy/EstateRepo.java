package org.emtalik.Repositroy;

import org.emtalik.model.Estate;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface EstateRepo extends JpaRepository<Estate, Integer> {
    
}
