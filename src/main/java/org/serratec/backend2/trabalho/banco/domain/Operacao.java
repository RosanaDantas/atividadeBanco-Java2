package org.serratec.backend2.trabalho.banco.domain;

public class Operacao {

	private TipoOperacao tipo;
	private Double valor;

	public Operacao() {
	}

	public Operacao(TipoOperacao tipo, Double valor) {
		this.tipo = tipo;
		this.valor = valor;
	}

	public TipoOperacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoOperacao tipo) {
		this.tipo = tipo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}
