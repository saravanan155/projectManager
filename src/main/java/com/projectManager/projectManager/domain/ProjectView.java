package com.projectManager.projectManager.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author n0241133
 *
 */
 
public class ProjectView {

	private long projectId;
	
	private String projectName;
	private Date startDate;
	private Date endDate;
	private int priority;
	private User manager;
	private boolean projectStatus;
	private int taskCount;
	
	public boolean isProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(boolean projectStatus) {
		this.projectStatus = projectStatus;
	}
	public int getTaskCount() {
		return taskCount;
	}
	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}
	public User getUser() {
		return manager;
	}
	public void setUser(User user) {
		this.manager = user;
	}
	public Date getEndDate() {
		return endDate;
	}
	public int getPriority() {
		return priority;
	}
	public long getProjectId() {
		return projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
}
