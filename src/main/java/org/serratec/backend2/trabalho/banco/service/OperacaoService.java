package org.serratec.backend2.trabalho.banco.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend2.trabalho.banco.domain.Conta;
import org.serratec.backend2.trabalho.banco.domain.Operacao;
import org.serratec.backend2.trabalho.banco.exceptions.InsufficientFundsException;
import org.springframework.stereotype.Service;

@Service
public class OperacaoService {

	List<Object> atualizacoes;

	public List<Object> debito(Conta conta, Operacao operacao) throws InsufficientFundsException {
		atualizacoes = new ArrayList<>();
		if (conta.getSaldo() < operacao.getValor()) {
			throw new InsufficientFundsException(conta.getSaldo(), operacao.getValor());
		}
		conta.setSaldo(conta.getSaldo() - operacao.getValor());
		atualizacoes.add(conta);
		atualizacoes.add(operacao);
		return atualizacoes;
	}

	public List<Object> credito(Conta conta, Operacao operacao) {
		atualizacoes = new ArrayList<>();

		conta.setSaldo(conta.getSaldo() + operacao.getValor());

		atualizacoes.add(conta);
		atualizacoes.add(operacao);
		return atualizacoes;
	}
}
