package com.miraeducation.springboot.api.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.miraeducation.springboot.api.entities.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long>{
	
	Collection<Pessoa> findAll();
	
	//Realiza a busca: ((nome AND sobrenome) OR cpf)
	Collection<Pessoa> findByNomeAndSobrenomeAllIgnoreCaseOrCpf(String nome, String sobrenome, String cpf);

}
