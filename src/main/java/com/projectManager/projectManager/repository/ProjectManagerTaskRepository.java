/*
 * Copyright (C) 2019, Liberty Mutual Group
 *
 * Created on Jul 3, 2019
 */

package com.projectManager.projectManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectManager.projectManager.domain.Task;

/**
 * @author n0241133
 *
 */
@Repository
public interface ProjectManagerTaskRepository extends JpaRepository<Task, Long> {

	/**
	 * @param projectId
	 * @return
	 */
	int countByProjectProjectId(long projectId);

	
}
