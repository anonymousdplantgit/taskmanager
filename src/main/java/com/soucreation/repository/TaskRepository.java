package com.soucreation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soucreation.model.Task;
import com.soucreation.model.WorkOrder;

public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findByWorkOrder(WorkOrder workOrder);
	List<Task> findByWorkOrderWorkOrderId(Long id);
}