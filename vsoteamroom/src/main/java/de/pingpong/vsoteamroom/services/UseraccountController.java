package de.pingpong.vsoteamroom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.pingpong.vsoteamroom.entities.Useraccount;
import de.pingpong.vsoteamroom.repository.UserRepository;

@RestController
public class UseraccountController {

	@Autowired
	UserRepository userAccountRepository;
	
	@RequestMapping("/findAllUsers")
	public List<Useraccount> findAllUsers(){
		return userAccountRepository.findAll();
	}
}
