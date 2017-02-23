package com.soucreation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soucreation.model.Project;
import com.soucreation.model.WorkOrder;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
	List<WorkOrder> findByProject(Project project);
	List<WorkOrder> findByProjectProjectId(Long id);
}