package com.kuliah.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kuliah.main.entity.PlotMataKuliah;
import com.kuliah.main.repository.DosenRepository;
import com.kuliah.main.repository.MahasiswaRepository;
import com.kuliah.main.repository.MataKuliahRepository;
import com.kuliah.main.repository.PlotMataKuliahRepository;
import com.kuliah.main.repository.SoalRepository;

@Controller
public class PlotMataKuliahPage {
	
	@Autowired
	PlotMataKuliahRepository plotMataKuliahRepo;
	
	@Autowired
	DosenRepository dosenRepo;
	
	@Autowired
	MahasiswaRepository mahasiswaRepo;
	
	@Autowired
	MataKuliahRepository mataKuliahRepo;
	
	@Autowired
	SoalRepository soalRepo;
	
	@GetMapping("/plotmatakuliah/view")
	public String viewPlotMataKuliah(Model model) {
		model.addAttribute("listPlotMataKuliah", plotMataKuliahRepo.findAll());
		model.addAttribute("active",6);
		return "view_plotmatakuliah";
	}
	
	@GetMapping("/plotmatakuliah/add")
	public String viewAddPlotMataKuliah(Model model) {
		model.addAttribute("plotmatakuliah", new PlotMataKuliah());
		model.addAttribute("listDosen", dosenRepo.findAll());
		model.addAttribute("listMahasiswa", mahasiswaRepo.findAll());
		model.addAttribute("listMataKuliah", mataKuliahRepo.findAll());
		model.addAttribute("listSoal", soalRepo.findAll());
		return "add_plotmatakuliah";
	}
	
	@PostMapping("/plotmatakuliah/view")
	public String addPlotMataKuliah(@ModelAttribute PlotMataKuliah plotMataKuliah, Model model) {
		this.plotMataKuliahRepo.save(plotMataKuliah);
		model.addAttribute("listPlotMataKuliah", plotMataKuliahRepo.findAll());
		return "view_plotmatakuliah";
	}
	
	@GetMapping("/plotmatakuliah/update/{id}")
	public String viewUpdatePlotMataKuliah(@PathVariable Long id, Model model) {
		PlotMataKuliah plotMataKuliah = plotMataKuliahRepo.findByIdPlotMataKuliah(id);
		model.addAttribute("plotmatakuliah", plotMataKuliah);
		model.addAttribute("listDosen", dosenRepo.findAll());
		model.addAttribute("listMahasiswa", mahasiswaRepo.findAll());
		model.addAttribute("listMataKuliah", mataKuliahRepo.findAll());
		model.addAttribute("listSoal", soalRepo.findAll());
		return "add_plotmatakuliah";
	}
	
	@GetMapping("/plotmatakuliah/delete/{id}")
	public String deletePlotMataKuliah(@PathVariable Long id, Model model) {
		this.plotMataKuliahRepo.deleteById(id);
		model.addAttribute("listPlotMataKuliah", plotMataKuliahRepo.findAll());
		return "redirect:/plotmatakuliah/view";
	}
	
	@GetMapping("/plotmatakuliah/ujian/{id}")
	public String viewUjianPlotMataKuliah(@PathVariable Long id, Model model) {
		model.addAttribute("ujian", soalRepo.findById(id));
		return "view_ujian";
	}
	
}
