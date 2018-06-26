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
	
}
