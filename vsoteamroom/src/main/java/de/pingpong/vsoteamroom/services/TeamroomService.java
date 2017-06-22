package de.pingpong.vsoteamroom.services;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.pingpong.vsoteamroom.VsoteamroomApplication;
import de.pingpong.vsoteamroom.components.TeamroomBean;
import de.pingpong.vsoteamroom.components.VsoTeamroomCommonBean;
import de.pingpong.vsoteamroom.entities.Teamroom;
import de.pingpong.vsoteamroom.exception.TeamroomExistsException;

@RestController
@RequestMapping(VsoteamroomApplication.REST_COMMON_PATH +  "/teamrooms")
public class TeamroomService {

	@Autowired
	TeamroomBean teamroomBean;
	
	@Autowired
	VsoTeamroomCommonBean commonBean;
	
	@CrossOrigin(origins ="http://localhost")
	@RequestMapping("/findAllTeamrooms")
	public List<Teamroom> loadTeamrooms(){
		return teamroomBean.findAllTeamrooms();
	}
	
	@CrossOrigin(origins ="http://localhost")
	@RequestMapping(value = "/create")
	public String createTeamroom(@RequestBody String roomnameData){
		String[] params = roomnameData.split("&");
		Map<String, String> paramMap = commonBean.convertParamsToMap(params);
		
		try {
			teamroomBean.saveTeamroom(paramMap.get("room"), paramMap.get("userdata"), paramMap.get("selected"));
		} catch (TeamroomExistsException e) {
			e.printStackTrace();
			return "ERROR";
		}
		
		return "SUCCESS";
	}
	
	@CrossOrigin(origins ="http://localhost")
	@RequestMapping(value = "/download",produces="application/data")
	public ResponseEntity<Resource> downloadFile(@RequestParam(name = "room")String roomid){
		
		String teamroomExport = teamroomBean.loadTeamroomUserExport(roomid);
		
		Teamroom room = teamroomBean.findTeamroomById(roomid);
		
		InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(teamroomExport.getBytes()));
		HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-Disposition", "attachment; filename="+room.getRoomname()+".csv");

	    return ResponseEntity.ok()
	    		.headers(headers)
	            .contentLength(teamroomExport.getBytes().length)
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .body(resource);
		
	}
	
	
}
