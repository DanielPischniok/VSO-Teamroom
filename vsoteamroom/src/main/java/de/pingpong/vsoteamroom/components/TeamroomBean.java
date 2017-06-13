package de.pingpong.vsoteamroom.components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.pingpong.vsoteamroom.entities.ProjectDefinition;
import de.pingpong.vsoteamroom.entities.Teamroom;
import de.pingpong.vsoteamroom.entities.Useraccount;
import de.pingpong.vsoteamroom.exception.TeamroomExistsException;
import de.pingpong.vsoteamroom.repository.TeamroomRepository;
import de.pingpong.vsoteamroom.repository.UseraccountRepository;

@Component
public class TeamroomBean {
	
	@Autowired
	TeamroomRepository teamroomRepository;
	
	@Autowired
	UseraccountRepository userRepository;
	
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
	
	public void saveTeamroom(String teamroomName, List<String> usernames)throws TeamroomExistsException{
		
		Teamroom dbRoom = teamroomRepository.findByRoomname(teamroomName);
		
		if(dbRoom != null){
			throw new TeamroomExistsException();
		}
		
		dbRoom = new Teamroom();
		dbRoom.setRoomname(teamroomName);
		
		List<Useraccount> users = new ArrayList<>();
		for(String user : usernames){
			Useraccount dbUser = userRepository.findByUsername(user);
			if(dbUser != null){
				users.add(dbUser);
			}
		}
		
		dbRoom.setUsers(users);
		
		teamroomRepository.saveAndFlush(dbRoom);
		
	}
	


}
