/*
 * Copyright (C) 2019, Liberty Mutual Group
 *
 * Created on Jul 3, 2019
 */

package com.projectManager.projectManager.service;

import java.util.List;

import com.projectManager.projectManager.domain.Parent;
import com.projectManager.projectManager.domain.Project;
import com.projectManager.projectManager.domain.Task;
import com.projectManager.projectManager.domain.User;

/**
 * @author n0241133
 *
 */
public interface ProjectManagerService {

	//User Table
	List<User> getUserDetails();
	List<User> getManagerDetails();
	void deleteUserDetails (long id);
	void saveUserDetails (User user);

	//Project Table	
	List<Project> getProjectDetails();
	void saveProjectDetails(Project project);
	void deleteProjectDetails(long project_id);
	/**
	 * @return
	 */
	List<Task> getTaskDetails();
	/**
	 * @return
	 */
	List<Parent> getParentDetails();
	/**
	 * @param parent
	 */
	void saveParentDetails(Parent parent);
	
}