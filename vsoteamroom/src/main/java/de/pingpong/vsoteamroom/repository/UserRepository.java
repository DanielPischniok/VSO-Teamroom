package de.pingpong.vsoteamroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.pingpong.vsoteamroom.entities.Useraccount;

public interface UserRepository extends JpaRepository<Useraccount, String> {

}
