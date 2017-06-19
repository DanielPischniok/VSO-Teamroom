package de.pingpong.vsoteamroom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.pingpong.vsoteamroom.VsoteamroomApplication;
import de.pingpong.vsoteamroom.components.TeamProjectBean;
import de.pingpong.vsoteamroom.entities.TeamProject;

@RestController
@RequestMapping(VsoteamroomApplication.REST_COMMON_PATH + "/projects")
public class ProjectService {
	
	@Autowired
	TeamProjectBean teamprojectBean;
	
	@CrossOrigin(origins ="http://localhost")
	@RequestMapping(value = "/loadAll")
	public List<TeamProject> findAllProjects(){
		return teamprojectBean.loadAllProjects();
	}

}
