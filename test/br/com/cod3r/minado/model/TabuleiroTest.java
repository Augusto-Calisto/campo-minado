package br.com.cod3r.minado.model;

import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class TabuleiroTest {
	
	@Mock
	private Tabuleiro tabuleiro;
	
	@BeforeEach
	public void setUp() {
		tabuleiro = new Tabuleiro(7, 7, 3);
	}

	@Test
	@DisplayName(value = "Abrir campo do tabuleiro linha e coluna")
	void abrirCampoDoTabuleiroPorLinhaEColuna() {
		
								
		assertAll(() -> tabuleiro.abrirCampo(1, 9));
	}
}