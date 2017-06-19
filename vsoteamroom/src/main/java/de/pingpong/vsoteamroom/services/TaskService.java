package de.pingpong.vsoteamroom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.pingpong.vsoteamroom.VsoteamroomApplication;
import de.pingpong.vsoteamroom.components.TaskBean;
import de.pingpong.vsoteamroom.entities.Comment;
import de.pingpong.vsoteamroom.entities.Task;

@RestController
@RequestMapping(VsoteamroomApplication.REST_COMMON_PATH +  "/tasks")
public class TaskService {
	
	@Autowired
	TaskBean taskBean;
	
	@CrossOrigin(origins ="http://localhost")
	@RequestMapping("/findAllTasks")
	public List<Task> findAllTasks(){
		return taskBean.findAllTasks();
	}
	
	@CrossOrigin(origins ="http://localhost")
	@RequestMapping("/findById")
	public Task findById(@RequestParam(name="id")String id){
		
		return taskBean.findTaskById(id);
	}
	
	
	@CrossOrigin(origins ="http://localhost")
	@RequestMapping("/loadComments")
	public List<Comment> loadComments(@RequestParam(name="taskid")String id){
		
		return taskBean.loadCommentsForTask(id);
	}
	
	
	@CrossOrigin(origins ="http://localhost")
	@RequestMapping("/findLatestTasks")
	public List<Task> findLatestTasks(){
		return taskBean.findLatestTasks();
	}
	
	@CrossOrigin(origins ="http://localhost")
	@RequestMapping("/findLatestUserTasks")
	public List<Task> findLatestTasks(@RequestParam(name="user")String user){
		return taskBean.findLatestTasksWithUser(user);
	}

}
