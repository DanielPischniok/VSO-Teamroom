package de.pingpong.vsoteamroom.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Artifact implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String title;
	
	private int version;
	
	@Lob
	@Column(columnDefinition="BINARY(5000)")
	@JsonIgnore
	private byte[] data;
	
	private String comment;
	
	@Column(unique = true)
	private String businessKey;
	
	@ManyToOne
	private Task task;
	
	@ManyToOne
	private Comment commentAttachment;
	
	@ManyToMany
	private List<Tag> tags;
	
	@ManyToOne
	@JsonIgnore
	private Folder folder;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public byte[] getData() {
		return data;
	}
	
	@JsonIgnore
	public void setData(byte[] data) {
		this.data = data;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Comment getCommentAttachment() {
		return commentAttachment;
	}

	public void setCommentAttachment(Comment commentAttachment) {
		this.commentAttachment = commentAttachment;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	
	
}
