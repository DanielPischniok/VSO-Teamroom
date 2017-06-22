package de.pingpong.vsoteamroom.components;

import java.util.ArrayList;
import java.util.Date;
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
	
	@Autowired
	VsoTeamroomCommonBean commonBean;
	
	private final static String NEW_LINE ="\r\n";
	
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
	
	public void saveTeamroom(String teamroomName, String userdata, String option)throws TeamroomExistsException{
		
		Teamroom dbRoom = teamroomRepository.findByRoomname(teamroomName);
		
		if(dbRoom == null){
			dbRoom = new Teamroom();
			dbRoom.setRoomname(teamroomName);
		}else{
			if(!option.equals("edit")){
				throw new TeamroomExistsException();
			}
		}
		
		List<Useraccount> users = new ArrayList<>();

		if(userdata != null && !userdata.isEmpty()){
			String userLines[] = userdata.split(NEW_LINE);
			for(String line : userLines){
				String[]uservalues = line.split(";", -1);
				Useraccount dbUser = userRepository.findByForeNameSurNameAndEmail(uservalues[0], uservalues[1], uservalues[2]);
				if(dbUser == null){
					dbUser = new Useraccount();
					dbUser.setActive(true);
					dbUser.setEmail(uservalues[2]);
					dbUser.setExpirationDate(new Date());
					dbUser.setName(uservalues[0]);
					dbUser.setSurname(uservalues[1]);
					dbUser.setPhonenumber(uservalues[3]);
					dbUser.setUsername(uservalues[0].toLowerCase());
					dbUser.setPassword(commonBean.hashPassword("hallo"));
					userRepository.saveAndFlush(dbUser);
					dbUser = userRepository.findByForeNameSurNameAndEmail(uservalues[0], uservalues[1], uservalues[2]);
				}
				
				users.add(dbUser);
			}
			
		}
		
		dbRoom.setUsers(users);
		
		teamroomRepository.saveAndFlush(dbRoom);
		
	}
	
	
	public String loadTeamroomUserExport(String roomid){
		Teamroom room = teamroomRepository.findOne(Long.parseLong(roomid));
		if(room == null){
			return "";
		}
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(room.getRoomname());
		builder.append(NEW_LINE);
		for(Useraccount user : room.getUsers()){
			builder.append(user.getName())
				   .append(";")
				   .append(user.getSurname())
				   .append(";")
				   .append(user.getEmail())
				   .append(";")
				   .append(user.getPhonenumber())
				   .append(NEW_LINE);
		   
		}
		
		return builder.toString();
		
	}
	
	public Teamroom findTeamroomById(String roomid){
		return teamroomRepository.findOne(Long.parseLong(roomid));
	}


}
