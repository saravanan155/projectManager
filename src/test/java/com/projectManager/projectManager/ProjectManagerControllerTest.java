/*
 * Copyright (C) 2019, Liberty Mutual Group
 *
 * Created on Aug 6, 2019
 */

package com.projectManager.projectManager;

/**
 * @author n0241133
 *
 */

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.projectManager.projectManager.domain.Parent;
import com.projectManager.projectManager.domain.Project;
import com.projectManager.projectManager.domain.ProjectView;
import com.projectManager.projectManager.domain.Task;
import com.projectManager.projectManager.domain.User;
import com.projectManager.projectManager.repository.*;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ProjectManagerApplication.class)
@ActiveProfiles("test")
public class ProjectManagerControllerTest extends TestCase {
	
	@Value("${local.server.port}")
    private Integer port;
    private String baseUrl;
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ProjectManagerProjectRepository projectRepository;

    @Autowired
    private ProjectManagerRepository userRepository;

    @Autowired
    private ProjectManagerParentRepository parentTaskRepository;

    @Autowired
    private ProjectManagerTaskRepository taskRepository;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        baseUrl = "http://localhost:".concat(port.toString()).concat("/api/v1");
        testRestTemplate = new TestRestTemplate();
    }
    
    @Test
    public void testAddUser() {

        User user = new User();
        user.setMgrId(true);
        user.setLastName("FirstNme");
        user.setFirstName("LastNme");
        user.setEmployeeId(12345);
        
        ResponseEntity<String> response = testRestTemplate.postForEntity(baseUrl.concat("/addUser"), user, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
    
    @Test
    public void testAddProject() {

        User user = new User();
        user.setMgrId(true);
        user.setLastName("FirstUsr");
        user.setFirstName("LastUsr");
        user.setEmployeeId(22345);
        userRepository.save(user);

        Project project = new Project();
        project.setProjectName("Test Project");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setPriority(3);
        project.setProjectStatus(true);
        project.setUser(user);

        ResponseEntity<String> response = testRestTemplate.postForEntity(baseUrl.concat("/addProject"), project, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void testAddParent() {

    	Parent pTask = new Parent();
    	pTask.setParentName("Test Parent");
        
        ResponseEntity<String> response = testRestTemplate.postForEntity(baseUrl.concat("/addParent"), pTask, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));        
    }
    
    @Test
    public void testAddTask() {

        User user = new User();
        user.setMgrId(true);
        user.setLastName("FirstUsr");
        user.setFirstName("LastUsr");
        user.setEmployeeId(22345);
        userRepository.save(user);

    	Parent parentTask = new Parent();
    	parentTask.setParentName("Test Parent");
    	parentTaskRepository.save(parentTask);

    	Project project = new Project();
        project.setProjectName("Test Project");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setPriority(3);
        project.setProjectStatus(true);
        project.setUser(user);
        projectRepository.save(project);
        
        Task task = new Task();
		task.setTaskName("Test Task");
		task.setStartDate(new Date());
		task.setEndDate(new Date());
		task.setPriority(3);
		task.setTaskStatus(true);
		task.setParent(parentTask);
		task.setProject(project);
		task.setUser(user);
		
		ResponseEntity<String> response = testRestTemplate.postForEntity(baseUrl.concat("/addTask"), task, String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
    
    @Test
    public void testGetUsers() throws Exception {
    	
    	User user = new User();
        user.setMgrId(true);
        user.setLastName("FirstNmeUpd");
        user.setFirstName("LastNmeUpd");
        user.setEmployeeId(12345);
        userRepository.save(user);
                
        ResponseEntity<String> response = testRestTemplate.getForEntity(baseUrl.concat("/getUsers"), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        
        List<User> userList = convertJsonToUserList(response.getBody());
        assertThat(userList.size(), equalTo(1));
    }
    
    @Test
	public void testGetProjects() throws Exception {
	    
    	User user = new User();
		user.setMgrId(true);
		user.setLastName("FirstUsr");
		user.setFirstName("LastUsr");
		user.setEmployeeId(22345);
		userRepository.save(user);
		
		Project project = new Project();
		project.setProjectName("Test Project");
		project.setStartDate(new Date());
		project.setEndDate(new Date());
		project.setPriority(3);
		project.setProjectStatus(true);
		project.setUser(user);
		projectRepository.save(project);
		
	    ResponseEntity<String> response = testRestTemplate.getForEntity(baseUrl.concat("/getProjects"), String.class);
	    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	    
	    List<ProjectView> projectList = convertJsonToProjectViewList(response.getBody());
	    assertThat(projectList.size(), equalTo(1));
	}
    
    
    @Test
    public void testGetTasks() throws Exception {
    	
    	Parent parent = new Parent();
    	parent.setParentName("Test Parent");
    	parentTaskRepository.save(parent);
    	
    	User user = new User();
		user.setMgrId(true);
		user.setLastName("FirstUsr");
		user.setFirstName("LastUsr");
		user.setEmployeeId(22345);
		userRepository.save(user);
		
		Project project = new Project();
		project.setProjectName("Test Project");
		project.setStartDate(new Date());
		project.setEndDate(new Date());
		project.setPriority(3);
		project.setProjectStatus(true);
		project.setUser(user);
		projectRepository.save(project);
		
		Task task = new Task();
		task.setEndDate(new Date());
		task.setPriority(8);
		task.setStartDate(new Date());
		task.setTaskStatus(true);
		task.setTaskName("Test Task");
		task.setParent(parent);
		task.setProject(project);
		task.setUser(user);
		taskRepository.save(task);
		
    	ResponseEntity<String> response = testRestTemplate.getForEntity(baseUrl.concat("/getTasks"), String.class);
	    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	    
	    List<Task> taskList = convertJsonToTaskList(response.getBody());
	    assertThat(taskList.size(), equalTo(1));
    }
    
    @Test
    public void testGetParentList() throws Exception {
    	
    	Parent pTask = new Parent();
    	pTask.setParentName("Test Parent");
    	parentTaskRepository.save(pTask);
    	
    	ResponseEntity<String> response = testRestTemplate.getForEntity(baseUrl.concat("/getParents"), String.class);
	    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	    
	    List<Parent> taskList = convertJsonToParentList(response.getBody());
	    assertThat(taskList.size(), equalTo(1));
    }
            
	private List<User> convertJsonToUserList(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, User.class));
    }
	
	private List<ProjectView> convertJsonToProjectViewList(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, ProjectView.class));
    }
	
	private List<Task> convertJsonToTaskList(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, Task.class));
    }
	
	private List<Parent> convertJsonToParentList(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, Parent.class));
    }
	
	@After
    public void tearDown() throws Exception {
        super.tearDown();
        baseUrl = null;
        testRestTemplate = null;
        taskRepository.deleteAll();
        projectRepository.deleteAll();
        userRepository.deleteAll();
        parentTaskRepository.deleteAll();
    }
}