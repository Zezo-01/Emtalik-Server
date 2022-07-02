package org.emtalik.Repositroy;

import java.util.List;

import org.emtalik.model.Estate;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface EstateRepo extends JpaRepository<Estate, Integer> {
    List<Estate> findByApprovedTrue();
    List<Estate> findByApprovedFalse();



}
