package org.emtalik.Repositroy;

import java.util.Optional;

import org.emtalik.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepo extends JpaRepository<User, Integer> {

	public Optional<User> findByUsername(String username);
	public Optional<User> findByContactNumber(String contactNumber);
	public Optional<User> findByEmail(String email);
	public Optional<User> findByIdAndPassword(int id,String password);
	
}
