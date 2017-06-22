package de.pingpong.vsoteamroom.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Useraccount implements Serializable{
	
	private static final long serialVersionUID = 5443847814544069033L;

	@Id
	private String username;
	
	@JsonIgnore
	private String password;
	
	private String name;
	
	private String surname;
	
	private boolean active;
	
	private Date expirationDate;
	
	private String email;
	
	private Date verificationDate;
	
	private String phonenumber;
	
	@ManyToMany(mappedBy="members")
	private List<Projectteam> teams;
	
	@ManyToMany(mappedBy="users")
	private List<Teamroom> rooms;
	
	@ManyToMany
	private List<Userrole> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getVerificationDate() {
		return verificationDate;
	}

	public void setVerificationDate(Date verificationDate) {
		this.verificationDate = verificationDate;
	}

	public List<Userrole> getRoles() {
		return roles;
	}

	public void setRoles(List<Userrole> roles) {
		this.roles = roles;
	}

	public List<Projectteam> getTeams() {
		return teams;
	}

	public void setTeams(List<Projectteam> teams) {
		this.teams = teams;
	}

	public List<Teamroom> getRooms() {
		return rooms;
	}

	public void setRooms(List<Teamroom> rooms) {
		this.rooms = rooms;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
}
