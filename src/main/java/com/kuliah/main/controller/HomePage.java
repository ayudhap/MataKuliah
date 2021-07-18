package com.kuliah.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kuliah.main.repository.MahasiswaRepository;

@Controller
public class HomePage {
	
	@Autowired
	MahasiswaRepository mahasiswaRepo;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listMahasiswa", mahasiswaRepo.findAll());

		return "view_mahasiswa";
		
	}
	
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		
		
		return "login";
		
	}
	
	

}
