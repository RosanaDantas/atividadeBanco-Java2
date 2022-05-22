package org.serratec.backend2.trabalho.banco.controller;

import org.serratec.backend2.trabalho.banco.domain.Conta;
import org.serratec.backend2.trabalho.banco.exceptions.InsufficientFundsException;
import org.serratec.backend2.trabalho.banco.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/conta" })
public class ContaController {

	@Autowired
	private ContaService contaService;

	@GetMapping("/listar")
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok(contaService.listaConta());
	}

	@GetMapping("/{numero}")
	public ResponseEntity<?> pegaPorNumero(@PathVariable Integer numero) {
		return ResponseEntity.ok(contaService.recuperarPorNumero(numero));
	}

	@PostMapping("/adicionar")
	public ResponseEntity<?> adcionarConta(@RequestBody Conta conta) {
		return ResponseEntity.status(HttpStatus.CREATED).body(contaService.adicionar(conta));
	}

	@PutMapping("/atualizar/{numero}")
	public ResponseEntity<?> atualizarConta(@PathVariable Integer numero, @RequestBody Conta conta) {
		Conta contaAtualizada = contaService.atualizarConta(conta, numero);
		return ResponseEntity.ok(contaAtualizada);
	}

	@DeleteMapping("/excluir/{numero}")
	public ResponseEntity<?> apagarConta(@PathVariable Integer numero) {
		contaService.apagarConta(numero);
		return ResponseEntity.ok("Conta excluída com sucesso!!");
	}

	@PostMapping("/operacao/{numero}/{operacao}/{valor}")
	public ResponseEntity<?> operacao(@PathVariable("numero") Integer numero, @PathVariable("operacao") String operacao,
			@PathVariable("valor") Double valor) throws InsufficientFundsException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(contaService.operacao(operacao, valor, numero));
	}

}
