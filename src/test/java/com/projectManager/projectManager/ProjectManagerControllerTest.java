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
    
}