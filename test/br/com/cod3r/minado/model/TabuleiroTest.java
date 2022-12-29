package br.com.cod3r.minado.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TabuleiroTest {
	private Tabuleiro tabuleiro;
	
	@BeforeEach
	public void setUp() {		
		tabuleiro = new Tabuleiro(7, 7, 10);
	}

	@Test
	@DisplayName(value = "Abrir campo do tabuleiro linha e coluna")
	void abrirCampoDoTabuleiroPorLinhaEColuna() {
		
	}
}