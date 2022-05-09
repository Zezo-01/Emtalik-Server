package org.emtalik.dao;

import org.emtalik.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepo extends JpaRepository<User, Integer> {

	
	
}
