package com.miraeducation.springboot.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miraeducation.springboot.api.entities.Pessoa;

@Repository
public interface PessoaRespository extends JpaRepository<Pessoa, Long>{
	
	List<Pessoa> findAll();
	
	Pessoa findByNomeAndSobrenome(String nome, String sobrenome);

}
