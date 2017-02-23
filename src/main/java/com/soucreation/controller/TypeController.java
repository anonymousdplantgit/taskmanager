package com.soucreation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.soucreation.model.Type;
import com.soucreation.repository.TypeRepository;

@Controller
@RequestMapping("/types")
public class TypeController {
	private TypeRepository typeRepository;
	private static Sort SORTING_DESC=new Sort(Sort.Direction.DESC, "typeId");
	
	@Autowired
	public TypeController(TypeRepository typeRepository) {
		this.typeRepository = typeRepository;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		List<Type> typeList = typeRepository.findAll(SORTING_DESC);
		if (typeList != null) {
			model.addAttribute("typeList", typeList);
		}
		model.addAttribute("type", new Type());
		return "typeManagement";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, Type type) {
		try{
			typeRepository.save(type);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		List<Type> typeList = typeRepository.findAll(SORTING_DESC);
		if (typeList != null) {
			model.addAttribute("typeList", typeList);
		}
		model.addAttribute("type", new Type());
		return "typeManagement";
	}
	
	@RequestMapping(value = "/update{id}", method = RequestMethod.GET)
	public String update(Model model, Long id) {
		Type r = null;
		try{
			 r=typeRepository.findOne(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		List<Type> typeList = typeRepository.findAll(SORTING_DESC);
		if (typeList != null) {
			model.addAttribute("typeList", typeList);
			model.addAttribute("type", r);
		}
		return "typeManagement";
	}
	
	@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
	public String delete(Long id,Model model) {
		try{
			typeRepository.delete(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		List<Type> typeList = typeRepository.findAll(SORTING_DESC);
		if (typeList != null) {
			model.addAttribute("typeList", typeList);
		}
		model.addAttribute("type", new Type());
		return "typeManagement";
	}
}