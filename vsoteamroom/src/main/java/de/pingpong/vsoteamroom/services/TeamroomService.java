package de.pingpong.vsoteamroom.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.pingpong.vsoteamroom.VsoteamroomApplication;
import de.pingpong.vsoteamroom.components.TeamroomBean;
import de.pingpong.vsoteamroom.entities.Teamroom;
import de.pingpong.vsoteamroom.exception.TeamroomExistsException;

@RestController
@RequestMapping(VsoteamroomApplication.REST_COMMON_PATH +  "/teamrooms")
public class TeamroomService {

	@Autowired
	TeamroomBean teamroomBean;
	
	@CrossOrigin(origins ="http://localhost")
	@RequestMapping("/findAllTeamrooms")
	public List<Teamroom> loadTeamrooms(){
		return teamroomBean.findAllTeamrooms();
	}
	
	@CrossOrigin(origins ="http://localhost")
	@RequestMapping(value = "/create")
	public String createTeamroom(@RequestParam(name = "roomname", defaultValue = "test")String roomname, 
			@RequestParam(name="userdata") String userdata){
		String[] userArray = userdata.split(";");
		List<String> userList = new ArrayList<>();
		for(String user : userArray){
			userList.add(user);
		}
		
		try {
			teamroomBean.saveTeamroom(roomname, userList);
		} catch (TeamroomExistsException e) {
			e.printStackTrace();
			return "ERROR";
		}
		
		return "SUCCESS";
	}
	
	
}
