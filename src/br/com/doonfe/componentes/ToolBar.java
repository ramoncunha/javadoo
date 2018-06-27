package br.com.doonfe.componentes;


import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;


public class ToolBar {
		
	private ActionListener removeAction;

	private ActionListener newAction;

	private ActionListener editAction;

	public void setRemoveAction(ActionListener removeAction) {
		this.removeAction = removeAction;
	}

	public void setNewAction(ActionListener newAction) {
		this.newAction = newAction;
	}

	public void setEditAction(ActionListener editAction) {
		this.editAction = editAction;
	}

	public JToolBar build(){
		JButton btnNovo = new JButton();
		btnNovo.setIcon(new ImageIcon(btnNovo.getClass().getResource("/images/novo.png")));		
		JButton btnEditar = new JButton();
		btnEditar.setIcon(new ImageIcon(btnEditar.getClass().getResource("/images/editar.png")));
		JButton btnExcluir = new JButton();
		btnExcluir.setIcon(new ImageIcon(btnExcluir.getClass().getResource("/images/excluir.png")));
		JButton btnDetalhes = new JButton();
		btnDetalhes.setIcon(new ImageIcon(btnDetalhes.getClass().getResource("/images/detalhes.png")));
		
		if (removeAction != null){
			btnExcluir.addActionListener(removeAction);
		}
		
		if(newAction != null){
			btnNovo.addActionListener(newAction);
		}
		
		if(editAction != null){
			btnEditar.addActionListener(editAction);
		}
		
		JToolBar toolbar = new JToolBar("Barra de Ferramentas");
		toolbar.add(btnNovo);
		toolbar.add(btnEditar);
		toolbar.add(btnExcluir);
		toolbar.add(btnDetalhes);
		
		return toolbar;
		
	}
}