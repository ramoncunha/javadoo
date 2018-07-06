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
	
	public Double mediaValorNotas() {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();

		String jpql = "select sum(i.preco*i.quantidade)/ count(i) from Itens i";
		Query query = em.createQuery(jpql);
		
		Double mediaValorNotas = (Double) query.getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		
		return mediaValorNotas;
	}
	
	public Double mediaValorItens() {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		String jpql = "select avg(i.preco) from Itens i";
		Query query = em.createQuery(jpql);
		
		Double media = (Double) query.getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		
		return media;
	}
	
	public Double maiorValorNota() {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		String jpql = "select i.preco*i.quantidade as total from Itens i group by i.notaFiscal order by total desc";
		Query query = em.createQuery(jpql);
		
		Double maiorValorNota = (Double) query.setMaxResults(1).getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		
		return maiorValorNota;
	}
	
}
