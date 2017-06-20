package de.pingpong.vsoteamroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import de.pingpong.vsoteamroom.entities.TeamProject;

public interface ProjectRepository extends JpaRepository<TeamProject, Long> {
	
	@Query("SELECT t FROM TeamProject t WHERE t.projectDefinition.projectName = :projectname")
	List<TeamProject> findProjectForProjectName(@Param("projectname") String projectname);

}
