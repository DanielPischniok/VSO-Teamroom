package de.pingpong.vsoteamroom.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.pingpong.vsoteamroom.VsoteamroomApplication;
import de.pingpong.vsoteamroom.components.VsoTeamroomCommonBean;
import de.pingpong.vsoteamroom.entities.Useraccount;
import de.pingpong.vsoteamroom.repository.UseraccountRepository;

@RestController
@RequestMapping(VsoteamroomApplication.REST_COMMON_PATH +  "/users")
public class UseraccountService {

	@Autowired
	UseraccountRepository userAccountRepository;
	
	@Autowired 
	VsoTeamroomCommonBean commonBean;
	
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
	
	@CrossOrigin(origins ="http://localhost")
	@RequestMapping("/saveProfile")
	public String saveProfile(@RequestBody String formdata){
		String[] params = formdata.split("&");
		Map<String, String> paramMap = commonBean.convertParamsToMap(params);
		
		if(!paramMap.get("userpass1").equals(paramMap.get("userpass2"))){
			return "ERROR";
		}
		
		Useraccount account = userAccountRepository.findByUsername(paramMap.get("username"));
		if(account == null){
			return "ERROR";
		}
		
		if (!account.getPassword().equals(commonBean.hashPassword(paramMap.get("userpass1")))){
			return "ERROR";
		}
		
		account.setName(paramMap.get("uservorname"));
		account.setSurname(paramMap.get("nachname"));
		account.setPhonenumber(paramMap.get("userphone"));
		account.setEmail(paramMap.get("useremail"));
		
		userAccountRepository.save(account);
		
		return "SUCCESS";
	}
}
