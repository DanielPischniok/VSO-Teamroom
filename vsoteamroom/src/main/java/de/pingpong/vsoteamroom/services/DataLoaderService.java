package de.pingpong.vsoteamroom.services;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import de.pingpong.vsoteamroom.components.VsoTeamroomCommonBean;
import de.pingpong.vsoteamroom.entities.Artifact;
import de.pingpong.vsoteamroom.entities.Folder;
import de.pingpong.vsoteamroom.entities.ProjectDefinition;
import de.pingpong.vsoteamroom.entities.Projectteam;
import de.pingpong.vsoteamroom.entities.Tag;
import de.pingpong.vsoteamroom.entities.Task;
import de.pingpong.vsoteamroom.entities.TeamProject;
import de.pingpong.vsoteamroom.entities.Teamroom;
import de.pingpong.vsoteamroom.entities.Useraccount;
import de.pingpong.vsoteamroom.entities.Userrole;
import de.pingpong.vsoteamroom.enums.TaskStatus;
import de.pingpong.vsoteamroom.enums.TaskType;

@Service
@Transactional
@org.springframework.transaction.annotation.Transactional
public class DataLoaderService {

	 @PersistenceContext
	 EntityManager em;
	 
	 @Autowired
	 VsoTeamroomCommonBean commonBean;
	 
	 @Value(value = "classpath:PrivateTabelle.xlsx")
	 Resource privateTabelle;
	 
	 @Value(value = "classpath:TestDocument.docx")
	 Resource testDocumentResource;
	
	public void loadData(){
		System.out.println("***Create User Role");
		 Userrole role = new Userrole();
		 role.setRolename("Admin");
		 em.persist(role);
		 
		 System.out.println("***Create Demo User");
		 Useraccount testAccount = new Useraccount();
		 testAccount.setActive(true);
		 testAccount.setEmail("demo@demo.de");
		 testAccount.setExpirationDate(new Date());
		 testAccount.setName("Demo");
		 testAccount.setPhonenumber("0163 - 12345 578");
		 testAccount.setPassword(commonBean.hashPassword("demo"));
		 testAccount.setExpirationDate(new Date());
		 testAccount.setSurname("Account");
		 testAccount.setUsername("demo");
		 testAccount.setVerificationDate(new Date());
		 testAccount.setRoles(new ArrayList<>());
		 testAccount.getRoles().add(role);
		 
		 em.persist(testAccount);
		 
		 
		 System.out.println("***Creating Teamroom");
		 Teamroom room = new Teamroom();
		 room.setRoomname("FST-Demo Teamroom");
		 em.persist(room);
		 
		 room.setUsers(new ArrayList<>());
		 room.getUsers().add(testAccount);
		 em.merge(room);
		 
		 System.out.println("***Creating Tags");
		 Tag demoTag = new Tag();
		 demoTag.setTagname("Demo");
		 em.persist(demoTag);
		 
		 Tag superTag = new Tag();
		 superTag.setTagname("Superdokument");
		 em.persist(superTag);
		 
		 System.out.println("***Creating Project definition");
		 ProjectDefinition project = new ProjectDefinition();
		 project.setDescription("DemoProjekt");
		 project.setEndDate(new Date());
		 project.setProjectName("FST Projekt");
		 project.setRoom(room);
		 project.setStartDate(new Date());
		 em.persist(project);
		 
		 System.out.println("***Creating Project Team");
		 Projectteam team = new Projectteam();
		 team.setTeamname("PingPong");
		 em.persist(team);
		 team.setMembers(new ArrayList<>());
		 team.getMembers().add(testAccount);
		 em.merge(team);
		 
		 System.out.println("***Creating TeamProject");
		 TeamProject teamproject = new TeamProject();
		 teamproject.setProjectDefinition(project);
		 teamproject.setTeam(team);
		 teamproject.setTeamStartDate(new Date());
		 em.persist(teamproject);
		 
		 System.out.println("***Creating folder");
		 Folder f1 = new Folder();
		 f1.setFolderName("DEMO");
		 em.persist(f1);
		 
		 Folder f2 = new Folder();
		 f2.setFolderName("BEISPIEL");
		 f2.setParentFolder(f1);
		 em.persist(f2);
		 
		 Folder f3 = new Folder();
		 f3.setFolderName("TEST");
		 em.persist(f3);
		 
		 Folder f4 = new Folder();
		 f4.setFolderName("PRIVAT");
		 f4.setParentFolder(f3);
		 em.persist(f4);
		 
		 System.out.println("***Creating Documents");
		 Artifact testDocument = new Artifact();
		 testDocument.setBusinessKey("TestDocument_id1_13062017");
		 testDocument.setComment("Kommentar zum Dokument");
		 testDocument.setTitle("TestDocument.docx");
		 testDocument.setVersion(1);
		 try {
			testDocument.setData(Files.readAllBytes(testDocumentResource.getFile().toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		testDocument.setFolder(f2);
		testDocument.setTags(new ArrayList<>());
		testDocument.getTags().add(demoTag);
		em.persist(testDocument);
		
		Artifact privateDocument = new Artifact();
		privateDocument.setBusinessKey("PrivateTabelle_id1_22062017");
		privateDocument.setComment("Kommentar Privat");
		privateDocument.setTitle("PrivateTabelle.xlsx");
		privateDocument.setVersion(1);
		try {
			privateDocument.setData(Files.readAllBytes(privateTabelle.getFile().toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		privateDocument.setFolder(f4);
		privateDocument.setTags(new ArrayList<>());
		privateDocument.getTags().add(superTag);
		em.persist(privateDocument);
		
		System.out.println("***Create Demo Task");
		Task task = new Task();
		task.setAssignee(testAccount);
		task.setCreateDate(new Date());
		task.setDescription("Demo Aufgabe als Beispiel");
		task.setEndDate(new Date());
		task.setEstimation(8);
		task.setPercentageOfCompletion(0);
		task.setPriority(1);
		task.setProject(teamproject);
		task.setReporter(testAccount);
		task.setStartDate(new Date());
		task.setStatus(TaskStatus.IN_PROGRESS);
		task.setTasktitle("Demo Aufgabe");
		task.setType(TaskType.AUFGABE);
		task.setUpdateDate(new Date());
		em.persist(task);
		
		Task task2 = new Task();
		task2.setAssignee(testAccount);
		task2.setCreateDate(new Date());
		task2.setDescription("Demo Aufgabe 2 als Beispiel");
		task2.setEndDate(new Date());
		task2.setEstimation(8);
		task2.setPercentageOfCompletion(0);
		task2.setPriority(1);
		task2.setProject(teamproject);
		task2.setReporter(testAccount);
		task2.setStartDate(new Date());
		task2.setStatus(TaskStatus.NEW);
		task2.setTasktitle("Demo Aufgabe 2");
		task2.setType(TaskType.AUFGABE);
		task2.setUpdateDate(new Date());
		em.persist(task2);
	}
	
}
