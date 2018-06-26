package br.com.doonfe.componentes;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BotaoCadastro {
	
	private ActionListener saveAction;
	
	private ActionListener cancelAction;

	public void setSaveAction(ActionListener saveAction) {
		this.saveAction = saveAction;
	}

	public void setCancelAction(ActionListener cancelAction) {
		this.cancelAction = cancelAction;
	}
	
	public JPanel buildBotaoFormulario() {
		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton btnSalvar = new JButton("Salvar");
		JButton btnCancelar = new JButton("Cancelar");
		painelBotoes.add(btnSalvar);
		painelBotoes.add(btnCancelar);
		
		if(saveAction != null) {
			btnSalvar.addActionListener(saveAction);
		}
		if(cancelAction != null) {
			btnCancelar.addActionListener(cancelAction);
		}
		
		return painelBotoes;
	}
	
}
