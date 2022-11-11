package br.com.cod3r.minado.model;

import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TabuleiroTest {

	@Test
	@DisplayName(value = "Abrir campo do tabuleiro linha e coluna")
	void abrirCampoDoTabuleiroPorLinhaEColuna() {
		Tabuleiro tabuleiro = new Tabuleiro(7, 7, 3);
								
		assertAll(() -> tabuleiro.abrirCampo(1, 9));
	}
}