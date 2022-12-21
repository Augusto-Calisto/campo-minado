package br.com.cod3r.minado.view;

import javax.swing.JFrame;

import br.com.cod3r.minado.model.Tabuleiro;

public class TelaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public TelaPrincipal(String titulo) {
		Tabuleiro tabuleiro = new Tabuleiro(15, 15, 20);
		
		PainelTabuleiro painelTabuleiro = new PainelTabuleiro(tabuleiro);
		
		super.add(painelTabuleiro);
		
		super.setTitle(titulo);
		
		super.setSize(690, 438);
				
		super.setLocationRelativeTo(null); // Centralizando a tela
		
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		super.setResizable(false);
		
		super.setVisible(true);
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		TelaPrincipal tela = new TelaPrincipal("CAMPO MINADO");
	}
}