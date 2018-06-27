package br.com.doonfe.telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import br.com.doonfe.componentes.BotaoCadastro;
import br.com.doonfe.componentes.FormularioItem;
import br.com.doonfe.componentes.FormularioNotaFiscal;
import br.com.doonfe.componentes.FormularioPessoa;
import br.com.doonfe.componentes.MenuBar;
import br.com.doonfe.componentes.TabelaCadastroItemNF;
import br.com.doonfe.dao.NotaFiscalDAO;
import br.com.doonfe.modelo.Itens;
import br.com.doonfe.modelo.ModeloNF;
import br.com.doonfe.modelo.NaturezaNF;
import br.com.doonfe.modelo.NotaFiscal;
import br.com.doonfe.modelo.PessoaFisica;
import br.com.doonfe.modelo.PessoaJuridica;

public class TelaCadastro {

	public void render() {
		
		JPanel formularioNF = new JPanel();
		formularioNF.setLayout(new BoxLayout(formularioNF, BoxLayout.Y_AXIS));
		
		FormularioNotaFiscal camposNota = new FormularioNotaFiscal();
		formularioNF.add(camposNota.buildFormularioNF());
		
		FormularioPessoa camposPessoa = new FormularioPessoa();
		formularioNF.add(camposPessoa.buildFormularioPessoa());
		
		FormularioItem camposItem = new FormularioItem();
		formularioNF.add(camposItem.buildFormularioItem());
		
		TabelaCadastroItemNF tabelaItem = new TabelaCadastroItemNF();
		JScrollPane jScrollPane = tabelaItem.buildTabela();		
		formularioNF.add(jScrollPane);
		
		ActionListener salvarItem = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println("Entrou");
					Integer codigoItem = Integer.parseInt(camposItem.getCampoCodigo().getText());
					String descricao = camposItem.getCampoDescricao().getText();
					Double preco = Double.parseDouble(camposItem.getCampoPreco().getText());
					Integer qtd = Integer.parseInt(camposItem.getCampoQtd().getText());
					
					tabelaItem.getModel().addRow(new Object[]{codigoItem, descricao, preco, qtd});
				}catch(Exception e) {
					System.out.println(e);
				}
				camposItem.getCampoCodigo().setText("");
				camposItem.getCampoDescricao().setText("");
				camposItem.getCampoPreco().setText("");
				camposItem.getCampoQtd().setText("");
			}
		};
		camposItem.setNewAction(salvarItem);
		
		ActionListener cancelarCadastro = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int dialogResult = JOptionPane.showConfirmDialog(null,"Você tem certeza que deseja cancelar?");
				if(dialogResult == JOptionPane.YES_OPTION) {
					TelaPrincipal Main = new TelaPrincipal();
					Main.render();
				}
			}
		};
		
		ActionListener salvarCadastro = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					NotaFiscalDAO persistNF = new NotaFiscalDAO();
					NotaFiscal nf = new NotaFiscal();
					nf.setNumeroNota(Integer.parseInt(camposNota.getCampoNNota().getText()));
					nf.setNatureza(NaturezaNF.VENDA);
					nf.setModelo(ModeloNF.MODELO1_A);
					nf.setDataOperacao(Calendar.getInstance());
					nf.setDataEmissao(Calendar.getInstance());
					nf.setInformacoesComplementares(camposNota.getCampoInformacoesComplementares().getText());
					
					if(camposPessoa.getCampoInscricao1().getText().equals("")) {
						PessoaFisica pessoaEmitente1 = new PessoaFisica();
						pessoaEmitente1.setNome(camposPessoa.getCampoNome1().getText());
						pessoaEmitente1.setCpf(camposPessoa.getCampoDocumento1().getText());
						pessoaEmitente1.setEstado(camposPessoa.getCampoEstado1().getText());
						nf.setEmitente(pessoaEmitente1);
					} else {
						PessoaJuridica pessoaEmitente2 = new PessoaJuridica();
						pessoaEmitente2.setCnpj(camposPessoa.getCampoDocumento1().getText());
						pessoaEmitente2.setInscricaoEstadual(camposPessoa.getCampoInscricao1().getText());
						pessoaEmitente2.setRazaoSocial(camposPessoa.getCampoNome1().getText());
						pessoaEmitente2.setEstado(camposPessoa.getCampoEstado1().getText());
						nf.setEmitente(pessoaEmitente2);
					}
					
					if(camposPessoa.getCampoInscricao2().getText().equals("")) {
						PessoaFisica pessoaDestinatario1 = new PessoaFisica();
						pessoaDestinatario1.setNome(camposPessoa.getCampoNome2().getText());
						pessoaDestinatario1.setCpf(camposPessoa.getCampoDocumento2().getText());
						pessoaDestinatario1.setEstado(camposPessoa.getCampoEstado2().getText());
						nf.setDestinatario(pessoaDestinatario1);
					} else {
						PessoaJuridica pessoaDestinatario2 = new PessoaJuridica();
						pessoaDestinatario2.setCnpj(camposPessoa.getCampoDocumento2().getText());
						pessoaDestinatario2.setInscricaoEstadual(camposPessoa.getCampoInscricao2().getText());
						pessoaDestinatario2.setRazaoSocial(camposPessoa.getCampoNome2().getText());
						pessoaDestinatario2.setEstado(camposPessoa.getCampoEstado2().getText());
						nf.setDestinatario(pessoaDestinatario2);
					}
					
					Itens item1 = new Itens();
					item1.setCodigo(1789);
					item1.setDescricao("Biscoito");
					item1.setQuantidade(5);
					item1.setValor(50.0);
					nf.setItens(Arrays.asList(item1));
					
					//nf.addItem(item1);
					
					persistNF.salvarNotaFiscal(nf);
					
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		};
		
		BotaoCadastro painelBotoes = new BotaoCadastro();
		painelBotoes.setCancelAction(cancelarCadastro);
		painelBotoes.setSaveAction(salvarCadastro);
		
		JScrollPane jScrollPaneFormulario = new JScrollPane();
		jScrollPaneFormulario.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPaneFormulario.setViewportView(formularioNF);
		
		JPanel pai = new JPanel();
		pai.add(jScrollPaneFormulario);
		pai.add(painelBotoes.buildBotaoFormulario());
		pai.setLayout(new BoxLayout(pai, BoxLayout.Y_AXIS));
		
		MenuBar menubar = new MenuBar();
		
		JFrame janela = new JFrame();
		janela.setTitle("Cadastro");
		janela.setSize(800, 600);
		janela.add(pai);
		janela.setJMenuBar(menubar.build());
		janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janela.setVisible(true);
	}

	/*private JScrollPane buildTabelaItensNF() {
		/* Lista de Itens 
		Object[] colunas = new String[]{"Código", "Descrição", "Preço", "Qtd", "Total"};
		Object[][] dados = new Object[][]{};
		
		DefaultTableModel model = new DefaultTableModel(dados, colunas);
	
		
		JTable table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model);
		
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setViewportView(table);
		/* FIM LISTAGEM ITENS  
		return jScrollPane;
	}*/
}
