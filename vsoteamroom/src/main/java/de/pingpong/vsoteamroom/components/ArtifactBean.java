package de.pingpong.vsoteamroom.components;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.pingpong.vsoteamroom.dtos.FolderDTO;
import de.pingpong.vsoteamroom.entities.Artifact;
import de.pingpong.vsoteamroom.entities.Folder;
import de.pingpong.vsoteamroom.repository.ArtifactRepository;
import de.pingpong.vsoteamroom.repository.FolderRepository;

@Component
public class ArtifactBean {
	
	@Autowired
	FolderRepository folderRepository;
	
	@Autowired
	ArtifactRepository artifactRepository;
	
	public List<FolderDTO> loadArtifactOverview(){
		List<Folder> allFolders = folderRepository.findAll();
		
		List<FolderDTO> dtos = new ArrayList<>();
		for(Folder f : allFolders){
			dtos.add(FolderDTO.convertToDto(f));
		}
		return dtos;
	}
	
	public List<Folder> findFoldersByFilename(String filename){
		List<Folder> result = new ArrayList<>();
		List<Artifact> artifactForFilename = artifactRepository.findArtifactByFilename(filename);
		Set<Folder> docFolder = new HashSet<>();
		for(Artifact a : artifactForFilename){
			docFolder.add(a.getFolder());
		}

		if(!docFolder.isEmpty()){
			result.addAll(docFolder);
		}
	
		return result;
	}

}
