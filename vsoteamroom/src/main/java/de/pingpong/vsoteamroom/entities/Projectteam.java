package de.pingpong.vsoteamroom.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Projectteam implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String teamname;
	
	@ManyToMany
	private List<Useraccount> members;
	
	@OneToMany
	private List<TeamProject> projects;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public List<Useraccount> getMembers() {
		return members;
	}

	public void setMembers(List<Useraccount> members) {
		this.members = members;
	}

	public List<TeamProject> getProjects() {
		return projects;
	}

	public void setProjects(List<TeamProject> projects) {
		this.projects = projects;
	}
	
}
