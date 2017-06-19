package de.pingpong.vsoteamroom.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.pingpong.vsoteamroom.entities.Useraccount;
import de.pingpong.vsoteamroom.repository.UseraccountRepository;

@Component
public class UseraccountBean {

	@Autowired
	UseraccountRepository userAccountRepository;
	
	@Autowired
	VsoTeamroomCommonBean commonBean;
	
	public Useraccount login(String username, String password){
		Useraccount user = userAccountRepository.findByUsernameAndPassword(username, commonBean.hashPassword(password));
		
		
		if(user == null){
			user = userAccountRepository.findByEmailAndPassword(username, commonBean.hashPassword(password));
		}
		
		
		if(user != null){
			user.setPassword(VsoTeamroomCommonBean.HIDE_PASSWORD);
		}
		return user;
	}
	
	public Useraccount findByUsername(String username){
		return userAccountRepository.findByUsername(username);
	}
	
}
