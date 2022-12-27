package br.com.cod3r.minado;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import br.com.cod3r.minado.model.PropertiesProjeto;
import br.com.cod3r.minado.model.Tabuleiro;
import br.com.cod3r.minado.view.PainelTabuleiro;

@SuppressWarnings("serial")
public class TelaJogo extends JFrame {
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
		
		super.setResizable(false);
				
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
			JOptionPane.showMessageDialog(this, "Cada partida come�a com um tabuleiro dividido em v�rios quadrados sem qualquer marca��o. Depois de clicar em um deles, os quadrados em volta desaparecem: alguns ficar�o brancos, \nenquanto outros ter�o n�meros. Com a ajuda dos n�meros, ser� poss�vel descobrir quais possuem minas por perto e quais podem ser clicados.\n\nUse os bot�es esquerdo e direito do mouse. N�o � preciso nada al�m do mouse para desfrutar do jogo; com o bot�o esquerdo, voc� clica em quadrados sem minas, enquanto o direito marca os quadrados �perigosos�.", "CAMPO MINADO", JOptionPane.INFORMATION_MESSAGE, PropertiesProjeto.getIcone("logo.png"));
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