/*
 * Copyright (C) 2019, Liberty Mutual Group
 *
 * Created on Jul 3, 2019
 */

package com.projectManager.projectManager.repository;

import java.util.List;

import org.hibernate.annotations.Any;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectManager.projectManager.domain.Project;

/**
 * @author n0241133
 *
 */
@Repository
public interface ProjectManagerProjectRepository extends JpaRepository<Project, Long> {

	
}
