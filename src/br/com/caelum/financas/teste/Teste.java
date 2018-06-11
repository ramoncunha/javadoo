package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class Teste {

	public static void main(String[] args) {
		
		/* Criar conta
		 * Conta conta = new Conta();
		conta.setTitular("Leonardo");
		conta.setBanco("Caixa Economica");
		conta.setAgencia("123");
		conta.setNumero("456");
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		em.persist(conta);
		em.getTransaction().commit();
		
		em.close();*/
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Conta conta = em.find(Conta.class, 1);
		
		conta.setTitular("Ramon Pires");
		
		System.out.println(conta);
		
		em.getTransaction().commit();

	}

}
