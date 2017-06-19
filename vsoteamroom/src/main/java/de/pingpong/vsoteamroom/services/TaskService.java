package de.pingpong.vsoteamroom.services;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.pingpong.vsoteamroom.VsoteamroomApplication;
import de.pingpong.vsoteamroom.components.TaskBean;
import de.pingpong.vsoteamroom.components.UseraccountBean;
import de.pingpong.vsoteamroom.entities.Comment;
import de.pingpong.vsoteamroom.entities.Task;
import de.pingpong.vsoteamroom.entities.Useraccount;
import de.pingpong.vsoteamroom.repository.CommentRepository;
import de.pingpong.vsoteamroom.repository.TaskRepository;

@RestController
@RequestMapping(VsoteamroomApplication.REST_COMMON_PATH +  "/tasks")
public class TaskService {
	
	@Autowired
	TaskBean taskBean;
	
	@Autowired
	UseraccountBean userAccountBean;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	TaskRepository taskRepository;
	
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
	
	@CrossOrigin(origins ="http://localhost")
	@RequestMapping("/createComment")
	public String createComment(@RequestBody String data){
		try{
			Task task = null;
			Useraccount user = null;
			Comment comment = null;
			String[] params = data.split("&");
			for(String param : params){
				String[] paramValue = param.split("=");
				if(paramValue[0].equals("task")){
					task = taskBean.findTaskById(paramValue[1]);
				}
				
				if(paramValue[0].equals("user")){
					user = userAccountBean.findByUsername(paramValue[1]);
				}
				
				if(paramValue[0].equals("text") && task != null && user != null){
					comment = new Comment();
					comment.setCreateDate(new Date());
					comment.setCreatedBy(user);
					comment.setTask(task);
					comment.setText(URLDecoder.decode(paramValue[1], "UTF-8"));
					comment.setTitle("Kommentar");
					comment = commentRepository.save(comment);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return "SUCCESS";
	}

}
