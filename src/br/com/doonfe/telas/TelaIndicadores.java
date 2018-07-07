package br.com.doonfe.telas;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import br.com.doonfe.componentes.MenuBar;
import br.com.doonfe.dao.IndicadoresDAO;

public class TelaIndicadores {
	
	public void render() {
		
		MenuBar menubar = new MenuBar();
		
		IndicadoresDAO consultaValor = new IndicadoresDAO(); 
		
		JTable table = new JTable();
		
		JScrollPane scrollTabela = new JScrollPane();
		scrollTabela.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollTabela.setViewportView(table);
		
		Object[] colunas = new String[]{"Indicador", "Valor"};
		Object[][] dados = new Object[][]{
			{"Total de Notas Fiscais cadastradas", consultaValor.totalNotasFicaisCadastradas()},
			{"Média de valor das notas", consultaValor.mediaValorNotas()},
			{"Média de valor dos itens das notas",consultaValor.mediaValorItens()},
			{"Maior valor de nota", consultaValor.maiorValorNota()},
			{"Estado com maior número de notas emitidas", consultaValor.estadoEmiteNf()},
			{"Estado com maior número de notas como destinatário",consultaValor.estadoDestinoNf()},
			{"Empresa que é a maior compradora em volume de vendas",consultaValor.destinatarioComprador()},
			{"Empresa que é a maior vendedora em volume de vendas",""},
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
