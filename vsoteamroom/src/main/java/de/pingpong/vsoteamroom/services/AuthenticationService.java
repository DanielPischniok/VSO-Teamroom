package de.pingpong.vsoteamroom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.pingpong.vsoteamroom.VsoteamroomApplication;
import de.pingpong.vsoteamroom.components.UseraccountBean;
import de.pingpong.vsoteamroom.entities.Useraccount;

@RestController
@RequestMapping(VsoteamroomApplication.REST_COMMON_PATH + "/auth")
public class AuthenticationService {

	@Autowired
	UseraccountBean userAccountBean;

	@CrossOrigin(origins ="http://localhost")
	@RequestMapping("/login")
	public Useraccount loginUser(@RequestParam(name = "user", defaultValue = "test") String username,
			@RequestParam(name = "pass", defaultValue = "test") String password) {
		return userAccountBean.login(username, password);
	}
}
