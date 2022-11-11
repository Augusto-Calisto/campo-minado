package br.com.cod3r.minado;

import br.com.cod3r.minado.model.Tabuleiro;

public class RunApp {

	public static void main(String[] args) {
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 3);
		
		tabuleiro.abrirCampo(1, 3);
		
		tabuleiro.marcarCampo(4, 3);
		
		tabuleiro.marcarCampo(5, 1);
		
		System.out.println(tabuleiro);
	}
}