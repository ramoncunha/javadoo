package br.com.doonfe.telas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import br.com.doonfe.componentes.MenuBar;
import br.com.doonfe.componentes.ToolBar;
import br.com.doonfe.modelo.NotaFiscal;
import br.com.doonfe.util.JPAUtil;

public class TelaPrincipal {
	
	public void render(){
		
		JTable table = buildTabela();
		
		ActionListener ExcluirLinha = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int linha = table.getSelectedRow();
					if(linha != -1)
						((DefaultTableModel) table.getModel()).removeRow(linha);
				} catch(ArrayIndexOutOfBoundsException e) {
					System.out.println(e);
				}
			}
		};
		
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setViewportView(table);
		
		ToolBar toolbar = new ToolBar();
		toolbar.setRemoveAction(ExcluirLinha);
		MenuBar menubar = new MenuBar();
		
		JFrame frame = new JFrame("Tela Inicial");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(toolbar.build(), BorderLayout.PAGE_START);
		frame.setJMenuBar(menubar.build());
		frame.add(jScrollPane);
		frame.setSize(800, 600);
		frame.setVisible(true);
		
	}

	private JTable buildTabela() {
		
		Object[] colunas = new String[]{"N Nota", "Modelo", "Natureza", "Dt Emissão", "Destinatario", "Emitente"};
		Object[][] dados = new Object[][]{};
		
		DefaultTableModel model = new DefaultTableModel(dados, colunas);
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		String jpql = "select m from NotaFiscal m";
		
		TypedQuery<NotaFiscal> query = em.createQuery(jpql, NotaFiscal.class);
		
		List<NotaFiscal> resultados = query.getResultList();
		
		for (NotaFiscal notaFiscal : resultados) {
			model.addRow(new Object[] {
					notaFiscal.getNumeroNota(),
					notaFiscal.getModelo(),
					notaFiscal.getNatureza(),
					notaFiscal.getDataEmissao(),
					notaFiscal.getDestinatario(),
					notaFiscal.getEmitente()
			});
		}
		
		em.getTransaction().commit();
		em.close();
		
		JTable table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model);
		
		return table;
	}

}
