package br.com.doonfe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.doonfe.modelo.NotaFiscal;
import br.com.doonfe.util.JPAUtil;

public class IndicadoresDAO {
	
	public List<NotaFiscal> getNotasFiscais() {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		String jpql = "select nf from NotaFiscal nf";
		TypedQuery<NotaFiscal> query = em.createQuery(jpql, NotaFiscal.class);
		
		List<NotaFiscal> notaFiscal = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return notaFiscal;
	}
	
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
	
	public String estadoEmiteNf() {
		
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		
		String jpql = "select max(p.estado) from Pessoa p, NotaFiscal nf where nf.emitente = p.id group by p.estado";
		Query query = em.createQuery(jpql);
		
		String estado = (String) query.setMaxResults(1).getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		
		return estado;
	}
	
	public String estadoDestinoNf() {
		
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		
		String jpql = "select max(p.estado) from Pessoa p, NotaFiscal nf where nf.destinatario = p.id group by p.estado";
		Query query = em.createQuery(jpql);
		
		String estado = (String) query.setMaxResults(1).getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		
		return estado;
	}
	
	public String destinatarioComprador() {
		
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		
		String jpql = "select max(pj.razaoSocial) from PessoaJuridica pj, NotaFiscal nf, Itens i "
				+ "where nf.destinatario = pj.id and nf.id = i.notaFiscal group by pj.id";
		Query query = em.createQuery(jpql);
		
		String estado = (String) query.setMaxResults(1).getSingleResult();
		
		em.getTransaction().commit();
		em.close();		
		
		return estado;
	}
	
	public String emitenteComprador() {
		
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		
		String jpql = "select max(pj.razaoSocial) from PessoaJuridica pj, NotaFiscal nf, Itens i "
				+ "where nf.emitente = pj.id and nf.id = i.notaFiscal group by pj.id";
		Query query = em.createQuery(jpql);
		
		String estado = (String) query.setMaxResults(1).getSingleResult();
		
		em.getTransaction().commit();
		em.close();		
		
		return estado;
	}
	
	public Integer notasSuperiorDezMil() {
		
		Integer supeiorDezMil = 0;
		List<NotaFiscal> nf = getNotasFiscais();
		
		for (NotaFiscal notaFiscal : nf) {
			if(notaFiscal.getValorItens() > 10000d) {
				supeiorDezMil++;
			}
		}
		
		return supeiorDezMil;
	}
	
	public Integer notasMaiorDez() {
		
		Integer qtdItem = 0;
		List<NotaFiscal> nf = getNotasFiscais();
		
		for (NotaFiscal notaFiscal : nf) {
			if(notaFiscal.getQtdItem() > 10) {
				qtdItem++;
			}
		}
		
		return qtdItem;
	}
}
