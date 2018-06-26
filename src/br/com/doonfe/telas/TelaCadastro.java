package br.com.doonfe.telas;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import br.com.doonfe.componentes.FormularioItem;
import br.com.doonfe.componentes.FormularioNotaFiscal;
import br.com.doonfe.componentes.FormularioPessoa;
import br.com.doonfe.componentes.MenuBar;
import br.com.doonfe.modelo.Itens;
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
		
		JPanel painelBotoes = botoesCadastro();
		
		JPanel pai = new JPanel();
		pai.add(formularioNF);
		pai.add(painelBotoes);
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

	private JPanel botoesCadastro() {
		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton btnSalvar = new JButton("Salvar");
		JButton btnCancelar = new JButton("Cancelar");
		painelBotoes.add(btnSalvar);
		painelBotoes.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int dialogResult = JOptionPane.showConfirmDialog(null,"Você tem certeza que deseja cancelar?");
				if(dialogResult == JOptionPane.YES_OPTION) {
					// ir para tela principal
				}
			}
		});
		
		return painelBotoes;
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