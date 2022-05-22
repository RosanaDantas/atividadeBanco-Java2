package org.serratec.backend2.trabalho.banco.controller;

import org.serratec.backend2.trabalho.banco.exceptions.InsufficientFundsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {


	@ExceptionHandler(InsufficientFundsException.class)
	public ResponseEntity<String> tratarInsuficientFundsExceptions(InsufficientFundsException exception) {
		String msg = "O saldo da conta é insuficiente para esta operação";
		return ResponseEntity.badRequest().body(msg);
	}
}