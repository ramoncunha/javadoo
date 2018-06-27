package br.com.doonfe.componentes;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormularioItem {
	private JTextField campoCodigo = new JTextField(15);
	private JTextField campoDescricao = new JTextField(15);
	private JTextField campoPreco = new JTextField(15);
	private JTextField campoQtd = new JTextField(15);
	private ActionListener newItem;
	
	public JTextField getCampoCodigo() {
		return campoCodigo;
	}
	public void setCampoCodigo(JTextField campoCodigo) {
		this.campoCodigo = campoCodigo;
	}
	public JTextField getCampoDescricao() {
		return campoDescricao;
	}
	public void setCampoDescricao(JTextField campoDescricao) {
		this.campoDescricao = campoDescricao;
	}
	public JTextField getCampoPreco() {
		return campoPreco;
	}
	public void setCampoPreco(JTextField campoPreco) {
		this.campoPreco = campoPreco;
	}
	public JTextField getCampoQtd() {
		return campoQtd;
	}
	public void setCampoQtd(JTextField campoQtd) {
		this.campoQtd = campoQtd;
	}	
	public void setNewItem(ActionListener newItem) {
		this.newItem = newItem;
	}
	
	public JPanel buildFormularioItem() {
		/* JPanel Item */
		JPanel camposItem = new JPanel();
		camposItem.setLayout(new BoxLayout(camposItem, BoxLayout.Y_AXIS));
		camposItem.setBorder(BorderFactory.createTitledBorder("ITEM"));
		camposItem.add(new JLabel("Código"));
		camposItem.add(campoCodigo);
		camposItem.add(new JLabel("Descrição"));
		camposItem.add(campoDescricao);
		camposItem.add(new JLabel("Preço"));
		camposItem.add(campoPreco);
		camposItem.add(new JLabel("Quantidade"));
		camposItem.add(campoQtd);
		JButton salvarItem = new JButton("Inserir");
		camposItem.add(salvarItem);
		
		if(newItem != null) {
			salvarItem.addActionListener(newItem);
		}
		
		return camposItem;
	}
}
