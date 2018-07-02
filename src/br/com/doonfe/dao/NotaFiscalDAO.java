package br.com.doonfe.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.doonfe.modelo.NotaFiscal;
import br.com.doonfe.modelo.PessoaFisica;
import br.com.doonfe.modelo.PessoaJuridica;
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
	
	public PessoaFisica getPessoaFisica(Integer id) {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		String jpql = "select p from PessoaFisica p where id = "+id;
		
		TypedQuery<PessoaFisica> query = em.createQuery(jpql, PessoaFisica.class);
		
		PessoaFisica registro = query.getSingleResult(); 
		
		em.getTransaction().commit();
		em.close();
		
		return registro;
	}
	
	public PessoaJuridica getPessoaJuridica(Integer id) {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		String jpql = "select p from PessoaJuridica p where id = "+id;
		
		TypedQuery<PessoaJuridica> query = em.createQuery(jpql, PessoaJuridica.class);
		
		PessoaJuridica registro = query.getSingleResult(); 
		
		em.getTransaction().commit();
		em.close();
		
		return registro;
	}
	
}
