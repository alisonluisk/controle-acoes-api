package com.ibolsa.api.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AcessoNegadoException extends AuthenticationException {
	
	private static final long serialVersionUID = 1L;
	
	public AcessoNegadoException(String msg) {
		super(msg);
	}
	
	public AcessoNegadoException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
