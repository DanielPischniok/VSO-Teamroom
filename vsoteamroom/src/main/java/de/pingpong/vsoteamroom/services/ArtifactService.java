package de.pingpong.vsoteamroom.services;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.pingpong.vsoteamroom.VsoteamroomApplication;
import de.pingpong.vsoteamroom.components.ArtifactBean;
import de.pingpong.vsoteamroom.entities.Artifact;
import de.pingpong.vsoteamroom.entities.Folder;
import de.pingpong.vsoteamroom.repository.ArtifactRepository;
import de.pingpong.vsoteamroom.repository.FolderRepository;


@RestController
@RequestMapping(VsoteamroomApplication.REST_COMMON_PATH + "/artifact")
public class ArtifactService {
	
	@Autowired
	ArtifactBean artifactBean;
	
	@Autowired
	FolderRepository folderRepository;
	
	@Autowired
	ArtifactRepository artifactRepository;
		
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
	
	@CrossOrigin(origins ="http://localhost")
	@RequestMapping(value = "/download",produces="application/data")
	public ResponseEntity<Resource> downloadFile(@RequestParam(name = "file")String fileid){
		
		byte[] payload = artifactBean.loadPayloadById(fileid);
		Artifact artifact = artifactRepository.findOne(Long.parseLong(fileid));
		if(artifact == null){
			return null;
		}
		
		InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(payload));
		HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-Disposition", "attachment; filename="+artifact.getTitle());

	    return ResponseEntity.ok()
	    		.headers(headers)
	            .contentLength(payload.length)
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .body(resource);
		
	}

}
