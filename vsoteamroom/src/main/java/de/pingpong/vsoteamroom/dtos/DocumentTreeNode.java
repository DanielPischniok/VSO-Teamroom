package de.pingpong.vsoteamroom.dtos;

import java.util.List;

import de.pingpong.vsoteamroom.entities.Folder;

public class DocumentTreeNode {
	
	private Folder folder;
	
	private List<DocumentTreeNode> childNodes;

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public List<DocumentTreeNode> getChildNodes() {
		return childNodes;
	}

	public void setChildNodes(List<DocumentTreeNode> childNodes) {
		this.childNodes = childNodes;
	}

	
}
