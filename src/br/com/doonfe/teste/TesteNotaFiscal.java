package br.com.doonfe.teste;

import javax.persistence.EntityManager;

import br.com.doonfe.modelo.Itens;
import br.com.doonfe.util.JPAUtil;

public class TesteNotaFiscal {

	public static void main(String[] args) {
		
		Itens item1 = new Itens();
		item1.setCodigo(1789);
		item1.setDescricao("Biscoito");
		item1.setQuantidade(5);
		item1.setValor(50.0);
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(item1);
		
		
		em.getTransaction().commit();
		em.close();

	}

}