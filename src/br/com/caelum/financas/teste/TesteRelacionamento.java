package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteRelacionamento {

	public static void main(String[] args) {
		
		Conta conta = new Conta();
		conta.setId(1);		
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setTipo(TipoMovimentacao.SAIDA);
		movimentacao.setData(Calendar.getInstance());
		movimentacao.setDescricao("Churrascaria");
		movimentacao.setValor(new BigDecimal("200.0"));
		movimentacao.setConta(conta);
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		em.persist(movimentacao);
		
		em.getTransaction().commit();
		em.close();
	}

}
