package br.com.cod3r.minado.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.cod3r.minado.excecao.ExplosaoException;

class CampoTest {
	private Campo campo;
	
	@BeforeEach
	void setUp() {
		campo = new Campo(2, 2);
	}
	
	@Test
	@DisplayName(value = "Retornar verdadeiro para vizinho direita se a distancia entre os campos for igual a 1")
	void retornarVerdadeiro_DistanciaIgual_Um_VizinhoDireita() {
		Campo vizinhoDireito = new Campo(2, 3);
		
		boolean isVizinho = campo.adicionarVizinho(vizinhoDireito);
		
		assertTrue(isVizinho);
	}
	
	@Test
	@DisplayName(value = "Retornar verdadeiro para vizinho esquerda se a distancia entre os campos for igual a 1")
	void retornarVerdadeiro_DistanciaIgual_Um_VizinhoEsquerda() {
		Campo vizinhoDireito = new Campo(2, 1);
		
		boolean isVizinho = campo.adicionarVizinho(vizinhoDireito);
		
		assertTrue(isVizinho);
	}
	
	@Test
	@DisplayName(value = "Retornar verdadeiro para vizinho diagonal direita se a distancia entre os campos for igual a 2")
	void retornarVerdadeiro_DistanciaIgual_Dois_DiagonalDireita() {
		Campo vizinhoDiagonalDireita = new Campo(1, 3);
		
		boolean isVizinho = campo.adicionarVizinho(vizinhoDiagonalDireita);
		
		assertTrue(isVizinho);
	}
	
	@Test
	@DisplayName(value = "Retornar verdadeiro para vizinho diagonal esquerda se a distancia entre os campos for igual a 2")
	void retornarVerdadeiro_DistanciaIgual_Dois_DiagonalEsquerda() {
		Campo vizinhoDiagonalEsquerda = new Campo(1, 1);
		
		boolean isVizinho = campo.adicionarVizinho(vizinhoDiagonalEsquerda);
		
		assertTrue(isVizinho);
	}
	
	@Test
	@DisplayName(value = "Retornar falso se a distancia entre o campo e o vizinho for diferente de 1 ou 2")
	void retornarFalso_Se_DeltaForMaior_UmOuDois() {
		Campo vizinho = new Campo(1, 4);
		
		boolean isVizinho = campo.adicionarVizinho(vizinho);
		
		assertFalse(isVizinho);
	}
	
	@Test
	@DisplayName(value = "Retornar verdadeiro quando estiver fechado (aberto = false), marcado = true")
	void marcadoTemQueSerVerdadeiro_QuandoEstiverFechado() {
		campo.setAberto(false);
		
		campo.alternarMarcacao();
		
		assertTrue(campo.isMarcado());
	}
	
	@Test
	@DisplayName(value = "Retornar falso quando estiver aberto, marcado = false")
	void marcadoTemQueSerFalso_QuandoEstiverAberto() {
		campo.setAberto(true);
		
		campo.alternarMarcacao();
		
		assertFalse(campo.isMarcado());
	}
	
	@Test
	@DisplayName(value = "Lancar a excecao ExplosaoException quando clicar em um campo minado")
	void lancarExplosaoException_QuandoClicarEmCampoMinado() {
		campo.setAberto(false);
		
		campo.setMarcado(false);
		
		campo.setMinado(true);
		
		assertThrows(ExplosaoException.class, () -> campo.abrir());
	}
	
	@Test
	@DisplayName(value = "Abrir o vizinho que nao tenha mina")
	void abrirVizinhoSeguro() {
		Campo vizinhoEsquerdo = new Campo(2, 3);
				
		vizinhoEsquerdo.setMinado(false);
		
		campo.adicionarVizinho(vizinhoEsquerdo);
		
		campo.abrir();
		
		assertTrue(vizinhoEsquerdo.isAberto());
	}
	
	@Test
	@DisplayName(value = "Nao pode abrir vizinho com campo minado")
	void naoPodeAbrirVizinhoComCampoMinado() {
		Campo vizinhoEsquerdo = new Campo(2, 3);
		
		Campo vizinhoDoVizinhoEsquerdo = new Campo(2, 4);	
		
		vizinhoDoVizinhoEsquerdo.setMinado(true);
		
		vizinhoEsquerdo.adicionarVizinho(vizinhoDoVizinhoEsquerdo);
		
		
		vizinhoEsquerdo.setMinado(false);
		
		campo.adicionarVizinho(vizinhoEsquerdo);
		
		
		campo.abrir();
		
		assertTrue(vizinhoEsquerdo.isAberto() && !vizinhoDoVizinhoEsquerdo.isAberto());
	}
	
	@Test
	@DisplayName(value = "Retornar verdadeiro quando clicar em um campo e expandir a vizinhanca")
	void retornarVerdadeiro_QuandoClicar_E_ExpandirVizinhanca() {
		campo.setAberto(false);
		
		campo.setMarcado(false);
		
		campo.setMinado(false);
		
		assertTrue(campo.abrir());
	}
	
	@Test
	@DisplayName(value = "Retornar falso quando campo estiver aberto ou marcado")
	void retornarFalso_QuandoEstiverAberto_E_Marcado() {
		campo.setAberto(true);
		
		campo.setMarcado(true);
				
		assertFalse(campo.abrir());
	}
}