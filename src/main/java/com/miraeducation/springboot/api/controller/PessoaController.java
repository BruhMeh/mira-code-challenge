package com.miraeducation.springboot.api.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.wnameless.spring.bulkapi.AcceptBulk;
import com.github.wnameless.spring.bulkapi.Bulkable;
import com.miraeducation.springboot.api.entities.Pessoa;
import com.miraeducation.springboot.api.exception.PessoaNotFoundException;
import com.miraeducation.springboot.api.repository.PessoaRepository;
import com.miraeducation.springboot.api.resource.PessoaResource;

@Bulkable(autoApply = false)
@RestController
@RequestMapping(value = "/pessoas", produces = "application/hal+json")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public ResponseEntity<Resources<PessoaResource>> getPessoas(@RequestParam Map<String, String> queryParameters) {

		List<PessoaResource> pessoas;

		if (queryParameters.isEmpty()) {
			pessoas = pessoaRepository.findAll().stream().map(PessoaResource::new).collect(Collectors.toList());
		} else {
			pessoas = pessoaRepository
					.findByNomeAndSobrenomeAllIgnoreCaseOrCpf(queryParameters.get("nome"),
							queryParameters.get("sobrenome"), queryParameters.get("cpf"))
					.stream().map(PessoaResource::new).collect(Collectors.toList());
			if (pessoas.isEmpty()) {
				throw new PessoaNotFoundException();
			}
		}

		final Resources<PessoaResource> resources = new Resources<>(pessoas);

		resources.add(new Link(ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString(), "self"));

		return ResponseEntity.ok(resources);
	}

	@AcceptBulk
	@GetMapping("/{pessoaId}")
	public ResponseEntity<PessoaResource> getPessoa(@PathVariable Long pessoaId) {
		return pessoaRepository.findById(pessoaId).map(p -> ResponseEntity.ok(new PessoaResource(p)))
				.orElseThrow(() -> new PessoaNotFoundException(pessoaId));
	}

	@AcceptBulk
	@PostMapping
	public ResponseEntity<PessoaResource> adicionaPessoa(@Valid @RequestBody Pessoa novaPessoa) {

		final Pessoa pessoaSalva = this.pessoaRepository.save(new Pessoa(novaPessoa));

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(pessoaSalva.getId()).toUri();

		return ResponseEntity.created(location).body(new PessoaResource(pessoaSalva));
	}

	@AcceptBulk
	@PutMapping("/{pessoaId}")
	public ResponseEntity<PessoaResource> alteraPessoa(@Valid @RequestBody Pessoa pessoaAlterada,
			@PathVariable Long pessoaId) {

		if (!pessoaRepository.findById(pessoaId).isPresent()) {
			throw new PessoaNotFoundException(pessoaId);
		}

		pessoaAlterada.setId(pessoaId);
		pessoaRepository.save(pessoaAlterada);

		return ResponseEntity.ok(new PessoaResource(pessoaAlterada));
	}

	@AcceptBulk
	@DeleteMapping("/{pessoaId}")
	public ResponseEntity<?> deletePessoa(@PathVariable Long pessoaId) {
		return pessoaRepository.findById(pessoaId).map(pessoa -> {
			pessoaRepository.deleteById(pessoaId);
			return ResponseEntity.noContent().build();
		}).orElseThrow(() -> new PessoaNotFoundException(pessoaId));
	}

	@PatchMapping("/ativarOuDesativar")
	public ResponseEntity<Resources<PessoaResource>> ativaOuDesativaPessoa(
			@Valid @RequestBody Collection<Pessoa> pessoas) {
		
		Collection<Pessoa> pessoasAlteradas = new ArrayList<>();
		
		pessoas.forEach(pessoaParaAlterar -> {
			pessoasAlteradas.add(pessoaRepository.findById(pessoaParaAlterar.getId())
			.map(pessoa -> {
				pessoa.setAtivo(pessoaParaAlterar.isAtivo());
				pessoaRepository.save(pessoa);
				return pessoa;
			}).orElseThrow(() -> new PessoaNotFoundException(pessoaParaAlterar.getId())));
		});

		final Resources<PessoaResource> resources = new Resources<>(
				pessoasAlteradas.stream().map(PessoaResource::new).collect(Collectors.toList()));

		return ResponseEntity.ok(resources);
	}

}
