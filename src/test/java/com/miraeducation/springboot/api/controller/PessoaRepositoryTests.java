package com.miraeducation.springboot.api.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.miraeducation.springboot.api.entities.Pessoa;
import com.miraeducation.springboot.api.repository.PessoaRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PessoaRepositoryTests {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Test
	public void whenFindAll_thenReturnPessoas() {
		//given
		//TODO Inicializar variaveis que não sejam as do data.sql para testes mais efetivos.
	
		//when
		List<Pessoa> pessoas = pessoaRepository.findAll();
		
		//then
	    assertEquals(pessoas.size(), 2);

	}
	
	@Test
	public void whenFindByNomeAndSobrenome_thenReturnPessoa() {
		//given
		//TODO Inicializar variaveis que não sejam as do data.sql para testes mais efetivos.
	
		//when
		Pessoa pessoa = pessoaRepository.findByNomeAndSobrenome("Bruno", "Delgado");
		
		//then
	    assertEquals(pessoa.getCpf(), "42840053837");

	}


}
