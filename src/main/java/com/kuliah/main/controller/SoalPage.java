package com.kuliah.main.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kuliah.main.entity.Soal;
import com.kuliah.main.repository.PertanyaanRepository;
import com.kuliah.main.repository.SoalRepository;

@Controller
public class SoalPage {
	
	@Autowired
	SoalRepository soalRepo;
	
	@Autowired
	PertanyaanRepository pertanyaanRepo;
	
	@GetMapping("/soal/view")
	public String viewSoal(Model model) {
		model.addAttribute("listSoal", soalRepo.findAll());
		model.addAttribute("active",5);
		return "view_soal";
	}
	
	@GetMapping("/soal/add")
	public String viewAddSoal(Model model) {
		model.addAttribute("soal", new Soal());
		model.addAttribute("listPertanyaan", pertanyaanRepo.findAll());
		return "add_soal";
	}
	
	@PostMapping("/soal/view")
	public String addSoal(@ModelAttribute Soal soal, Model model) {
		this.soalRepo.save(soal);
		model.addAttribute("listSoal", soalRepo.findAll());
		return "view_soal";
	}
	
	@GetMapping("/soal/update/{id}")
	public String viewUpdateSoal(@PathVariable long id, Model model) {
		Optional<Soal> soal = soalRepo.findById(id);
		model.addAttribute("soal", soal);
		model.addAttribute("listPertanyaan", pertanyaanRepo.findAll());
		return "add_soal";
	}
	
	@GetMapping("/soal/delete/{id}")
	public String deleteSoal(@PathVariable long id, Model model) {
		this.soalRepo.deleteById(id);
		model.addAttribute("listSoal", soalRepo.findAll());
		return "redirect:/soal/view";
	}
	
}
