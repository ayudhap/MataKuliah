package com.kuliah.main.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class UjianHasil {

	
	private List<Pertanyaan> pertanyaan;
	private List<String> jawaban;
	
	
	
}
