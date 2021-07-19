package com.kuliah.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kuliah.main.entity.Mahasiswa;
import com.kuliah.main.repository.MahasiswaRepository;

@Controller
public class MahasiswaPage {

	@Autowired
	MahasiswaRepository mahasiswaRepo;
	
	@GetMapping("/mahasiswa/view")
	public String viewHomePage(Model model) {
		model.addAttribute("listMahasiswa", mahasiswaRepo.findAll());
		model.addAttribute("active", 1);
		return "view_mahasiswa";
		
	}
	
	@GetMapping("/mahasiswa/add")
	public String viewAddMahasiswa(Model model) {
		model.addAttribute("mahasiswa", new Mahasiswa());
		return "add_mahasiswa";
	}
	
	@PostMapping("/mahasiswa/view")
	public String addMahasiswa(@ModelAttribute Mahasiswa mahasiswa, Model model) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String plainPassword = mahasiswa.getPassword();
		String encodePassword = passwordEncoder.encode(plainPassword);
		mahasiswa.setPassword(encodePassword);
		
		this.mahasiswaRepo.save(mahasiswa);
		model.addAttribute("listMahasiswa", mahasiswaRepo.findAll());
		return "redirect:/mahasiswa/view";
	}
	
	@GetMapping("/mahasiswa/update/{id}")
	public String viewUpdateMahasiswa(@PathVariable String id, Model model) {
		Mahasiswa mahasiswa = mahasiswaRepo.findByIdMahasiswa(Long.parseLong(id));
		model.addAttribute("mahasiswa", mahasiswa);
		return "add_mahasiswa";
	}
	
	@GetMapping("/mahasiswa/delete/{id}")
	public String deleteMahasiswa(@PathVariable String id, Model model) {
		this.mahasiswaRepo.deleteById(Long.parseLong(id));
		model.addAttribute("listMahasiswa", mahasiswaRepo.findAll());
		return "redirect:/mahasiswa/view";
	}

}
