package de.pingpong.vsoteamroom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.pingpong.vsoteamroom.VsoteamroomApplication;
import de.pingpong.vsoteamroom.components.TeamroomBean;
import de.pingpong.vsoteamroom.entities.Teamroom;

@RestController
@RequestMapping(VsoteamroomApplication.REST_COMMON_PATH +  "/teamrooms")
public class TeamroomService {

	@Autowired
	TeamroomBean teamroomBean;
	
	@RequestMapping("/findAllTeamrooms")
	public List<Teamroom> loadTeamrooms(){
		return teamroomBean.findAllTeamrooms();
	}
}
