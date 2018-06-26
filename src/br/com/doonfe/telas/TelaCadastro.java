package br.com.doonfe.telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import br.com.doonfe.componentes.BotaoCadastro;
import br.com.doonfe.componentes.FormularioItem;
import br.com.doonfe.componentes.FormularioNotaFiscal;
import br.com.doonfe.componentes.FormularioPessoa;
import br.com.doonfe.componentes.MenuBar;
import br.com.doonfe.dao.NotaFiscalDAO;
import br.com.doonfe.modelo.Itens;
import br.com.doonfe.modelo.ModeloNF;
import br.com.doonfe.modelo.NaturezaNF;
import br.com.doonfe.modelo.NotaFiscal;
import br.com.doonfe.modelo.PessoaFisica;
import br.com.doonfe.modelo.PessoaJuridica;
import br.com.doonfe.util.JPAUtil;

public class TelaCadastro {

	public void render() {
		
		JPanel formularioNF = new JPanel();
		formularioNF.setLayout(new BoxLayout(formularioNF, BoxLayout.Y_AXIS));
		
		FormularioNotaFiscal camposNota = new FormularioNotaFiscal();
		formularioNF.add(camposNota.buildFormularioNF());
		
		FormularioPessoa camposPessoa = new FormularioPessoa();
		formularioNF.add(camposPessoa.buildFormularioPessoa());
		
		FormularioItem camposItem = new FormularioItem();
		formularioNF.add(camposItem.buildFormularioItem());
		
		JScrollPane jScrollPane = listarItensNF();
		formularioNF.add(jScrollPane);
		
		ActionListener cancelarCadastro = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int dialogResult = JOptionPane.showConfirmDialog(null,"Você tem certeza que deseja cancelar?");
				if(dialogResult == JOptionPane.YES_OPTION) {
					// ir para tela principal
				}
			}
		};
		
		ActionListener salvarCadastro = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					NotaFiscalDAO persistNF = new NotaFiscalDAO();
					NotaFiscal nf = new NotaFiscal();
					nf.setNumeroNota(Integer.parseInt(camposNota.getCampoNNota().getText()));
					nf.setNatureza(NaturezaNF.VENDA);
					nf.setModelo(ModeloNF.MODELO1_A);
					nf.setDataOperacao(Calendar.getInstance());
					nf.setDataEmissao(Calendar.getInstance());
					nf.setInformacoesComplementares(camposNota.getCampoInformacoesComplementares().getText());
					
					if(camposPessoa.getCampoInscricao1().getText().equals("")) {
						PessoaFisica pessoaEmitente1 = new PessoaFisica();
						pessoaEmitente1.setNome(camposPessoa.getCampoNome1().getText());
						pessoaEmitente1.setCpf(camposPessoa.getCampoDocumento1().getText());
						pessoaEmitente1.setEstado(camposPessoa.getCampoEstado1().getText());
						nf.setEmitente(pessoaEmitente1);
						persistNF.salvarPessoa(pessoaEmitente1);
					} else {
						PessoaJuridica pessoaEmitente2 = new PessoaJuridica();
						pessoaEmitente2.setCnpj(camposPessoa.getCampoDocumento1().getText());
						pessoaEmitente2.setInscricaoEstadual(camposPessoa.getCampoInscricao1().getText());
						pessoaEmitente2.setRazaoSocial(camposPessoa.getCampoNome1().getText());
						pessoaEmitente2.setEstado(camposPessoa.getCampoEstado1().getText());
						nf.setEmitente(pessoaEmitente2);
						persistNF.salvarPessoa(pessoaEmitente2);
					}
					
					if(camposPessoa.getCampoInscricao2().getText().equals("")) {
						PessoaFisica pessoaDestinatario1 = new PessoaFisica();
						pessoaDestinatario1.setNome(camposPessoa.getCampoNome2().getText());
						pessoaDestinatario1.setCpf(camposPessoa.getCampoDocumento2().getText());
						pessoaDestinatario1.setEstado(camposPessoa.getCampoEstado2().getText());
						nf.setDestinatario(pessoaDestinatario1);
						persistNF.salvarPessoa(pessoaDestinatario1);
					} else {
						PessoaJuridica pessoaDestinatario2 = new PessoaJuridica();
						pessoaDestinatario2.setCnpj(camposPessoa.getCampoDocumento2().getText());
						pessoaDestinatario2.setInscricaoEstadual(camposPessoa.getCampoInscricao2().getText());
						pessoaDestinatario2.setRazaoSocial(camposPessoa.getCampoNome2().getText());
						pessoaDestinatario2.setEstado(camposPessoa.getCampoEstado2().getText());
						nf.setDestinatario(pessoaDestinatario2);
						persistNF.salvarPessoa(pessoaDestinatario2);
					}
					
					Itens item1 = new Itens();
					item1.setCodigo(1789);
					item1.setDescricao("Biscoito");
					item1.setQuantidade(5);
					item1.setValor(50.0);
					nf.setItens(Arrays.asList(item1));
					persistNF.salvarItens(item1);
					
					persistNF.salvarNotaFiscal(nf);
					
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		};
		
		BotaoCadastro painelBotoes = new BotaoCadastro();
		painelBotoes.setCancelAction(cancelarCadastro);
		painelBotoes.setSaveAction(salvarCadastro);
		
		JPanel pai = new JPanel();
		pai.add(formularioNF);
		pai.add(painelBotoes.buildBotaoFormulario());
		pai.setLayout(new BoxLayout(pai, BoxLayout.Y_AXIS));
		JScrollPane jScrollPanePai = new JScrollPane();
		jScrollPanePai.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPanePai.setViewportView(pai);
		
		MenuBar menubar = new MenuBar();
		
		JFrame janela = new JFrame();
		janela.setTitle("Cadastro");
		janela.setSize(800, 600);
		janela.add(jScrollPanePai);
		janela.setJMenuBar(menubar.build());
		janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janela.setVisible(true);
	}

	private JScrollPane listarItensNF() {
		/* Lista de Itens */
		Object[] colunas = new String[]{"Código", "Descrição", "Preço", "Qtd", "Total"};
		Object[][] dados = new Object[][]{};
		
		DefaultTableModel model = new DefaultTableModel(dados, colunas);
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		String jpql = "select m from Itens m";
		TypedQuery<Itens> query = em.createQuery(jpql, Itens.class);
		
		List<Itens> resultados = query.getResultList();
		
		for (Itens item : resultados) {
			model.addRow(new Object[] {
					item.getCodigo(),
					item.getDescricao(),
					item.getValor(),
					item.getQuantidade()
			});
		}
		
		em.getTransaction().commit();
		em.close();
		
		JTable table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model);
		
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setViewportView(table);
		/* FIM LISTAGEM ITENS  */
		return jScrollPane;
	}
}
