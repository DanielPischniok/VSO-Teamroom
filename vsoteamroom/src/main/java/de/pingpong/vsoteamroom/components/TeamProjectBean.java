package de.pingpong.vsoteamroom.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.pingpong.vsoteamroom.entities.TeamProject;
import de.pingpong.vsoteamroom.repository.ProjectRepository;

@Component
public class TeamProjectBean {

	@Autowired
	ProjectRepository projectRepository;
	
	public List<TeamProject> loadAllProjects(){
		return projectRepository.findAll();
	}
}
