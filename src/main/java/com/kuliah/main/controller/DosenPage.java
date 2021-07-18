package com.kuliah.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kuliah.main.entity.Dosen;
import com.kuliah.main.repository.DosenRepository;

@Controller
public class DosenPage {
	
	@Autowired
	DosenRepository dosenRepo;
	
	@GetMapping("/dosen/view")
	public String viewHomePage(Model model) {
		model.addAttribute("listDosen", dosenRepo.findAll());
		model.addAttribute("active",2);
		return "view_dosen";
		
	}
	
	@GetMapping("/dosen/add")
	public String viewAddDosen(Model model) {
		model.addAttribute("dosen", new Dosen());
		return "add_dosen";
	}
	
	@PostMapping("/dosen/view")
	public String addDosen(@ModelAttribute Dosen dosen, Model model) {
		this.dosenRepo.save(dosen);
		model.addAttribute("listDosen", dosenRepo.findAll());
		return "redirect:/dosen/view";
	}
	
	@GetMapping("/dosen/update/{id}")
	public String viewUpdateDosen(@PathVariable long id, Model model) {
		Dosen dosen = dosenRepo.findByIdDosen(id);
		model.addAttribute("dosen", dosen);
		return "add_dosen";
	}
	
	@GetMapping("/dosen/delete/{id}")
	public String deleteDosen(@PathVariable long id, Model model) {
		this.dosenRepo.deleteById(id);
		model.addAttribute("listDosen", dosenRepo.findAll());
		return "redirect:/dosen/view";
	}

}
