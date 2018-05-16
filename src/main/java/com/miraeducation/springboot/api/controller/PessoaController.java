package com.miraeducation.springboot.api.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.miraeducation.springboot.api.entities.Pessoa;
import com.miraeducation.springboot.api.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepo;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Pessoa>> getPeople() {
		return new ResponseEntity<>(pessoaRepo.findAll(), HttpStatus.OK);
	}

}
