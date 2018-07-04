package br.com.doonfe.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.com.doonfe.telas.TelaCadastro;
import br.com.doonfe.telas.TelaIndicadores;
import br.com.doonfe.telas.TelaPrincipal;
import br.com.doonfe.telas.TelaSobre;

public class GenericsAction {
	
	public ActionListener novaNotaFiscal() {
		 ActionListener novaNotaFiscal = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastro TelaCadastro = new TelaCadastro();
				TelaCadastro.render();
			}
		};
		
		return novaNotaFiscal;
	}
	
	public ActionListener navegarTelaPrincipal() {
		ActionListener telaPrincipal = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TelaPrincipal Main = new TelaPrincipal();
				Main.render();
			}
		};
		
		return telaPrincipal;
	}
	
	public ActionListener navegarTelaSobre() {
		ActionListener telaSobre = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TelaSobre TelaSobre = new TelaSobre();
				TelaSobre.render();
			}
		};
		
		return telaSobre;
	}
	
	public ActionListener navegarTelaIndicadores() {
		ActionListener telaIndicadores = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TelaIndicadores TelaIndicadores = new TelaIndicadores();
				TelaIndicadores.render();
			}
		};
		
		return telaIndicadores;
	}
}
