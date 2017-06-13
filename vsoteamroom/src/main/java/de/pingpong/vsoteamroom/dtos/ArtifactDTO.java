package de.pingpong.vsoteamroom.dtos;

import java.io.Serializable;

import de.pingpong.vsoteamroom.entities.Artifact;

public class ArtifactDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String title;
	
	private int version;
	
	private String comment;
	
	private String businessKey;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	
	public static ArtifactDTO convertToDTO(Artifact artifact){
		ArtifactDTO dto = new ArtifactDTO();
		dto.setBusinessKey(artifact.getBusinessKey());
		dto.setComment(artifact.getComment());
		dto.setId(artifact.getId());
		dto.setTitle(artifact.getTitle());
		dto.setVersion(artifact.getVersion());
		return dto;
	}

}
