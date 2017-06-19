package de.pingpong.vsoteamroom.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import de.pingpong.vsoteamroom.entities.Comment;
import de.pingpong.vsoteamroom.entities.Task;
import de.pingpong.vsoteamroom.repository.TaskRepository;

@Component
public class TaskBean {

	@Autowired
	TaskRepository taskRepository;
	
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
			return task.getComments();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
