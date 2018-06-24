package br.com.doonfe.telas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.BoxLayout;
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
		JPanel formularioPessoa = new JPanel();
		formularioPessoa.setLayout(new BoxLayout(formularioPessoa, BoxLayout.Y_AXIS));
		formularioPessoa.add(new JLabel("EMITENTE"));
		formularioPessoa.add(new JLabel("CNPJ/CPF"));
		formularioPessoa.add(campoDocumento1);
		formularioPessoa.add(new JLabel("Razão Social/Nome"));
		formularioPessoa.add(campoNome1);
		formularioPessoa.add(new JLabel("Inscrição Estadual"));
		formularioPessoa.add(campoInscricao1);
		formularioPessoa.add(new JLabel("Estado"));
		formularioPessoa.add(campoEstado1);
		
		formularioPessoa.add(new JLabel("DESTINATARIO"));
		formularioPessoa.add(new JLabel("CNPJ/CPF"));
		formularioPessoa.add(campoDocumento2);
		formularioPessoa.add(new JLabel("Razão Social/Nome"));
		formularioPessoa.add(campoNome2);
		formularioPessoa.add(new JLabel("Inscrição Estadual"));
		formularioPessoa.add(campoInscricao2);
		formularioPessoa.add(new JLabel("Estado"));
		formularioPessoa.add(campoEstado2);
		
		/* Lista de Itens */
		JPanel listaItens = new JPanel();
		listaItens.setLayout(new BoxLayout(listaItens, BoxLayout.Y_AXIS));
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
		listaItens.add(jScrollPane);
		/* FIM LISTAGEM ITENS  */
		
		JPanel pai = new JPanel();
		pai.add(formularioNF);
		pai.add(formularioPessoa);
		pai.add(listaItens);
		pai.setLayout(new BoxLayout(pai, BoxLayout.X_AXIS));
		
		JFrame janela = new JFrame();
		janela.setTitle("Cadastro");
		janela.setSize(800, 600);
		janela.add(pai);
		janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janela.setVisible(true);
	}
}
