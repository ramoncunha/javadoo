package br.com.doonfe.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.doonfe.util.JPAUtil;

public class IndicadoresDAO {
	
	public Long totalNotasFicaisCadastradas() {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		String jpql = "select count(nf) from NotaFiscal nf";
		Query query = em.createQuery(jpql);
		
		Long totalResult = (Long) query.getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		
		return totalResult;		
	}
	
}
