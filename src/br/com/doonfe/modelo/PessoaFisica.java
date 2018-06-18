package br.com.doonfe.modelo;

import javax.persistence.Entity;

@Entity
public class PessoaFisica extends Pessoa {
	
	private String cpf;
	private String nome;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
