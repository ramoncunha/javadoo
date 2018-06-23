package br.com.doonfe.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import br.com.doonfe.modelo.NotaFiscal;
import br.com.doonfe.util.JPAUtil;

public class Main {

	public static void main(String[] args) {
		
		/* ****************Criando JMenu Bar**************** */
		JMenuItem eMenuSair = new JMenuItem("Sair");
		eMenuSair.setMnemonic(KeyEvent.VK_S);
		eMenuSair.setToolTipText("Sair da Aplicação");
		eMenuSair.addActionListener((ActionEvent event) -> {
		    System.exit(0);
		});
		// Opção incluir Nota Fiscal
		JMenuItem eMenuIncluirNota = new JMenuItem("Incluir Nota Fiscal");
		eMenuIncluirNota.setMnemonic(KeyEvent.VK_N);
		eMenuIncluirNota.setToolTipText("Incluir nova Nota Fiscal");
		// Aba Arquivo
		JMenu fileArquivo = new JMenu("Arquivo");
		fileArquivo.setMnemonic(KeyEvent.VK_F);
		fileArquivo.add(eMenuSair);
		// Aba Cadastro
		JMenu fileCadastro = new JMenu("Cadastro");
		fileCadastro.setMnemonic(KeyEvent.VK_I);
		fileCadastro.add(eMenuIncluirNota);
		// Aba Sobre
		JMenu fileSobre = new JMenu("Sobre");
		fileSobre.setMnemonic(KeyEvent.VK_S);
		// MENU BAR
		JMenuBar menubar = new JMenuBar();
		menubar.add(fileArquivo);
		menubar.add(fileCadastro);
		menubar.add(fileSobre);
		/* ****************FIM JMENU BAR**************** */
		
		/* ****************Criando JToolBar**************** */
		JButton btnNovo = new JButton();
		btnNovo.setIcon(new ImageIcon(btnNovo.getClass().getResource("/images/novo.png")));
		JButton btnEditar = new JButton();
		btnEditar.setIcon(new ImageIcon(btnEditar.getClass().getResource("/images/editar.png")));
		JButton btnExcluir = new JButton();
		btnExcluir.setIcon(new ImageIcon(btnExcluir.getClass().getResource("/images/excluir.png")));
		JButton btnDetalhes = new JButton();
		btnDetalhes.setIcon(new ImageIcon(btnDetalhes.getClass().getResource("/images/detalhes.png")));
		
		JToolBar toolbar = new JToolBar("Barra de Ferramentas");
		toolbar.add(btnNovo);
		toolbar.add(btnEditar);
		toolbar.add(btnExcluir);
		toolbar.add(btnDetalhes);
		/* ****************FIM JTOOLBAR**************** */
		
		Object[] colunas = new String[]{"N Nota", "Modelo", "Natureza", "Dt Emissão", "Destinatario", "Emitente"};
		Object[][] dados = new Object[][]{};
		
		DefaultTableModel model = new DefaultTableModel(dados, colunas);
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		String jpql = "select m from NotaFiscal m";
		Query query = em.createQuery(jpql);
		
		List<NotaFiscal> resultados = query.getResultList();
		
		for (NotaFiscal notaFiscal : resultados) {
			model.addRow(new Object[] {
					notaFiscal.getNumeroNota(),
					notaFiscal.getModelo(),
					notaFiscal.getNatureza(),
					notaFiscal.getDataEmissao(),
					notaFiscal.getDestinatario(),
					notaFiscal.getEmitente()
			});
		}
		
		em.getTransaction().commit();
		em.close();
		
		JTable table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model);
		
		ActionListener ExcluirLinha = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int linha = table.getSelectedRow();
					if(linha != -1)
						model.removeRow(linha);
				} catch(ArrayIndexOutOfBoundsException e) {
					System.out.println(e);
				}
			}
		};
		
		btnExcluir.addActionListener(ExcluirLinha);
		
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setViewportView(table);
		
		JFrame frame = new JFrame("Tela Inicial");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(toolbar, BorderLayout.PAGE_START);
		frame.setJMenuBar(menubar);
		frame.add(jScrollPane);
		frame.setSize(800, 600);
		frame.setVisible(true);
	}

}
