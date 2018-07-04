package br.com.doonfe.dao;

import javax.persistence.EntityManager;

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
		
		NotaFiscal registroNf = em.find(NotaFiscal.class, id);
		
		em.getTransaction().commit();
		em.close();
		
		return registroNf;
	}
	
	public NotaFiscal alterarNotaFiscal(NotaFiscal notaFiscal) {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		em.merge(notaFiscal);
		
		em.getTransaction().commit();
		em.close();
		
		return notaFiscal;
	}
	
	public void removerNotaFiscal(Integer id) {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		NotaFiscal nfRemovida = em.find(NotaFiscal.class, id);
		
		em.remove(nfRemovida);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
}
