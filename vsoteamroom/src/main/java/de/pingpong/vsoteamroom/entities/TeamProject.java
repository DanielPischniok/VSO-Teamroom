package de.pingpong.vsoteamroom.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TeamProject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private ProjectDefinition projectDefinition;
	
	private Date teamStartDate;
	
	@ManyToOne
	private Projectteam team;
	
	@OneToMany(mappedBy="project")
	@JsonIgnore
	private List<Task> tasks;

	public ProjectDefinition getProjectDefinition() {
		return projectDefinition;
	}

	public void setProjectDefinition(ProjectDefinition projectDefinition) {
		this.projectDefinition = projectDefinition;
	}

	public Date getTeamStartDate() {
		return teamStartDate;
	}

	public void setTeamStartDate(Date teamStartDate) {
		this.teamStartDate = teamStartDate;
	}

	public Projectteam getTeam() {
		return team;
	}

	public void setTeam(Projectteam team) {
		this.team = team;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	
	
}
