package com.miraeducation.springboot.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PessoaNotFoundException extends RuntimeException {

		public PessoaNotFoundException(Long userId) {
			super("Não foi possivel encontrar pessoa com id '" + userId + "'.");
		}

}
