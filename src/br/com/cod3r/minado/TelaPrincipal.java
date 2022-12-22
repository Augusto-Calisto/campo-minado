package br.com.cod3r.minado;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.com.cod3r.minado.model.Tabuleiro;
import br.com.cod3r.minado.view.PainelTabuleiro;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {
	public static final String PATH = System.getProperty("user.dir");
	
	public TelaPrincipal(String titulo) {
		Tabuleiro tabuleiro = new Tabuleiro(15, 15, 20);
		
		PainelTabuleiro painelTabuleiro = new PainelTabuleiro(tabuleiro);
		
		super.add(painelTabuleiro);
		
		super.setTitle(titulo);
		
		super.setSize(690, 438);
				
		super.setLocationRelativeTo(null); // Centralizando a tela
		
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		super.setResizable(false);
		
		Image iconeAplicacao = new ImageIcon(PATH.concat("/imagens/logo.png")).getImage();
		
		super.setIconImage(iconeAplicacao);
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuJogo = new JMenu("Jogo");
		
		JMenu menuSobre = new JMenu("Sobre");
				
		JMenuItem itemReiniciar = new JMenuItem("Reiniciar");
		
		menuJogo.add(itemReiniciar);
				
		menuBar.add(menuJogo);
		
		menuBar.add(menuSobre);
		
		super.setJMenuBar(menuBar);
		
		super.setVisible(true);
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {		
		TelaPrincipal tela = new TelaPrincipal("CAMPO MINADO");
	}
}