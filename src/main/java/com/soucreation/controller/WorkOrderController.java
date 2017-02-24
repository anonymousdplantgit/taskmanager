package com.soucreation.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.soucreation.model.Project;
import com.soucreation.model.Ressource;
import com.soucreation.model.WorkOrder;
import com.soucreation.repository.ProjectRepository;
import com.soucreation.repository.RessourceRepository;
import com.soucreation.repository.WorkOrderRepository;

@Controller
@RequestMapping("/workOrders")
public class WorkOrderController {
	private WorkOrderRepository workOrderRepository;
	private RessourceRepository ressourceRepository;
	private ProjectRepository projectRepository;
	private static Sort SORTING_DESC_STATE=new Sort(Sort.Direction.ASC, "ressourceId");
	
	@Autowired
	public WorkOrderController(WorkOrderRepository workOrderRepository,RessourceRepository ressourceRepository,ProjectRepository projectRepository) {
		this.workOrderRepository = workOrderRepository;
		this.ressourceRepository = ressourceRepository;
		this.projectRepository = projectRepository;
	}
	
	@RequestMapping(value = "/home{id}", method = RequestMethod.GET)
	public String home(Model model,Long id,Principal principal) {
		List<WorkOrder> workOrderList = workOrderRepository.findByProjectProjectId((id));
		List<Ressource> ressourceList = ressourceRepository.findAll(SORTING_DESC_STATE);
		Project project=projectRepository.findOne(id);
		WorkOrder workOrder=new WorkOrder();
		workOrder.setProject(project);
		if (workOrderList != null) {
			model.addAttribute("workOrderList", workOrderList);
		}
		model.addAttribute("ressourceList", ressourceList);
		model.addAttribute("workOrder", workOrder);
		return "workOrderManagement";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, WorkOrder workOrder,Principal principal) {
		try{
			workOrderRepository.save(workOrder);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		List<WorkOrder> workOrderList = workOrderRepository.findByProjectProjectId(workOrder.getProject().getId());
		List<Ressource> ressourceList = ressourceRepository.findAll(SORTING_DESC_STATE);
		model.addAttribute("ressourceList", ressourceList);
		if (workOrderList != null) {
			model.addAttribute("workOrderList", workOrderList);
		}
		WorkOrder w=new WorkOrder();
		w.setProject(workOrder.getProject());
		model.addAttribute("workOrder", w);
		model.addAttribute("userName", principal.getName());
		return "workOrderManagement";
	}
	
	@RequestMapping(value = "/update{id}", method = RequestMethod.GET)
	public String update(Model model, Long id,Principal principal) {
		WorkOrder r = null;
		try{
			 r=workOrderRepository.findOne(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		List<WorkOrder> workOrderList = workOrderRepository.findByProjectProjectId(r.getProject().getId());
		List<Ressource> ressourceList = ressourceRepository.findAll(SORTING_DESC_STATE);
		model.addAttribute("ressourceList", ressourceList);
		if (workOrderList != null) {
			model.addAttribute("workOrderList", workOrderList);
			model.addAttribute("workOrder", r);
		}
		model.addAttribute("userName", principal.getName());
		return "workOrderManagement";
	}
	
	@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
	public String delete(Long id,Model model,Principal principal) {
		WorkOrder workOrder=workOrderRepository.findOne(id);
		try{
			workOrderRepository.delete(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		List<WorkOrder> workOrderList = workOrderRepository.findByProjectProjectId(workOrder.getProject().getId());
		List<Ressource> ressourceList = ressourceRepository.findAll(SORTING_DESC_STATE);
		model.addAttribute("ressourceList", ressourceList);
		if (workOrderList != null) {
			model.addAttribute("workOrderList", workOrderList);
		}
		Project project=workOrder.getProject();
		workOrder=new WorkOrder();
		workOrder.setProject(project);
		model.addAttribute("workOrder", workOrder);
		model.addAttribute("userName", principal.getName());
		return "workOrderManagement";
	}
}