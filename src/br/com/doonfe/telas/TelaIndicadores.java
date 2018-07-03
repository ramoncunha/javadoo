package br.com.doonfe.telas;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import br.com.doonfe.componentes.MenuBar;

public class TelaIndicadores {
	
	public void render() {
		
		MenuBar menubar = new MenuBar();
		
		JTable table = new JTable();
		
		JScrollPane scrollTabela = new JScrollPane();
		scrollTabela.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollTabela.setViewportView(table);
		
		Object[] colunas = new String[]{"Indicador", "Valor"};
		Object[][] dados = new Object[][]{
			{"Total de Notas Fiscais cadastradas", ""},
			{"Média de valor das notas",""},
			{"Média de valor dos itens das notas",""},
			{"Maior valor de nota",""},
			{"Estado com maior número de notas emitidas", ""},
			{"Estado com maior número de notas como destinatário",""},
			{"empresa que é a maior compradora em volume de vendas",""},
			{"empresa que é a maior vendedora em volume de vendas",""},
			{"Total de notas com valor superior a 10 mil",""},
			{"Total de notas com mais de 10 itens",""}
			};
		
		DefaultTableModel model = new DefaultTableModel(dados, colunas);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model);
		
		JFrame frame = new JFrame("Tela Indicadores");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menubar.build());
		frame.add(scrollTabela);
		frame.setSize(800, 600);
		frame.setVisible(true);		
	}
	
}
