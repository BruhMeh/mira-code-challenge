package com.miraeducation.springboot.api.controller;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Collection;

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
		Pessoa pessoaTeste = new Pessoa();
		Pessoa pessoaTeste2 = new Pessoa();
		
		pessoaTeste.setNome("Bruno");
		pessoaTeste.setSobrenome("Delgado");
		pessoaTeste.setDataNascimento(LocalDate.of(1994, 12, 7));
		pessoaTeste = pessoaRepository.save(pessoaTeste);
		
		pessoaTeste2.setNome("Marcos");
		pessoaTeste2.setSobrenome("Delgado");
		pessoaTeste2.setDataNascimento(LocalDate.of(1968, 3, 5));
		pessoaTeste2 = pessoaRepository.save(pessoaTeste2);
	
		//when
		Collection<Pessoa> pessoas = pessoaRepository.findAll();
		
		//then
	    assertEquals(2, pessoas.size());
	    assertTrue(pessoas.contains(pessoaTeste));

	}
	
	@Test
	public void whenFindByNomeAndSobrenomeOfCpf_thenReturnPessoa() {
		//given
		Pessoa pessoaTeste = new Pessoa();
		Pessoa pessoaTeste2 = new Pessoa();
		
		pessoaTeste.setNome("Bruno");
		pessoaTeste.setSobrenome("Delgado");
		pessoaTeste.setCpf("42840053837");
		pessoaTeste.setDataNascimento(LocalDate.of(1994, 12, 7));
		pessoaTeste = pessoaRepository.save(pessoaTeste);
		
		pessoaTeste2.setNome("Marcos");
		pessoaTeste2.setSobrenome("Delgado");
		pessoaTeste2.setCpf("11577503899");
		pessoaTeste2.setDataNascimento(LocalDate.of(1968, 3, 5));
		pessoaTeste2 = pessoaRepository.save(pessoaTeste2);
		
		//when
		Collection<Pessoa> retornoNomeSobrenome = pessoaRepository.findByNomeAndSobrenomeAllIgnoreCaseOrCpf("Bruno", "Delgado", null);
		Collection<Pessoa> retornoCpf = pessoaRepository.findByNomeAndSobrenomeAllIgnoreCaseOrCpf(null, null, "11577503899");
		
		//then
	    assertTrue(retornoNomeSobrenome.contains(pessoaTeste));
	    assertTrue(retornoCpf.contains(pessoaTeste2));

	}


}
