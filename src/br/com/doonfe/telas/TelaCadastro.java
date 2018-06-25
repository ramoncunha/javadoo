package br.com.doonfe.telas;

import java.awt.FlowLayout;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
		
		/* JPane Formulário Nota Fiscal */
		JPanel formularioNF = new JPanel();
		formularioNF.setLayout(new BoxLayout(formularioNF, BoxLayout.Y_AXIS));
		formularioNF.add(new JLabel("N Nota"));
		formularioNF.add(campoNNota);
		formularioNF.add(new JLabel("Modelo"));
		formularioNF.add(campoModelo);
		formularioNF.add(new JLabel("Natureza"));
		formularioNF.add(campoNatureza);
		formularioNF.add(new JLabel("Data da Operação"));
		formularioNF.add(campoDtOperacao);
		formularioNF.add(new JLabel("Data da Emissão"));
		formularioNF.add(campoDtEmissao);
		
		/* JPane Formulário Pessoa */
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
		
		/* Lista de Itens */
		Object[] colunas = new String[]{"Código", "Descrição", "Preço", "Qtd", "Total"};
		Object[][] dados = new Object[][]{};
		
		DefaultTableModel model = new DefaultTableModel(dados, colunas);
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		String jpql = "select m from Itens m";
		Query query = em.createQuery(jpql);
		
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
		
		JFrame janela = new JFrame();
		janela.setTitle("Cadastro");
		janela.setSize(800, 600);
		janela.add(pai);
		janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janela.setVisible(true);
	}
}
