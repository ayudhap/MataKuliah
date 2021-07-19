
package com.kuliah.main.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kuliah.main.entity.Pertanyaan;
import com.kuliah.main.repository.PertanyaanRepository;
import com.kuliah.main.utility.FileUtility;

@Controller
public class PertanyaanPage {

	@Autowired
	PertanyaanRepository pertanyaanRepo;

	@GetMapping("/pertanyaan/view")
	public String viewPertanyaan(Model model) {
		model.addAttribute("listpertanyaan", pertanyaanRepo.findAll());
		model.addAttribute("active", 4);
		return "view_pertanyaan";
	}

	@GetMapping("/pertanyaan/add")
	public String viewAddPertanyaan(Model model) {
		model.addAttribute("pertanyaan", new Pertanyaan());
		return "add_pertanyaan";
	}

	@PostMapping("/pertanyaan/view")
	public String addPertanyaan(@ModelAttribute Pertanyaan pertanyaan, @RequestParam("file") MultipartFile file,
			Model model) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String uploadDir = "/user-photos";
		try {
			FileUtility.saveFile(uploadDir, fileName, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pertanyaan.setStatusGambar(uploadDir + "/" + fileName);
		this.pertanyaanRepo.save(pertanyaan);
		model.addAttribute("listpertanyaan", pertanyaanRepo.findAll());
		return "redirect:/pertanyaan/view";
	}

	@GetMapping("/pertanyaan/update/{id}")
	public String viewUpdatePertanyaan(@PathVariable String id, Model model) {
		Optional<Pertanyaan> pertanyaan = pertanyaanRepo.findById(Long.parseLong(id));
		model.addAttribute("pertanyaan", pertanyaan);
		return "add_pertanyaan";
	}

	@GetMapping("/pertanyaan/delete/{id}")
	public String deletePertanyaan(@PathVariable String id, Model model) {
		this.pertanyaanRepo.deleteById(Long.parseLong(id));
		model.addAttribute("listpertanyaan", pertanyaanRepo.findAll());
		return "redirect:/pertanyaan/view";
	}

}