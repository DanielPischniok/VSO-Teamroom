package de.pingpong.vsoteamroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import de.pingpong.vsoteamroom.entities.Useraccount;

public interface UseraccountRepository extends JpaRepository<Useraccount, String> {
	
	Useraccount findByUsernameAndPassword(String username, String password);
	
	Useraccount findByEmailAndPassword(String email, String password);
	
	Useraccount findByUsername(String username);
	
	@Query("SELECT u FROM Useraccount u WHERE u.name = :forename AND u.surname = :surname AND u.email = :email")
	Useraccount findByForeNameSurNameAndEmail(@Param("forename")String forname, @Param("surname")String surname, @Param("email")String email);

}
