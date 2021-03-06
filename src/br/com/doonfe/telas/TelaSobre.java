package br.com.doonfe.telas;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import br.com.doonfe.componentes.MenuBar;

public class TelaSobre {

	public void render() {
		
		MenuBar menubar = new MenuBar();
		
		Font fonteTitulos = new Font("Serif", Font.BOLD, 50);
		Font fonteSubTitulos = new Font("Serif", Font.BOLD, 20);
		
		JLabel titulo = new JLabel("Trabalho Modular DOO");
		titulo.setFont(fonteTitulos);
		
		JLabel disciplinaTitulo = new JLabel("Desenvolvimento Orientado a Objetos"); 
		disciplinaTitulo.setFont(fonteSubTitulos);
		
		JLabel equipeCassio = new JLabel("- Cássio Zanganelli");
		equipeCassio.setFont(fonteSubTitulos);
		JLabel equipeIcaro = new JLabel("- Ícaro Ferreira");
		equipeIcaro.setFont(fonteSubTitulos);
		JLabel equipeRamon = new JLabel("- Ramon Pires");
		equipeRamon.setFont(fonteSubTitulos);
		
		JLabel infTrabalho = new JLabel("Trabalho desenvolvido na linguagem Java utilizando o framework Hibernate"
				+ " e Swing");
		infTrabalho.setFont(fonteSubTitulos);
		
		JLabel quebraLinha1 = new JLabel("<html><br></html>");
		JLabel quebraLinha2 = new JLabel("<html><br><br><br></html>");
		JLabel quebraLinha3 = new JLabel("<html><br><br><br></html>");
		
		JPanel sobrePanel = new JPanel();
		sobrePanel.setLayout(new BoxLayout(sobrePanel, BoxLayout.Y_AXIS));
		sobrePanel.add(titulo);
		sobrePanel.add(quebraLinha1);
		sobrePanel.add(disciplinaTitulo);
		sobrePanel.add(quebraLinha2);
		sobrePanel.add(equipeCassio);
		sobrePanel.add(equipeIcaro);
		sobrePanel.add(equipeRamon);
		sobrePanel.add(quebraLinha3);
		sobrePanel.add(infTrabalho);
		
		JFrame janela = new JFrame();
		janela.setTitle("Sobre");
		janela.setSize(800, 600);
		janela.add(sobrePanel);
		janela.setJMenuBar(menubar.build());
		janela.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janela.setVisible(true);
	}
	
}
