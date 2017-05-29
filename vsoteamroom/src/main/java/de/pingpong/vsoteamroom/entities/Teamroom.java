package de.pingpong.vsoteamroom.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Teamroom implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String roomname;
	
	@ManyToMany
	private List<Useraccount> users;
	
	@OneToMany(mappedBy = "room")
	private List<ProjectDefinition> projects;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public List<Useraccount> getUsers() {
		return users;
	}

	public void setUsers(List<Useraccount> users) {
		this.users = users;
	}

	public List<ProjectDefinition> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectDefinition> projects) {
		this.projects = projects;
	}
	

}
