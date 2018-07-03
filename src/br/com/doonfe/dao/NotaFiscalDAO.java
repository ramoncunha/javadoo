package br.com.doonfe.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.doonfe.modelo.NotaFiscal;
import br.com.doonfe.util.JPAUtil;

public class NotaFiscalDAO {

	public void salvarNotaFiscal(NotaFiscal notaFiscal){
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(notaFiscal);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	public NotaFiscal editarNotaFiscal(Integer id) {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		String jpql = "select nf from NotaFiscal nf join fetch nf.itens where nf.id = " + id;
		Query query = em.createQuery(jpql);
		NotaFiscal registroNf = (NotaFiscal) query.getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		
		return registroNf;
	}
	
}
