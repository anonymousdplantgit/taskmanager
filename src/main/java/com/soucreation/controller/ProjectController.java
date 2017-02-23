package com.soucreation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.soucreation.model.Project;
import com.soucreation.model.State;
import com.soucreation.model.Type;
import com.soucreation.repository.ProjectRepository;
import com.soucreation.repository.StateRepository;
import com.soucreation.repository.TypeRepository;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	private ProjectRepository projectRepository;
	private StateRepository stateRepository;
	private TypeRepository typeRepository;
	private static Sort SORTING_DESC_PROJECT=new Sort(Sort.Direction.DESC, "projectId");
	private static Sort SORTING_DESC_STATE=new Sort(Sort.Direction.ASC, "stateId");
	private static Sort SORTING_DESC_TYPE=new Sort(Sort.Direction.ASC, "typeId");
	
	@Autowired
	public ProjectController(ProjectRepository projectRepository,StateRepository stateRepository,TypeRepository typeRepository) {
		this.projectRepository = projectRepository;
		this.stateRepository = stateRepository;
		this.typeRepository = typeRepository;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		List<Project> projectList = projectRepository.findAll(SORTING_DESC_PROJECT);
		List<State> statList = stateRepository.findAll(SORTING_DESC_STATE);
		List<Type> typeList = typeRepository.findAll(SORTING_DESC_TYPE);
		model.addAttribute("typeList", typeList);
		model.addAttribute("stateList", statList);
		if (projectList != null) {
			model.addAttribute("projectList", projectList);
		}
		
		model.addAttribute("project", new Project());
		return "projectManagement";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, Project project) {
		try{
			projectRepository.save(project);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		List<Project> projectList = projectRepository.findAll(SORTING_DESC_PROJECT);
		List<State> statList = stateRepository.findAll(SORTING_DESC_STATE);
		List<Type> typeList = typeRepository.findAll(SORTING_DESC_TYPE);
		model.addAttribute("typeList", typeList);
		model.addAttribute("stateList", statList);
		if (projectList != null) {
			model.addAttribute("projectList", projectList);
		}
		model.addAttribute("project", new Project());
		return "projectManagement";
	}
	
	@RequestMapping(value = "/update{id}", method = RequestMethod.GET)
	public String update(Model model, Long id) {
		Project r = null;
		try{
			 r=projectRepository.findOne(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		List<Project> projectList = projectRepository.findAll(SORTING_DESC_PROJECT);
		List<State> statList = stateRepository.findAll(SORTING_DESC_STATE);
		List<Type> typeList = typeRepository.findAll(SORTING_DESC_TYPE);
		model.addAttribute("typeList", typeList);
		model.addAttribute("stateList", statList);
		if (projectList != null) {
			model.addAttribute("projectList", projectList);
			model.addAttribute("project", r);
		}
		return "projectManagement";
	}
	
	@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
	public String delete(Long id,Model model) {
		try{
			projectRepository.delete(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		List<Project> projectList = projectRepository.findAll(SORTING_DESC_PROJECT);
		List<State> statList = stateRepository.findAll(SORTING_DESC_STATE);
		List<Type> typeList = typeRepository.findAll(SORTING_DESC_TYPE);
		model.addAttribute("typeList", typeList);
		model.addAttribute("stateList", statList);
		if (projectList != null) {
			model.addAttribute("projectList", projectList);
		}
		model.addAttribute("project", new Project());
		return "projectManagement";
	}
}