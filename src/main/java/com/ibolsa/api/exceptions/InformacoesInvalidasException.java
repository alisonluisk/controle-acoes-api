package com.ibolsa.api.exceptions;

public class InformacoesInvalidasException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InformacoesInvalidasException(String msg) {
		super(msg);
	}

	public InformacoesInvalidasException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
