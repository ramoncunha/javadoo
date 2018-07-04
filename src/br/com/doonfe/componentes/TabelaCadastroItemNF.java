package br.com.doonfe.componentes;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import br.com.doonfe.modelo.Itens;

public class TabelaCadastroItemNF {
	
	private DefaultTableModel model;
	private JTable table = new JTable();

	public DefaultTableModel getModel() {
		return model;
	}
	public JTable getTable() {
		return table;
	}


	public JScrollPane buildTabela() {
		/* Lista de Itens */
		Object[] colunas = new String[]{"Código", "Descrição", "Preço", "Qtd", "Total"};
		Object[][] dados = new Object[][]{};
		
		this.model = new DefaultTableModel(dados, colunas);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model);
		
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setViewportView(table);
		/* FIM LISTAGEM ITENS  */
		return jScrollPane;
	}
	
	public void addItemNF(Itens i) {
		Integer codigo = i.getCodigo();
		String descricao = i.getDescricao();
		Double preco = i.getValor();
		Integer qtd = i.getQuantidade();
		Double total = preco * qtd;

		getModel().addRow(new Object[]{codigo, descricao, preco, qtd, total});
	}
	
}
