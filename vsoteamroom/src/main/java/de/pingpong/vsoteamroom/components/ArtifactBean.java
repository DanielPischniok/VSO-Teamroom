package de.pingpong.vsoteamroom.components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.pingpong.vsoteamroom.dtos.FolderDTO;
import de.pingpong.vsoteamroom.entities.Folder;
import de.pingpong.vsoteamroom.repository.FolderRepository;

@Component
public class ArtifactBean {
	
	@Autowired
	FolderRepository folderRepository;
	
	public List<FolderDTO> loadArtifactOverview(){
		List<Folder> allFolders = folderRepository.findAll();
		
		List<FolderDTO> dtos = new ArrayList<>();
		for(Folder f : allFolders){
			dtos.add(FolderDTO.convertToDto(f));
		}
		return dtos;
	}

}
