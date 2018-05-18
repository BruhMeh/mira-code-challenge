package com.miraeducation.springboot.api.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceSupport;

import com.miraeducation.springboot.api.controller.PessoaController;
import com.miraeducation.springboot.api.entities.Pessoa;


public class PessoaResource extends ResourceSupport {
	
	private final Pessoa pessoa;
	
	public PessoaResource(final Pessoa pessoa) {
		this.pessoa = pessoa;

        add(linkTo(PessoaController.class).withRel("pessoas"));
        add(linkTo(methodOn(PessoaController.class).getPessoa(pessoa.getId())).withSelfRel());

	}

	public Pessoa getPessoa() {
		return pessoa;
	}

}
