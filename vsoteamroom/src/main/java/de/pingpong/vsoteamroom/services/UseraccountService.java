package de.pingpong.vsoteamroom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.pingpong.vsoteamroom.VsoteamroomApplication;
import de.pingpong.vsoteamroom.entities.Useraccount;
import de.pingpong.vsoteamroom.repository.UseraccountRepository;

@RestController
@RequestMapping(VsoteamroomApplication.REST_COMMON_PATH +  "/users")
public class UseraccountService {

	@Autowired
	UseraccountRepository userAccountRepository;
	
	@CrossOrigin(origins ="http://localhost")
	@RequestMapping("/findAllUsers")
	public List<Useraccount> findAllUsers(){
		
		return userAccountRepository.findAll();
	}
	
	@CrossOrigin(origins ="http://localhost")
	@RequestMapping("/loadProfile")
	public Useraccount loadUser(@RequestParam(name = "user", defaultValue = "test")String username){
		return userAccountRepository.findByUsername(username);
	}
}
