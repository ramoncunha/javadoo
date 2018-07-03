package br.com.doonfe.telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
import br.com.doonfe.modelo.NotaFiscal;
import br.com.doonfe.modelo.PessoaFisica;
import br.com.doonfe.modelo.PessoaJuridica;

public class TelaCadastro {
	
	private Integer editarNf;	

	public void setEditarNf(Integer editarNf) {
		this.editarNf = editarNf;
	}

	public void render() {
		
		JPanel formularioNF = new JPanel();
		formularioNF.setLayout(new BoxLayout(formularioNF, BoxLayout.Y_AXIS));
		/* build formulário da nota fiscal */
		FormularioNotaFiscal camposNota = new FormularioNotaFiscal();
		formularioNF.add(camposNota.buildFormularioNF());
		/* build formulário do emitente/destinatario */
		FormularioPessoa camposPessoa = new FormularioPessoa();
		formularioNF.add(camposPessoa.buildFormularioPessoa());
		/* build formulário itens */
		FormularioItem camposItem = new FormularioItem();
		/* build tabela dos itens */
		TabelaCadastroItemNF tabelaItem = new TabelaCadastroItemNF();
		
		ActionListener salvarItem = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Integer codigoItem = Integer.parseInt(camposItem.getCampoCodigo().getText());
					String descricao = camposItem.getCampoDescricao().getText();
					Double preco = Double.parseDouble(camposItem.getCampoPreco().getText());
					Integer qtd = Integer.parseInt(camposItem.getCampoQtd().getText());
					Double total = preco * qtd;
					tabelaItem.getModel().addRow(new Object[]{codigoItem, descricao, preco, qtd, total});
				}catch(Exception e) {
					System.out.println(e);
				}
				camposItem.getCampoCodigo().setText("");
				camposItem.getCampoDescricao().setText("");
				camposItem.getCampoPreco().setText("");
				camposItem.getCampoQtd().setText("");
			}
		};
		
		ActionListener removerItem = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Integer linha = tabelaItem.getTable().getSelectedRow();
					if(linha != -1) {
						tabelaItem.getModel().removeRow(linha);
					}
				} catch(Exception e) {
					System.out.println(e);
				}
			}
		};
		/* Setando ação dos botões dos itens */
		camposItem.setNewItem(salvarItem);
		camposItem.setRemoveItem(removerItem);
		
		formularioNF.add(camposItem.buildFormularioItem());
		
		JScrollPane jScrollPane = tabelaItem.buildTabela();		
		formularioNF.add(jScrollPane);
		
		/* Se houver ID para editar uma nota */
		if(editarNf != null) {
			NotaFiscalDAO persistNF = new NotaFiscalDAO();
			NotaFiscal nfEditavel = persistNF.editarNotaFiscal(editarNf);
			
			camposNota.setNotaFiscal(nfEditavel);
			
			camposPessoa.setEmitente(nfEditavel.getEmitente());
			camposPessoa.setDestinatario(nfEditavel.getDestinatario());
			
			List<Itens> itens = nfEditavel.getItens();
			for (Itens itens2 : itens) {
				tabelaItem.setItemNF(itens2);
			}
		}
		
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
					NotaFiscal nf = camposNota.toNotaFiscal();
					
					if(camposPessoa.getCampoInscricao1().getText().equals("")) {
						PessoaFisica pessoaEmitente1 = camposPessoa.toPessoaFisica1();
						nf.setEmitente(pessoaEmitente1);
					} else {
						PessoaJuridica pessoaEmitente2 = camposPessoa.toPessoaJuridica1();
						nf.setEmitente(pessoaEmitente2);
					}
					
					if(camposPessoa.getCampoInscricao2().getText().equals("")) {
						PessoaFisica pessoaDestinatario1 = camposPessoa.toPessoaFisica2();
						nf.setDestinatario(pessoaDestinatario1);
					} else {
						PessoaJuridica pessoaDestinatario2 = camposPessoa.toPessoaJuridica2();
						nf.setDestinatario(pessoaDestinatario2);
					}
					
					/* Navegando nas linhas */
					for(int i=0; i < tabelaItem.getModel().getRowCount(); i++) {
						Itens itemLinha = new Itens();
						/* Navegando nas colunas */
						for(int j=0; j < 4; j++) {
							Object valorColuna = tabelaItem.getModel().getValueAt(i, j);
							
							if(j == 0) {
								itemLinha.setCodigo((Integer) (valorColuna));
							} else if(j == 1) {
								itemLinha.setDescricao((String) valorColuna);
							} else if(j == 2) {
								itemLinha.setValor((Double) valorColuna);
							} else if(j == 3) {
								itemLinha.setQuantidade((Integer) valorColuna);
							}
						}
						nf.addItem(itemLinha);
					}
					/* Se for editavel */
					if(editarNf != null) {
						nf.setId(editarNf);
						persistNF.alterarNotaFiscal(nf);
					} else {
						persistNF.salvarNotaFiscal(nf);
					}
					
					TelaPrincipal Main = new TelaPrincipal();
					Main.render();
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		};
		/* Setando ação dos botoões do cadastro de Nota Fiscal */
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
}
