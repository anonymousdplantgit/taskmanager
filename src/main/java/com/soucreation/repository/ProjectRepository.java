package com.soucreation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soucreation.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	List<Project> findByCode(String code);
}