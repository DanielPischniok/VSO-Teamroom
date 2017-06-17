package de.pingpong.vsoteamroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.pingpong.vsoteamroom.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
}
