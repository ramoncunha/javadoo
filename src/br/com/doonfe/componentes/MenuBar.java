package br.com.doonfe.componentes;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.com.doonfe.action.GenericsAction;

public class MenuBar {

	public JMenuBar build() {
		GenericsAction actionGeneric = new GenericsAction();
		
		JMenuItem eMenuSair = new JMenuItem("Sair");
		eMenuSair.setMnemonic(KeyEvent.VK_S);
		eMenuSair.setToolTipText("Sair da Aplicação");
		eMenuSair.addActionListener((ActionEvent event) -> {
		    System.exit(0);
		});
		//Opção ir para Tela Principal
		JMenuItem eMenuTelaPrincipal = new JMenuItem("Tela Principal");
		eMenuTelaPrincipal.setMnemonic(KeyEvent.VK_T);
		eMenuTelaPrincipal.setToolTipText("Navegar para Tela Principal");
		// Opção incluir Nota Fiscal
		JMenuItem eMenuIncluirNota = new JMenuItem("Incluir Nota Fiscal");
		eMenuIncluirNota.setMnemonic(KeyEvent.VK_N);
		eMenuIncluirNota.setToolTipText("Incluir nova Nota Fiscal");
		// Aba Arquivo
		JMenu fileArquivo = new JMenu("Arquivo");
		fileArquivo.setMnemonic(KeyEvent.VK_F);
		fileArquivo.add(eMenuTelaPrincipal);
		fileArquivo.add(eMenuSair);
		// Aba Cadastro
		JMenu fileCadastro = new JMenu("Cadastro");
		fileCadastro.setMnemonic(KeyEvent.VK_I);
		fileCadastro.add(eMenuIncluirNota);
		// Aba Sobre
		JMenu fileSobre = new JMenu("Sobre");
		fileSobre.setMnemonic(KeyEvent.VK_S);
		
		eMenuIncluirNota.addActionListener(actionGeneric.novaNotaFiscal());
		
		eMenuTelaPrincipal.addActionListener(actionGeneric.navegarTelaPrincipal());
		
		
		// MENU BAR
		JMenuBar menubar = new JMenuBar();
		menubar.add(fileArquivo);
		menubar.add(fileCadastro);
		menubar.add(fileSobre);
		
		return menubar;
	}
}
