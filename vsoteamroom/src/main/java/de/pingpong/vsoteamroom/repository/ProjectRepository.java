package de.pingpong.vsoteamroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.pingpong.vsoteamroom.entities.TeamProject;

public interface ProjectRepository extends JpaRepository<TeamProject, Long> {

}
