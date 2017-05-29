package de.pingpong.vsoteamroom.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tag implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String tagname;

}
