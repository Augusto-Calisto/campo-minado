package br.com.cod3r.minado;

import javax.swing.JOptionPane;

import br.com.cod3r.minado.model.Tabuleiro;

public class RunApp {

	public static void main(String[] args) {
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 3);
		
		tabuleiro.registrarObservadores(ganhouPartida -> {
			if(ganhouPartida) {
				JOptionPane.showMessageDialog(null, "PARABENS, VOCE GANHOU", "CAMPO MINADO", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "FIM DE JOGO", "CAMPO MINADO", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		tabuleiro.abrirCampo(1, 3);
		
		tabuleiro.marcarCampo(4, 3);
		
		tabuleiro.marcarCampo(5, 1);
	}
}