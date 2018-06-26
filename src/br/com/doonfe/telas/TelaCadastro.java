package br.com.doonfe.telas;

import java.awt.FlowLayout;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import br.com.doonfe.componentes.MenuBar;
import br.com.doonfe.modelo.Itens;
import br.com.doonfe.util.JPAUtil;

public class TelaCadastro {

	public static void main(String[] args) {
		
		/* Campos Formulário Nota Fiscal */
		JTextField campoNNota = new JTextField(10);
		JTextField campoModelo = new JTextField(10);
		campoModelo.setText("Modelo 1-A");
		campoModelo.setEditable(false);
		JTextField campoNatureza = new JTextField(10);
		campoNatureza.setText("Venda");
		campoNatureza.setEditable(false);
		JTextField campoDtOperacao = new JTextField(10);
		JTextField campoDtEmissao = new JTextField(10);
		
		/* Campos Formulário Pessoa */
		JTextField campoDocumento1 = new JTextField(15);
		JTextField campoNome1 = new JTextField(15);
		JTextField campoInscricao1 = new JTextField(15);
		JTextField campoEstado1 = new JTextField(5);
		
		JTextField campoDocumento2 = new JTextField(15);
		JTextField campoNome2 = new JTextField(15);
		JTextField campoInscricao2 = new JTextField(15);
		JTextField campoEstado2 = new JTextField(5);
		
		/* Campos formulário Itens */
		JTextField campoCodigo = new JTextField(15);
		JTextField campoDescricao = new JTextField(15);
		JTextField campoPreco = new JTextField(15);
		JTextField campoQtd = new JTextField(15);
		JButton salvarItem = new JButton("Inserir");		
		
		/* JPane Formulário Nota Fiscal */
		JPanel formularioNF = new JPanel();
		formularioNF.setLayout(new BoxLayout(formularioNF, BoxLayout.Y_AXIS));
		
		JPanel camposNota = new JPanel();
		camposNota.setLayout(new BoxLayout(camposNota, BoxLayout.Y_AXIS));
		camposNota.add(new JLabel("N Nota"));
		camposNota.add(campoNNota);
		camposNota.add(new JLabel("Modelo"));
		camposNota.add(campoModelo);
		camposNota.add(new JLabel("Natureza"));
		camposNota.add(campoNatureza);
		camposNota.add(new JLabel("Data da Operação"));
		camposNota.add(campoDtOperacao);
		camposNota.add(new JLabel("Data da Emissão"));
		camposNota.add(campoDtEmissao);
		formularioNF.add(camposNota);
		
		/* JPane Pessoa Emitente */
		JPanel emitentePanel = new JPanel();
		emitentePanel.setBorder(BorderFactory.createTitledBorder("EMITENTE"));
		emitentePanel.setLayout(new BoxLayout(emitentePanel, BoxLayout.Y_AXIS));
		emitentePanel.add(new JLabel("CNPJ/CPF"));
		emitentePanel.add(campoDocumento1);
		emitentePanel.add(new JLabel("Razão Social/Nome"));
		emitentePanel.add(campoNome1);
		emitentePanel.add(new JLabel("Inscrição Estadual"));
		emitentePanel.add(campoInscricao1);
		emitentePanel.add(new JLabel("Estado"));
		emitentePanel.add(campoEstado1);
		
		/* JPane Pessoa Destinatario */
		JPanel destinatarioPanel = new JPanel();
		destinatarioPanel.setBorder(BorderFactory.createTitledBorder("DESTINATÁRIO"));
		destinatarioPanel.setLayout(new BoxLayout(destinatarioPanel, BoxLayout.Y_AXIS));
		destinatarioPanel.add(new JLabel("CNPJ/CPF"));
		destinatarioPanel.add(campoDocumento2);
		destinatarioPanel.add(new JLabel("Razão Social/Nome"));
		destinatarioPanel.add(campoNome2);
		destinatarioPanel.add(new JLabel("Inscrição Estadual"));
		destinatarioPanel.add(campoInscricao2);
		destinatarioPanel.add(new JLabel("Estado"));
		destinatarioPanel.add(campoEstado2);
		
		JPanel paiPanelPessoa = new JPanel();
		paiPanelPessoa.setLayout(new BoxLayout(paiPanelPessoa, BoxLayout.X_AXIS));
		paiPanelPessoa.add(emitentePanel);
		paiPanelPessoa.add(destinatarioPanel);
		formularioNF.add(paiPanelPessoa);
		
		/* JPanel Item */
		JPanel camposItem = new JPanel();
		camposItem.setLayout(new BoxLayout(camposItem, BoxLayout.Y_AXIS));
		camposItem.setBorder(BorderFactory.createTitledBorder("ITEM"));
		camposItem.add(new JLabel("Código"));
		camposItem.add(campoCodigo);
		camposItem.add(new JLabel("Descrição"));
		camposItem.add(campoDescricao);
		camposItem.add(new JLabel("Preço"));
		camposItem.add(campoPreco);
		camposItem.add(new JLabel("Quantidade"));
		camposItem.add(campoQtd);
		camposItem.add(salvarItem);		
		formularioNF.add(camposItem);
		
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
		formularioNF.add(jScrollPane);
		/* FIM LISTAGEM ITENS  */
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton btnSalvar = new JButton("Salvar");
		JButton btnCancelar = new JButton("Cancelar");
		painelBotoes.add(btnSalvar);
		painelBotoes.add(btnCancelar);
		
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
}