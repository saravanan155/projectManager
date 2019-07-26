/*
 * Copyright (C) 2019, Liberty Mutual Group
 *
 * Created on Jul 2, 2019
 */

package com.projectManager.projectManager.controller;

import java.io.Console;
import java.util.*;

import com.projectManager.projectManager.domain.Parent;
import com.projectManager.projectManager.domain.Project;
import com.projectManager.projectManager.domain.Task;
import com.projectManager.projectManager.domain.User;
import com.projectManager.projectManager.service.ProjectManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author n0241133
 *
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")

public class ProjectManagerController {

	@Autowired
	ProjectManagerService projectManagerService ;
	
	//User Screen related services

	@GetMapping(value="/getUsers",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsersDetails(){
		List<User> users =projectManagerService.getUserDetails(); 
		return users;
	}
	
	@GetMapping(value="/getManagers",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> getManagersDetails(){
		List<User> users =projectManagerService.getManagerDetails(); 
		return users;
	}
	@DeleteMapping(value="/deleteUser/{id}")
	public void deleteUsersDetails(@PathVariable long id){
		projectManagerService.deleteUserDetails(id); 
	}
	
	@PostMapping(value="/addUser",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void addUsersDetails(@RequestBody User user){
		projectManagerService.saveUserDetails(user); 
	}

	//Project Screen related services
	
	@PostMapping(value="/addProject",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void addProjectDetails(@RequestBody Project project){
		projectManagerService.saveProjectDetails(project); 
	}

	@GetMapping(value="/getProjects",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Project> getProjectDetails(){
		List<Project> projects=projectManagerService.getProjectDetails();
		 
		//System.console().writer().println("project object:" + projects);
		return projects;
	}
	
	@DeleteMapping(value="/deleteProject/{project_id}")
	public void deleteProjectDetails(@PathVariable long project_id){
		projectManagerService.deleteProjectDetails(project_id); 
	}
	
	//Tasks Screen related services
	@GetMapping(value="/getTasks",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Task> getTaskDetails(){
		List<Task> tasks=projectManagerService.getTaskDetails();
		//System.console().writer().println("Task object:" + tasks);
		return tasks;
	}
	
	@GetMapping(value="/getParents",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Parent> getParentDetails(){
		List<Parent> parents=projectManagerService.getParentDetails();
		return parents;
	}
	
	@PostMapping(value="/addParent",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void addParentDetails(@RequestBody Parent parent){
		projectManagerService.saveParentDetails(parent); 
	}
	
}
