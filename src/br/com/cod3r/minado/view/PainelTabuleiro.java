package br.com.cod3r.minado.view;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.com.cod3r.minado.model.Tabuleiro;

public class PainelTabuleiro extends JPanel {
	private static final long serialVersionUID = 1L;
			
	public PainelTabuleiro(Tabuleiro tabuleiro) {
		int quantidadeDeLinhas = tabuleiro.getQuantidadeDeLinhas();
		
		int quantidadeDeColunas = tabuleiro.getQuantidadeDeColunas();
		
		GridLayout layout = new GridLayout(quantidadeDeLinhas, quantidadeDeColunas);
				
		super.setLayout(layout);
		
		tabuleiro.adicionarCampoNoBotao(campo -> super.add(new BotaoCampo(campo)));
		
		tabuleiro.registrarObservadores(ganhouPartida -> {
			if(ganhouPartida) {
				JOptionPane.showMessageDialog(null, "PARABENS, VOCE GANHOU :)", "CAMPO MINADO", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "VOCE PERDEU, FIM DE JOGO :(", "CAMPO MINADO", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
}