package br.com.cod3r.minado.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CampoTest {
	private Campo campo;
	
	@BeforeEach
	void setUp() {
		campo = new Campo(2, 2);
	}
	
	@Test
	@DisplayName(value = "Retornar true se a distancia entre os campos for igual a 1")
	void retornarVerdadeiroDistanciaIgual1() {
		Campo vizinhoDireito = new Campo(2, 3);
		
		boolean isVizinho = campo.adicionarVizinho(vizinhoDireito);
		
		assertTrue(isVizinho);
	}
	
	@Test
	@DisplayName(value = "Retornar true se a distancia entre os campos for igual a 2")
	void retornarVerdadeiroDistanciaIgual2() {
		Campo vizinhoDiagonalDireita = new Campo(1, 3);
		
		boolean isVizinho = campo.adicionarVizinho(vizinhoDiagonalDireita);
		
		assertTrue(isVizinho);
	}
	
	@Test
	@DisplayName(value = "Retornar falso se a distancia entre os campos for diferente de 1 ou 2")
	void retornarFalsoSeDeltaForMaiorQueUmOuDois() {
		Campo vizinho = new Campo(1, 4);
		
		boolean isVizinho = campo.adicionarVizinho(vizinho);
		
		assertFalse(isVizinho);
	}
}