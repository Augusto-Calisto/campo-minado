package br.com.minado.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import br.com.minado.model.PropertiesProjeto;
import br.com.minado.model.Tabuleiro;

public class TelaJogo extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menuBar;
	private JMenu menuJogo;
	private JMenu menuSobre;
	private JMenuItem itemReiniciar;
	private JMenuItem itemSobreJogo;
	private JMenuItem itemSobreDesenvolvedor;
	
	public TelaJogo(Tabuleiro tabuleiro) {
		PainelTabuleiro painelTabuleiro = new PainelTabuleiro(tabuleiro);
		
		super.add(painelTabuleiro);
		
		super.setTitle("CAMPO MINADO");
				
		super.setSize(690, 438);
						
		super.setLocationRelativeTo(null); // Centralizando a tela
		
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						
		super.setIconImage(PropertiesProjeto.getImagem("logo.png"));
		
		menuBar = new JMenuBar();
		
		addMenuJogo(tabuleiro);
		
		addMenuSobre();
		
		super.setJMenuBar(menuBar);
				
		super.setVisible(true);
	}
	
	private void addMenuJogo(Tabuleiro tabuleiro) {		
		menuJogo = new JMenu("Jogo");
						
		itemReiniciar = new JMenuItem("Reiniciar");
				
		itemReiniciar.addActionListener((e) -> {
			tabuleiro.reiniciarJogo();
		});
		
		menuJogo.add(itemReiniciar);
						
		menuBar.add(menuJogo);
	}
	
	private void addMenuSobre() {
		menuSobre = new JMenu("Sobre");
				
		itemSobreJogo = new JMenuItem("Regras do Jogo");
		
		itemSobreJogo.addActionListener((e) -> {
			JOptionPane.showMessageDialog(this, "Cada partida come�a com um tabuleiro dividido em v�rios quadrados sem qualquer marcacao. Depois de clicar em um deles, os quadrados em volta desaparecem: alguns ficarao brancos, \nenquanto outros terao numeros. Com a ajuda dos numeros, sera possivel descobrir quais possuem minas por perto e quais podem ser clicados.\n\nUse os botoes esquerdo e direito do mouse. Nao e preciso nada alem do mouse para desfrutar do jogo; com o botao esquerdo, voce clica em quadrados sem minas, enquanto o direito marca os quadrados perigosos.", "CAMPO MINADO", JOptionPane.INFORMATION_MESSAGE, PropertiesProjeto.getIcone("logo.png"));
		});
		
		itemSobreDesenvolvedor = new JMenuItem("Desenvolvedor");
		
		itemSobreDesenvolvedor.addActionListener((e) -> {
			JOptionPane.showMessageDialog(this, "DESENVOLVIDO POR AUGUSTO CALISTO", "CAMPO MINADO", JOptionPane.INFORMATION_MESSAGE, PropertiesProjeto.getIcone("logo.png"));
		});
				
		menuSobre.add(itemSobreJogo);
		
		menuSobre.add(itemSobreDesenvolvedor);
				
		menuBar.add(menuJogo);
		
		menuBar.add(menuSobre);
	}
}