package de.pingpong.vsoteamroom.services;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.pingpong.vsoteamroom.components.VsoTeamroomCommonBean;

@Service
@Transactional
@javax.transaction.Transactional
public class StartApplicationService {

	 @PersistenceContext
	 EntityManager em;
	 
	 @Autowired
	 VsoTeamroomCommonBean commonBean;
	 
	 @Autowired
	 DataLoaderService dataLoader;
	 
	 @PostConstruct
	 public void initData(){
		 dataLoader.loadData();
	 }
	
}
