package br.com.doonfe.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.doonfe.dao.NotaFiscalDAO;
import br.com.doonfe.telas.TelaCadastro;

public class ToolBarAction {
	
	public ActionListener excluirLinha(JTable tabela) {
		
		ActionListener ExcluirLinha = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int linha = tabela.getSelectedRow();
					NotaFiscalDAO removerNf = new NotaFiscalDAO();
					if(linha != -1) {
						int valorId = (int) tabela.getModel().getValueAt(linha, 0);
						removerNf.removerNotaFiscal(valorId);
						((DefaultTableModel) tabela.getModel()).removeRow(linha);
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					System.out.println(e);
				}
			}
		};
		
		return ExcluirLinha;
	}
	
	public ActionListener editarLinha(JTable tabela) {
		
		ActionListener editarLinha = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int linha = tabela.getSelectedRow();
					if(linha != -1) {
						/* Seleciona o id do registro */
						int valorId = (int) tabela.getModel().getValueAt(linha, 0);
						/* Exibe a nova tela de cadastro com o id da nf */
						TelaCadastro TelaCadastro = new TelaCadastro();
						TelaCadastro.setEditarNf(valorId);
						TelaCadastro.render();
						
					}
				} catch(ArrayIndexOutOfBoundsException er) {
					System.out.println(er);
				}				
			}
		};
		return editarLinha;
	}
	
}
