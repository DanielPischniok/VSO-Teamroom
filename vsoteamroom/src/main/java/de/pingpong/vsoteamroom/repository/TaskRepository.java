package de.pingpong.vsoteamroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import de.pingpong.vsoteamroom.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
	@Query("SELECT t FROM Task t WHERE t.assignee.username = :user ORDER BY t.id DESC")
	public List<Task> findLatestTasksForUser(@Param("user")String username);
	
}
