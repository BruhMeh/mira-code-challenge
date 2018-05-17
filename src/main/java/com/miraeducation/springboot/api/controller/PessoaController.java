package com.miraeducation.springboot.api.controller;

import java.net.URI;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.miraeducation.springboot.api.entities.Pessoa;
import com.miraeducation.springboot.api.exception.PessoaNotFoundException;
import com.miraeducation.springboot.api.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public Collection<Pessoa> getPessoas() {
		return pessoaRepository.findAll();
	}

	@GetMapping("/{pessoaId}")
	public Pessoa getPessoa(@PathVariable Long pessoaId) {
		return pessoaRepository.findById(pessoaId).orElseThrow(() -> new PessoaNotFoundException(pessoaId));
	}

	@PostMapping
	public ResponseEntity<?> adicionaPessoa(@Valid @RequestBody Pessoa novaPessoa) {

		Pessoa pessoaSalva = this.pessoaRepository.save(new Pessoa(novaPessoa.getNome(), novaPessoa.getSobrenome(),
				novaPessoa.getCpf(), novaPessoa.getDataNascimento(), novaPessoa.getEnderecos(),
				novaPessoa.getTelefones(), novaPessoa.getEmails()));

		URI location = ServletUriComponentsBuilder.newInstance().buildAndExpand(pessoaSalva.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{pessoaId}")
	public ResponseEntity<?> alteraPessoa(@Valid @RequestBody Pessoa pessoaAlterada, @PathVariable Long pessoaId) {

		pessoaRepository.findById(pessoaId).orElseThrow(() -> new PessoaNotFoundException(pessoaId));

		pessoaAlterada.setId(pessoaId);
		pessoaRepository.save(pessoaAlterada);
		
		final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

		return ResponseEntity.created(uri).body("");
	}

	@DeleteMapping("/{pessoaId}")
	public ResponseEntity<?> deletePessoa(@PathVariable Long pessoaId) {
		return pessoaRepository
				.findById(pessoaId)
				.map( pessoa -> {
					pessoaRepository.deleteById(pessoaId);
					return ResponseEntity.noContent().build();
				}).orElseThrow(() -> new PessoaNotFoundException(pessoaId));		
	}

}
