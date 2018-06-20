package br.com.doonfe.teste;

import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.doonfe.modelo.*;
import br.com.doonfe.util.JPAUtil;

public class TesteNotaFiscal {

	/*public static void main(String[] args) {
		
		Itens item1 = new Itens();
		item1.setCodigo(1789);
		item1.setDescricao("Biscoito");
		item1.setQuantidade(5);
		item1.setValor(50.0);
		
		NotaFiscal nf = new NotaFiscal();
		nf.setDataEmissao(Calendar.getInstance());
		nf.setDataOperacao(Calendar.getInstance());
		nf.setInformacoesComplementares("Nennhuma informação");
		nf.setModelo(ModeloNF.MODELO1_A);
		nf.setNatureza(NaturezaNF.VENDA);
		nf.setNumeroNota(201814);
		nf.setItens(Arrays.asList(item1));
		
		PessoaFisica pessoa1 = new PessoaFisica();
		pessoa1.setCpf("111.1111.1111-30");
		pessoa1.setEstado("MG");
		pessoa1.setNome("Ramon Pires");
		
		PessoaJuridica pessoa2 = new PessoaJuridica();
		pessoa2.setCnpj("1256.45597.1445");
		pessoa2.setEstado("SP");
		pessoa2.setRazaoSocial("Não sei o que é isso");
		
		nf.setDestinatario(pessoa1);
		nf.setEmitente(pessoa2);
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(item1);
		em.persist(pessoa1);
		em.persist(pessoa2);
		em.persist(nf);
		
		
		em.getTransaction().commit();
		em.close();

	}*/

}