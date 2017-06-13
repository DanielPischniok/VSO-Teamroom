package de.pingpong.vsoteamroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.pingpong.vsoteamroom.entities.Useraccount;

public interface UseraccountRepository extends JpaRepository<Useraccount, String> {
	
	Useraccount findByUsernameAndPassword(String username, String password);
	
	Useraccount findByEmailAndPassword(String email, String password);
	
	Useraccount findByUsername(String username);

}
