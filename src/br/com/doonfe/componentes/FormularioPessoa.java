package br.com.doonfe.componentes;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.doonfe.modelo.PessoaFisica;
import br.com.doonfe.modelo.PessoaJuridica;

public class FormularioPessoa {

	/* Campos Formulário Pessoa */
	private JTextField campoDocumento1 = new JTextField(15);
	private JTextField campoNome1 = new JTextField(15);
	private JTextField campoInscricao1 = new JTextField(15);
	private JTextField campoEstado1 = new JTextField(5);
	
	private JTextField campoDocumento2 = new JTextField(15);
	private JTextField campoNome2 = new JTextField(15);
	private JTextField campoInscricao2 = new JTextField(15);
	private JTextField campoEstado2 = new JTextField(5);
	
	public JTextField getCampoDocumento1() {
		return campoDocumento1;
	}
	public void setCampoDocumento1(JTextField campoDocumento1) {
		this.campoDocumento1 = campoDocumento1;
	}
	public JTextField getCampoNome1() {
		return campoNome1;
	}
	public void setCampoNome1(JTextField campoNome1) {
		this.campoNome1 = campoNome1;
	}
	public JTextField getCampoInscricao1() {
		return campoInscricao1;
	}
	public void setCampoInscricao1(JTextField campoInscricao1) {
		this.campoInscricao1 = campoInscricao1;
	}
	public JTextField getCampoEstado1() {
		return campoEstado1;
	}
	public void setCampoEstado1(JTextField campoEstado1) {
		this.campoEstado1 = campoEstado1;
	}
	public JTextField getCampoDocumento2() {
		return campoDocumento2;
	}
	public void setCampoDocumento2(JTextField campoDocumento2) {
		this.campoDocumento2 = campoDocumento2;
	}
	public JTextField getCampoNome2() {
		return campoNome2;
	}
	public void setCampoNome2(JTextField campoNome2) {
		this.campoNome2 = campoNome2;
	}
	public JTextField getCampoInscricao2() {
		return campoInscricao2;
	}
	public void setCampoInscricao2(JTextField campoInscricao2) {
		this.campoInscricao2 = campoInscricao2;
	}
	public JTextField getCampoEstado2() {
		return campoEstado2;
	}
	public void setCampoEstado2(JTextField campoEstado2) {
		this.campoEstado2 = campoEstado2;
	}
	
	public JPanel buildFormularioPessoa() {
		
		JPanel paiPanel = new JPanel();
		paiPanel.setLayout(new BoxLayout(paiPanel, BoxLayout.X_AXIS));
		
		/* JPane Pessoa Emitente */
		JPanel emitentePanel = new JPanel();
		emitentePanel.setBorder(BorderFactory.createTitledBorder("EMITENTE"));
		emitentePanel.setLayout(new BoxLayout(emitentePanel, BoxLayout.Y_AXIS));
		emitentePanel.add(new JLabel("CNPJ/CPF"));
		emitentePanel.add(campoDocumento1);
		emitentePanel.add(new JLabel("Razão Social/Nome"));
		emitentePanel.add(campoNome1);
		emitentePanel.add(new JLabel("Inscrição Estadual"));
		emitentePanel.add(campoInscricao1);
		emitentePanel.add(new JLabel("Estado"));
		emitentePanel.add(campoEstado1);
		
		/* JPane Pessoa Destinatario */
		JPanel destinatarioPanel = new JPanel();
		destinatarioPanel.setBorder(BorderFactory.createTitledBorder("DESTINATÁRIO"));
		destinatarioPanel.setLayout(new BoxLayout(destinatarioPanel, BoxLayout.Y_AXIS));
		destinatarioPanel.add(new JLabel("CNPJ/CPF"));
		destinatarioPanel.add(campoDocumento2);
		destinatarioPanel.add(new JLabel("Razão Social/Nome"));
		destinatarioPanel.add(campoNome2);
		destinatarioPanel.add(new JLabel("Inscrição Estadual"));
		destinatarioPanel.add(campoInscricao2);
		destinatarioPanel.add(new JLabel("Estado"));
		destinatarioPanel.add(campoEstado2);
		
		paiPanel.add(emitentePanel);
		paiPanel.add(destinatarioPanel);
		
		return paiPanel;
	}
	
	public PessoaFisica toPessoaFisica1() {
		PessoaFisica pessoaFisica1 = new PessoaFisica();
		pessoaFisica1.setNome(getCampoNome1().getText());
		pessoaFisica1.setCpf(getCampoDocumento1().getText());
		pessoaFisica1.setEstado(getCampoEstado1().getText());
		
		return pessoaFisica1;
	}
	
	public PessoaFisica toPessoaFisica2() {
		PessoaFisica pessoaFisica2 = new PessoaFisica();
		pessoaFisica2.setNome(getCampoNome2().getText());
		pessoaFisica2.setCpf(getCampoDocumento2().getText());
		pessoaFisica2.setEstado(getCampoEstado2().getText());
		
		return pessoaFisica2;
	}
	
	public PessoaJuridica toPessoaJuridica1() {
		PessoaJuridica pessoaJuridica1 = new PessoaJuridica();
		pessoaJuridica1.setCnpj(getCampoDocumento1().getText());
		pessoaJuridica1.setInscricaoEstadual(getCampoInscricao1().getText());
		pessoaJuridica1.setRazaoSocial(getCampoNome1().getText());
		pessoaJuridica1.setEstado(getCampoEstado1().getText());
		
		return pessoaJuridica1;
	}
	
	public PessoaJuridica toPessoaJuridica2 () {
		PessoaJuridica pessoaJuridica2 = new PessoaJuridica();
		pessoaJuridica2.setCnpj(getCampoDocumento2().getText());
		pessoaJuridica2.setInscricaoEstadual(getCampoInscricao2().getText());
		pessoaJuridica2.setRazaoSocial(getCampoNome2().getText());
		pessoaJuridica2.setEstado(getCampoEstado2().getText());
		
		return pessoaJuridica2;
	}
}
