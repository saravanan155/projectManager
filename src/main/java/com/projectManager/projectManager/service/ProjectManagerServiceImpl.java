/*
 * Copyright (C) 2019, Liberty Mutual Group
 *
 * Created on Jul 3, 2019
 */

package com.projectManager.projectManager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectManager.projectManager.domain.Parent;
import com.projectManager.projectManager.domain.Project;
import com.projectManager.projectManager.domain.Task;
import com.projectManager.projectManager.domain.User;
import com.projectManager.projectManager.repository.ProjectManagerParentRepository;
import com.projectManager.projectManager.repository.ProjectManagerProjectRepository;
import com.projectManager.projectManager.repository.ProjectManagerRepository;
import com.projectManager.projectManager.repository.ProjectManagerTaskRepository;

/**
 * @author n0241133
 *
 */
@Service
public class ProjectManagerServiceImpl implements ProjectManagerService {

	@Autowired
	ProjectManagerRepository projectManagerRepository;
	
	@Autowired
	ProjectManagerProjectRepository projectManagerProjectRepository;
	
	@Autowired
	ProjectManagerTaskRepository projectManagerTaskRepository;
	
	@Autowired
	ProjectManagerParentRepository projectManagerParentRepository;
	
	//User Table
	
	/* (non-Javadoc)
	 * @see com.projectManager.projectManager.service.ProjectManagerService#getUserDetails()
	 */
	@Override
	public List<User> getUserDetails(){
		List<User> users = new ArrayList<>();
		users = projectManagerRepository.findAll();
		return users;
	}
	
	@Override
	public List<User> getManagerDetails(){
		List<User> users = new ArrayList<>();
		users = projectManagerRepository.findByMgrInd(true);
		return users;
	}
	
	@Override
	public void deleteUserDetails(long id){
		projectManagerRepository.deleteById(id);
	}
	
	@Override
	public void saveUserDetails(User user){
		projectManagerRepository.save(user);
	}

	//Project Table
	public void saveProjectDetails(Project project){
		projectManagerProjectRepository.save(project);
	}
	
	public List<Project> getProjectDetails(){
		return projectManagerProjectRepository.findAll();
	}

	@Override
	public void deleteProjectDetails(long project_id){
		projectManagerProjectRepository.deleteById(project_id);
	}

	/* (non-Javadoc)
	 * @see com.projectManager.projectManager.service.ProjectManagerService#getTaskDetails()
	 */
	@Override
	public List<Task> getTaskDetails() {
		return projectManagerTaskRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.projectManager.projectManager.service.ProjectManagerService#getParentDetails()
	 */
	@Override
	public List<Parent> getParentDetails() {
		return projectManagerParentRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.projectManager.projectManager.service.ProjectManagerService#saveParentDetails(com.projectManager.projectManager.domain.Parent)
	 */
	@Override
	public void saveParentDetails(Parent parent) {
		projectManagerParentRepository.save(parent);
		
	}

	/* (non-Javadoc)
	 * @see com.projectManager.projectManager.service.ProjectManagerService#saveTaskDetails(com.projectManager.projectManager.domain.Task)
	 */
	@Override
	public void saveTaskDetails(Task task) {
		projectManagerTaskRepository.save(task);
		
	}

	/* (non-Javadoc)
	 * @see com.projectManager.projectManager.service.ProjectManagerService#getProjectCount()
	 */
	@Override
	public int getProjectCount(Project project) {
		// TODO Auto-generated method stub
		return projectManagerTaskRepository.countByProjectProjectId(project.getProjectId());
	}
}
