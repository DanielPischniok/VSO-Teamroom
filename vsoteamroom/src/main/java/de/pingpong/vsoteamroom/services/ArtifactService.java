package de.pingpong.vsoteamroom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.pingpong.vsoteamroom.VsoteamroomApplication;
import de.pingpong.vsoteamroom.components.ArtifactBean;
import de.pingpong.vsoteamroom.entities.Folder;
import de.pingpong.vsoteamroom.repository.FolderRepository;

@RestController
@RequestMapping(VsoteamroomApplication.REST_COMMON_PATH + "/artifact")
public class ArtifactService {
	
	@Autowired
	ArtifactBean artifactBean;
	
	@Autowired
	FolderRepository folderRepository;
		
	@CrossOrigin(origins ="http://localhost")
	@RequestMapping(value = "/loadAll")
	public List<Folder> loadArtifactOverview(){
		return folderRepository.findAll();
	}
	
	@CrossOrigin(origins ="http://localhost")
	@RequestMapping(value = "/searchDocument")
	public List<Folder> searchArtifacts(@RequestParam(name = "filename") String filename){
		return artifactBean.findFoldersByFilename(filename);
	}

}
