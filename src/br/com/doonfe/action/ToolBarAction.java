package br.com.doonfe.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ToolBarAction {
	
	public ActionListener excluirLinha(JTable tabela) {
		
		ActionListener ExcluirLinha = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int linha = tabela.getSelectedRow();
					if(linha != -1)
						((DefaultTableModel) tabela.getModel()).removeRow(linha);
				} catch(ArrayIndexOutOfBoundsException e) {
					System.out.println(e);
				}
			}
		};
		
		return ExcluirLinha;
	}
	
}
