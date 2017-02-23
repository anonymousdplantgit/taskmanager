package com.soucreation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.soucreation.model.Task;
import com.soucreation.model.WorkOrder;
import com.soucreation.repository.TaskRepository;
import com.soucreation.repository.WorkOrderRepository;

@Controller
@RequestMapping("/tasks")
public class TaskController {
	private TaskRepository taskRepository;
	private WorkOrderRepository workOrderRepository;

	@Autowired
	public TaskController(TaskRepository taskRepository, WorkOrderRepository workOrderRepository) {
		this.taskRepository = taskRepository;
		this.workOrderRepository = workOrderRepository;
	}

	@RequestMapping(value = "/home{id}", method = RequestMethod.GET)
	public String home(Model model, Long id) {
		List<Task> taskList = taskRepository.findByWorkOrderWorkOrderId((id));
		WorkOrder workOrder = workOrderRepository.findOne(id);
		Task task = new Task();
		task.setWorkOrder(workOrder);
		if (taskList != null) {
			model.addAttribute("taskList", taskList);
		}
		model.addAttribute("task", task);
		return "taskManagement";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, Task task) {
		try {
			taskRepository.save(task);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Task> taskList = taskRepository.findByWorkOrderWorkOrderId(task.getWorkOrder().getId());
		if (taskList != null) {
			model.addAttribute("taskList", taskList);
		}
		Task w = new Task();
		w.setWorkOrder(task.getWorkOrder());
		model.addAttribute("task", w);
		return "taskManagement";
	}

	@RequestMapping(value = "/update{id}", method = RequestMethod.GET)
	public String update(Model model, Long id) {
		Task r = null;
		try {
			r = taskRepository.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Task> taskList = taskRepository.findByWorkOrderWorkOrderId(r.getWorkOrder().getId());
		if (taskList != null) {
			model.addAttribute("taskList", taskList);
			model.addAttribute("task", r);
		}
		return "taskManagement";
	}

	@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
	public String delete(Long id, Model model) {
		Task task = taskRepository.findOne(id);
		try {
			taskRepository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Task> taskList = taskRepository.findByWorkOrderWorkOrderId(task.getWorkOrder().getId());
		if (taskList != null) {
			model.addAttribute("taskList", taskList);
		}
		WorkOrder workOrder = task.getWorkOrder();
		task = new Task();
		task.setWorkOrder(workOrder);
		model.addAttribute("task", task);
		return "taskManagement";
	}
}