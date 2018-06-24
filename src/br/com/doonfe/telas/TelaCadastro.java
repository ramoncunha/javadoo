package br.com.doonfe.telas;

import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class TelaCadastro {

	public static void main(String[] args) {
		
		/* Campos Formulário Nota Fiscal */
		JTextField campoNNota = new JTextField(10);
		JTextField campoModelo = new JTextField(10);
		campoModelo.setText("Modelo 1-A");
		campoModelo.setEditable(false);
		JTextField campoNatureza = new JTextField(10);
		campoNatureza.setText("Venda");
		campoNatureza.setEditable(false);
		JTextField campoDtOperacao = new JTextField(10);
		JTextField campoDtEmissao = new JTextField(10);
		
		/* Campos Formulário Pessoa */
		JTextField campoDocumento1 = new JTextField(15);
		JTextField campoNome1 = new JTextField(15);
		JTextField campoInscricao1 = new JTextField(15);
		JTextField campoEstado1 = new JTextField(5);
		
		JTextField campoDocumento2 = new JTextField(15);
		JTextField campoNome2 = new JTextField(15);
		JTextField campoInscricao2 = new JTextField(15);
		JTextField campoEstado2 = new JTextField(5);
		
		/* JPane Formulário Nota Fiscal */
		JPanel formularioNF = new JPanel();
		//formularioNF.setLayout(new BoxLayout(formularioNF, BoxLayout.Y_AXIS));
		formularioNF.add(new JLabel("N Nota"));
		formularioNF.add(campoNNota);
		formularioNF.add(new JLabel("Modelo"));
		formularioNF.add(campoModelo);
		formularioNF.add(new JLabel("Natureza"));
		formularioNF.add(campoNatureza);
		formularioNF.add(new JLabel("Data da Operação"));
		formularioNF.add(campoDtOperacao);
		formularioNF.add(new JLabel("Data da Emissão"));
		formularioNF.add(campoDtEmissao);
		
		/* JPane Formulário Pessoa */
		JPanel formularioPessoa = new JPanel();
		//LayoutManager layoutPessoa = new FlowLayout(FlowLayout.RIGHT);
		//formularioPessoa.setLayout(layoutPessoa);
		formularioPessoa.add(new JLabel("EMITENTE"));
		formularioPessoa.add(new JLabel("CNPJ/CPF"));
		formularioPessoa.add(campoDocumento1);
		formularioPessoa.add(new JLabel("Razão Social/Nome"));
		formularioPessoa.add(campoNome1);
		formularioPessoa.add(new JLabel("Inscrição Estadual"));
		formularioPessoa.add(campoInscricao1);
		formularioPessoa.add(new JLabel("Estado"));
		formularioPessoa.add(campoEstado1);
		
		formularioPessoa.add(new JLabel("DESTINATARIO"));
		formularioPessoa.add(new JLabel("CNPJ/CPF"));
		formularioPessoa.add(campoDocumento2);
		formularioPessoa.add(new JLabel("Razão Social/Nome"));
		formularioPessoa.add(campoNome2);
		formularioPessoa.add(new JLabel("Inscrição Estadual"));
		formularioPessoa.add(campoInscricao2);
		formularioPessoa.add(new JLabel("Estado"));
		formularioPessoa.add(campoEstado2);
		
		JPanel pai = new JPanel();
		pai.add(formularioNF);
		pai.add(formularioPessoa);
		pai.setLayout(new BoxLayout(pai, BoxLayout.X_AXIS));
		
		JFrame janela = new JFrame();
		janela.setTitle("Cadastro");
		janela.setSize(800, 600);
		janela.add(pai);
		janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janela.setVisible(true);
	}
}
