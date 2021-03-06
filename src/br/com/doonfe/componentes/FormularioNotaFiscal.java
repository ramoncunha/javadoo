package br.com.doonfe.componentes;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.doonfe.modelo.ModeloNF;
import br.com.doonfe.modelo.NaturezaNF;
import br.com.doonfe.modelo.NotaFiscal;

public class FormularioNotaFiscal {
	
	/* Campos Formulário Nota Fiscal */
	private JTextField campoNNota = new JTextField(10);
	private JTextField campoModelo = new JTextField(10);
	private JTextField campoNatureza = new JTextField(10);
	private JTextField campoDtOperacao = new JTextField(10);
	private JTextField campoDtEmissao = new JTextField(10);
	private JTextField campoInformacoesComplementares = new JTextField(20);
	
	public JTextField getCampoNNota() {
		return campoNNota;
	}
	public void setCampoNNota(JTextField campoNNota) {
		this.campoNNota = campoNNota;
	}
	public JTextField getCampoDtOperacao() {
		return campoDtOperacao;
	}
	public void setCampoDtOperacao(JTextField campoDtOperacao) {
		this.campoDtOperacao = campoDtOperacao;
	}
	public JTextField getCampoDtEmissao() {
		return campoDtEmissao;
	}
	public void setCampoDtEmissao(JTextField campoDtEmissao) {
		this.campoDtEmissao = campoDtEmissao;
	}
	public JTextField getCampoInformacoesComplementares() {
		return campoInformacoesComplementares;
	}
	public void setCampoInformacoesComplementares(JTextField campoInformacoesComplementares) {
		this.campoInformacoesComplementares = campoInformacoesComplementares;
	}
	
	public JPanel buildFormularioNF() {
		
		JPanel camposNota = new JPanel();
		camposNota.setLayout(new BoxLayout(camposNota, BoxLayout.Y_AXIS));
		camposNota.add(new JLabel("N Nota"));
		camposNota.add(campoNNota);
		camposNota.add(new JLabel("Modelo"));
		campoModelo.setText("Modelo 1-A");
		campoModelo.setEditable(false);
		camposNota.add(campoModelo);
		camposNota.add(new JLabel("Natureza"));
		campoNatureza.setText("Venda");
		campoNatureza.setEditable(false);
		camposNota.add(campoNatureza);
		camposNota.add(new JLabel("Data da Operação"));
		camposNota.add(campoDtOperacao);
		camposNota.add(new JLabel("Data da Emissão"));
		camposNota.add(campoDtEmissao);
		camposNota.add(new JLabel("Informações Complementares"));
		camposNota.add(campoInformacoesComplementares);
		
		return camposNota;
	}
	
	public NotaFiscal toNotaFiscal() {
		NotaFiscal nf = new NotaFiscal();
		nf.setNumeroNota(Integer.parseInt(getCampoNNota().getText()));
		nf.setNatureza(NaturezaNF.VENDA);
		nf.setModelo(ModeloNF.MODELO1_A);
		nf.setDataOperacao(getCampoDtOperacao().getText());
		nf.setDataEmissao(getCampoDtEmissao().getText());
		nf.setInformacoesComplementares(getCampoInformacoesComplementares().getText());
		
		return nf;
	}
	
	public void setNotaFiscal(NotaFiscal nf) {
		campoNNota.setText(String.valueOf(nf.getNumeroNota()));
		campoDtOperacao.setText(nf.getDataOperacao());
		campoDtEmissao.setText(nf.getDataEmissao());
		campoInformacoesComplementares.setText(nf.getInformacoesComplementares());
	}
}
