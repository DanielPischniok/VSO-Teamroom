package de.pingpong.vsoteamroom.components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.pingpong.vsoteamroom.entities.ProjectDefinition;
import de.pingpong.vsoteamroom.entities.Teamroom;
import de.pingpong.vsoteamroom.entities.Useraccount;
import de.pingpong.vsoteamroom.repository.TeamroomRepository;

@Component
public class TeamroomBean {
	
	@Autowired
	TeamroomRepository teamroomRepository;
	
	public List<Teamroom> findAllTeamrooms(){
		List<Teamroom> allRooms = new ArrayList<>();
		
		List<Teamroom> allFromDb = teamroomRepository.findAll();
		if(!allFromDb.isEmpty()){
			for(Teamroom tr : allFromDb){
				for(ProjectDefinition pd : tr.getProjects()){
					pd.setRoom(null);
				}
				
				for(Useraccount user : tr.getUsers()){
					user.setRooms(new ArrayList<>());
					user.setTeams(new ArrayList<>());
				}
				
				allRooms.add(tr);
			}
		}
		return allRooms;
	}

}
