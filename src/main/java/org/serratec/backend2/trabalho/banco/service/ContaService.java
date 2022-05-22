package org.serratec.backend2.trabalho.banco.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.serratec.backend2.trabalho.banco.domain.Conta;
import org.serratec.backend2.trabalho.banco.domain.Operacao;
import org.serratec.backend2.trabalho.banco.domain.TipoOperacao;
import org.serratec.backend2.trabalho.banco.exceptions.InsufficientFundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

	@Autowired
	private OperacaoService operacaoService;

	private List<Conta> banco;
	private int nextnumero;

	public ContaService() {
		banco = new ArrayList<>();
		banco.add(new Conta(1, "Rosana Dantas", 15000.00));
		banco.add(new Conta(2, "Raissa da Silva", 10000.00));
		banco.add(new Conta(3, "Gal Gadot", 5000.00));
		banco.add(new Conta(4, "Aline Moraes", 200.00));
		nextnumero = 5;
	}

	public List<Conta> listaConta() {
		return banco;
	}

	public Conta recuperarPorNumero(Integer numero) {
		Conta contaRecuperada = new Conta();
		for (Conta conta : banco) {
			if (numero.equals(conta.getNumero())) {
				contaRecuperada = conta;
			}
		}
		return contaRecuperada;
	}

	public Conta adicionar(Conta conta) {
		conta.setNumero(nextnumero);
		banco.add(conta);
		nextnumero++;
		return conta;

	}

	public Conta atualizarConta(Conta conta, Integer numero) {
		Conta contaAtualizada = recuperarPorNumero(numero);
		if (null != conta.getNumero() && conta.getNumero() > 0) {
			contaAtualizada.setNumero(conta.getNumero());
		}

		if (null != conta.getTitular() && !"".equals(conta.getTitular())) {
			contaAtualizada.setTitular(conta.getTitular());
		}

		return contaAtualizada;
	}

	public void apagarConta(Integer numero) {
		Conta conta = recuperarPorNumero(numero);
		banco.remove(conta);
	}

	public List<?> operacao(String operacao, Double valor, Integer numero) throws InsufficientFundsException {

		Conta conta = recuperarPorNumero(numero);
		Operacao operacaoClass = new Operacao();
		switch (operacao) {
		case "debito":
			operacaoClass.setTipo(TipoOperacao.valueOf(operacao.toUpperCase()));
			operacaoClass.setValor(valor);
			return operacaoService.debito(conta, operacaoClass);
		case "credito":
			operacaoClass.setTipo(TipoOperacao.valueOf(operacao.toUpperCase()));
			operacaoClass.setValor(valor);
			return operacaoService.credito(conta, operacaoClass);
		default:
			return Collections.emptyList();
		}
	}
}
