package de.pingpong.vsoteamroom.dtos;

import java.util.ArrayList;
import java.util.List;

import de.pingpong.vsoteamroom.entities.Artifact;
import de.pingpong.vsoteamroom.entities.Folder;

public class FolderDTO {

	private long id;
	
	private String folderName;
	
	private String parentFolderName;
	
	private List<ArtifactDTO> artifacts;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getParentFolderName() {
		return parentFolderName;
	}

	public void setParentFolderName(String parentFolderName) {
		this.parentFolderName = parentFolderName;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public List<ArtifactDTO> getArtifacts() {
		return artifacts;
	}

	public void setArtifacts(List<ArtifactDTO> artifacts) {
		this.artifacts = artifacts;
	}
	
	public static FolderDTO convertToDto(Folder folder){
		FolderDTO dto = new FolderDTO();
		dto.setFolderName(folder.getFolderName());
		dto.setId(folder.getId());
		if(folder.getParentFolder() != null){
			dto.setParentFolderName(folder.getParentFolder().getFolderName());
		}
		if(folder.getArtifacts() != null && !folder.getArtifacts().isEmpty()){
			dto.setArtifacts(new ArrayList<>());
			for(Artifact a : folder.getArtifacts()){
				dto.getArtifacts().add(ArtifactDTO.convertToDTO(a));
			}
		}
		return dto;
	}

}
