package de.pingpong.vsoteamroom.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.pingpong.vsoteamroom.enums.TaskStatus;
import de.pingpong.vsoteamroom.enums.TaskType;

@Entity
public class Task implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Enumerated(EnumType.STRING)
	private TaskType type;
	
	@Enumerated(EnumType.STRING)
	private TaskStatus status;
	
	private String tasktitle;
	
	private String description;
	
	private Date startDate;
	
	private Date endDate;
	
	private Date createDate;
	
	private Date updateDate;
	
	private int priority;
	
	private double estimation;
	
	private int percentageOfCompletion;
	
	@ManyToOne
	private TeamProject project;
	
	@JsonIgnore
	@OneToMany(mappedBy="task")
	private List<Comment> comments;
	
	@JsonIgnore
	@OneToMany
	private List<Artifact> artifacts;
	
	@ManyToOne
	private Useraccount reporter;
	
	@ManyToOne
	private Useraccount assignee;
	
	@PrePersist
	public void preCreate(){
		createDate = new Date();
	}
	
	@PreUpdate
	public void preUpdate(){
		updateDate = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTasktitle() {
		return tasktitle;
	}

	public void setTasktitle(String tasktitle) {
		this.tasktitle = tasktitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public double getEstimation() {
		return estimation;
	}

	public void setEstimation(double estimation) {
		this.estimation = estimation;
	}

	public int getPercentageOfCompletion() {
		return percentageOfCompletion;
	}

	public void setPercentageOfCompletion(int percentageOfCompletion) {
		this.percentageOfCompletion = percentageOfCompletion;
	}

	public TaskType getType() {
		return type;
	}

	public void setType(TaskType type) {
		this.type = type;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Artifact> getArtifacts() {
		return artifacts;
	}

	public void setArtifacts(List<Artifact> artifacts) {
		this.artifacts = artifacts;
	}

	public TeamProject getProject() {
		return project;
	}

	public void setProject(TeamProject project) {
		this.project = project;
	}

	public Useraccount getReporter() {
		return reporter;
	}

	public void setReporter(Useraccount reporter) {
		this.reporter = reporter;
	}

	public Useraccount getAssignee() {
		return assignee;
	}

	public void setAssignee(Useraccount assignee) {
		this.assignee = assignee;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
	
}
