package de.pingpong.vsoteamroom.components;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import de.pingpong.vsoteamroom.entities.Comment;
import de.pingpong.vsoteamroom.entities.Task;
import de.pingpong.vsoteamroom.entities.TeamProject;
import de.pingpong.vsoteamroom.entities.Useraccount;
import de.pingpong.vsoteamroom.enums.TaskStatus;
import de.pingpong.vsoteamroom.enums.TaskType;
import de.pingpong.vsoteamroom.repository.ProjectRepository;
import de.pingpong.vsoteamroom.repository.TaskRepository;
import de.pingpong.vsoteamroom.repository.UseraccountRepository;

@Component
public class TaskBean {

	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	UseraccountRepository userRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	public List<Task> findAllTasks(){
		return taskRepository.findAll(new Sort(Direction.DESC, "id"));
	}
	
	public List<Task> findLatestTasks(){
		List<Task> all = taskRepository.findAll(new Sort(Direction.DESC, "id"));
		if(all.size() > 3){
			return all.subList(0, 3);
		}
		return all;
	}
	
	public List<Task> findLatestTasksWithUser(String username){
		List<Task> all = taskRepository.findLatestTasksForUser(username);
		if(all.size() > 3){
			return all.subList(0, 3);
		}
		return all;
	}
	
	public Task findTaskById(String id){
		try{
			long longId = Long.parseLong(id);
			Task task = taskRepository.findOne(longId);
			return task;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Comment> loadCommentsForTask(String taskid){
		try{
			long longId = Long.parseLong(taskid);
			Task task = taskRepository.findOne(longId);
			Collections.reverse(task.getComments());
			return task.getComments();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean createTask(Map<String, String> paramMap) throws ParseException{
		//load creation user
		Useraccount createUser = userRepository.findByUsername(paramMap.get("user"));
		if(createUser == null){
			return false;
		}
		
		List<TeamProject> teamProjects = projectRepository.findProjectForProjectName(paramMap.get("userproject"));
		if(teamProjects.isEmpty()){
			return false;
		}
		
		TaskType type = TaskType.valueOf(paramMap.get("usercategory"));
		
		Useraccount assignee = userRepository.findByUsername(paramMap.get("userassignee"));
		
		Useraccount watcher = userRepository.findByUsername(paramMap.get("userwatcher"));
		
		Date vonDate = dateFormat.parse(paramMap.get("uservondate"));
		
		Date bisDate = dateFormat.parse(paramMap.get("userbisdate"));
		
		Task task = new Task();
		task.setCreateDate(new Date());
		task.setAssignee(assignee);
		task.setDescription(paramMap.get("userdescr"));
		task.setTasktitle(paramMap.get("usertitle"));
		task.setEndDate(bisDate);
		task.setEstimation(Double.parseDouble(paramMap.get("usertime")));
		task.setPercentageOfCompletion(0);
		task.setPriority(Integer.parseInt(paramMap.get("userprio")));
		task.setProject(teamProjects.get(0));
		task.setReporter(watcher);
		task.setStartDate(vonDate);
		task.setStatus(TaskStatus.NEW);
		task.setType(type);
		task.setUpdateDate(new Date());
		
		taskRepository.save(task);
		
		return true;
	}
}
