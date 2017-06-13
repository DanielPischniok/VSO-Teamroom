package de.pingpong.vsoteamroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.pingpong.vsoteamroom.entities.Teamroom;

public interface TeamroomRepository extends JpaRepository<Teamroom, Long> {
	
	public Teamroom findByRoomname(String roomname);

}
