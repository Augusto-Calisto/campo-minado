package br.com.cod3r.minado.model;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Campo {	
	private final int linha;
	private final int coluna;
	
	private boolean aberto;
	private boolean minado;
	private boolean marcado;
	
	private List<Campo> vizinhos;
	
	public Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
		this.vizinhos = new ArrayList<>();
	}

	public boolean adicionarVizinho(Campo campo) {
		int deltaLinha = Math.abs(this.linha - campo.linha);
		int deltaColuna = Math.abs(this.coluna - campo.coluna);
		
		int deltaGeral = deltaLinha + deltaColuna;
		
		if(deltaGeral == 1 || deltaGeral == 2) {
			vizinhos.add(campo);
			
			return true;
		}

		return false;
	}
}