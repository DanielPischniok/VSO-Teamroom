package de.pingpong.vsoteamroom.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Userrole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String rolename;

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
}
