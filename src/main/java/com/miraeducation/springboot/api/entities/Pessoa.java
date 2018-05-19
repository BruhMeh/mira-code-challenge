package com.miraeducation.springboot.api.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity
@Table(name = "pessoas")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "pessoa_id")
	private long id;
	
	private String nome;
	
	private String sobrenome;
	
	private String cpf;
	
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dataNascimento;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private Set<Endereco> enderecos = new HashSet<>();
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private Set<Telefone> telefones = new HashSet<>();
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private Set<Email> emails = new HashSet<>();
	
	private boolean ativo;
	
	public Pessoa() { }; //JPA and tests use only
	
	public Pessoa(Pessoa pessoa) {
		this.nome = pessoa.getNome();
		this.sobrenome = pessoa.getSobrenome();
		this.cpf = pessoa.getCpf();
		this.dataNascimento = pessoa.getDataNascimento();
		pessoa.getEnderecos().forEach(n -> n.setPessoa(this));
		this.enderecos = pessoa.getEnderecos();
		pessoa.getTelefones().forEach(n -> n.setPessoa(this));
		this.telefones = pessoa.getTelefones();
		pessoa.getEmails().forEach(n -> n.setPessoa(this));
		this.emails = pessoa.getEmails();
		this.ativo = true;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
