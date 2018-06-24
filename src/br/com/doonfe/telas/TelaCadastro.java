package br.com.doonfe.telas;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class TelaCadastro {

	public static void main(String[] args) {
		
		JTextField campoNNota = new JTextField(10);
		JTextField campoModelo = new JTextField(10);
		JTextField campoNatureza = new JTextField(10);
		JTextField campoDtOperacao = new JTextField(10);
		JTextField campoDtEmissao = new JTextField(10);
		
		JPanel formulario = new JPanel();
		formulario.add(new JLabel("N Nota"));
		formulario.add(campoNNota);
		formulario.add(new JLabel("Modelo"));
		formulario.add(campoModelo);
		formulario.add(new JLabel("Natureza"));
		formulario.add(campoNatureza);
		formulario.add(new JLabel("Data da Operação"));
		formulario.add(campoDtOperacao);
		formulario.add(new JLabel("Data da Emissão"));
		formulario.add(campoDtEmissao);
		
		JPanel pai = new JPanel();
		pai.add(formulario);
		pai.setLayout(new BoxLayout(pai, BoxLayout.Y_AXIS));
		
		JFrame janela = new JFrame();
		janela.setTitle("Cadastro");
		janela.setSize(800, 600);
		janela.add(pai);
		janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janela.setVisible(true);
		
		
	}

}
