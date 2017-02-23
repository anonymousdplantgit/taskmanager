package com.soucreation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.soucreation.model.Ressource;
import com.soucreation.repository.RessourceRepository;

@Controller
@RequestMapping("/ressources")
public class RessourceController {
	@Autowired
	private RessourceRepository ressourceRepository;
	private static Sort SORTING_DESC=new Sort(Sort.Direction.DESC, "ressourceId");
	
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		List<Ressource> ressourceList = ressourceRepository.findAll(SORTING_DESC);
		if (ressourceList != null) {
			model.addAttribute("ressourceList", ressourceList);
		}
		model.addAttribute("ressource", new Ressource());
		return "ressourceManagement";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, Ressource ressource) {
		try{
			ressourceRepository.save(ressource);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		List<Ressource> ressourceList = ressourceRepository.findAll(SORTING_DESC);
		if (ressourceList != null) {
			model.addAttribute("ressourceList", ressourceList);
		}
		model.addAttribute("ressource", new Ressource());
		return "ressourceManagement";
	}
	
	@RequestMapping(value = "/update{id}", method = RequestMethod.GET)
	public String update(Model model, Long id) {
		Ressource r = null;
		try{
			 r=ressourceRepository.findOne(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		List<Ressource> ressourceList = ressourceRepository.findAll(SORTING_DESC);
		if (ressourceList != null) {
			model.addAttribute("ressourceList", ressourceList);
			model.addAttribute("ressource", r);
		}
		return "ressourceManagement";
	}
	
	@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
	public String delete(Long id,Model model) {
		try{
			ressourceRepository.delete(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		List<Ressource> ressourceList = ressourceRepository.findAll(SORTING_DESC);
		if (ressourceList != null) {
			model.addAttribute("ressourceList", ressourceList);
		}
		model.addAttribute("ressource", new Ressource());
		return "ressourceManagement";
	}
}