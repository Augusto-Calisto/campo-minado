package br.com.minado.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.minado.excecao.ExplosaoException;

class CampoTest {
	private Campo campo;
	
	@BeforeEach
	void setUp() {
		campo = new Campo(2, 2);
	}
	
	@Test
	@DisplayName(value = "Retornar verdadeiro para vizinho direito quando a distancia entre os campos for igual 1")
	void retornarVerdadeiro_DistanciaIgual_Um_VizinhoDireita() {
		Campo vizinhoDireito = new Campo(2, 3);
		
		boolean isVizinho = campo.adicionarVizinho(vizinhoDireito);
		
		assertTrue(isVizinho);
	}
	
	@Test
	@DisplayName(value = "Retornar verdadeiro para vizinho esquerdo quando a distancia entre os campos for igual 1")
	void retornarVerdadeiro_DistanciaIgual_Um_VizinhoEsquerda() {
		Campo vizinhoEsquerdo = new Campo(2, 1);
		
		boolean isVizinho = campo.adicionarVizinho(vizinhoEsquerdo);
		
		assertTrue(isVizinho);
	}
	
	@Test
	@DisplayName(value = "Retornar falso se a distancia entre o campo e o vizinho for maior que 1")
	void retornarFalso_Se_DeltaForMaior_Um() {
		Campo campoDistante = new Campo(1, 3);
		
		boolean isVizinho = campo.adicionarVizinho(campoDistante);
		
		assertFalse(isVizinho);
	}
	
	@Test
	@DisplayName(value = "Retornar verdadeiro quando marcar um campo fechado")
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
	@DisplayName(value = "Abrir vizinho que nao tenha mina")
	void abrirVizinhoSeguro() {
		Campo vizinhoDireito = new Campo(2, 3);
				
		vizinhoDireito.setMinado(false);
		
		campo.adicionarVizinho(vizinhoDireito);
		
		campo.abrir();
		
		assertTrue(vizinhoDireito.isAberto());
	}
	
	@Test
	@DisplayName(value = "Nao expandir vizinho com campo minado")
	void naoPodeAbrirVizinhoComCampoMinado() {
		Campo vizinhoDireito = new Campo(2, 3);
		
		Campo vizinhoDoVizinhoDireito = new Campo(2, 4);	
		
		vizinhoDoVizinhoDireito.setMinado(true);
		
		vizinhoDireito.adicionarVizinho(vizinhoDoVizinhoDireito);
		
		
		vizinhoDireito.setMinado(false);
		
		campo.adicionarVizinho(vizinhoDireito);
		
		
		campo.abrir();
		
		assertTrue(vizinhoDireito.isAberto() && !vizinhoDoVizinhoDireito.isAberto());
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
	
	@Test
	@DisplayName(value = "Verificar quantidade vizinhos com minas")
	void verificando_QuantidadeDeVizinhosComMinas() {
		Campo vizinhoDeCima = new Campo(1, 2);
		vizinhoDeCima.setMinado(false);
		
		Campo vizinhoDireito = new Campo(2, 3);
		vizinhoDireito.setMinado(false);
		
		Campo vizinhoDeBaixo = new Campo(3, 2);
		vizinhoDeBaixo.setMinado(true);
		
		
		campo.adicionarVizinho(vizinhoDeCima);
		
		campo.adicionarVizinho(vizinhoDireito);

		campo.adicionarVizinho(vizinhoDeBaixo);
		
		
		assertEquals(1L, campo.vizinhosComMinas());
	}
	
	@Test
	@DisplayName(value = "Retornar verdadeiro quando o campo nao minado estiver aberto")
	void retornarVerdadeiro_QuandoDesvendarTodosOsCampos() {
		campo.setAberto(true);
		
		campo.setMinado(false);
		
		assertTrue(campo.objetivoAlcancado());
	}
	
	@Test
	@DisplayName(value = "Retornar verdadeiro quando todos os campos minados estiverem marcados")
	void retornarVerdadeiro_QuandoTodosOsCamposMinadosEstiveremMarcados() {
		campo.setAberto(false);
		
		campo.setMinado(true);
		
		campo.setMarcado(true);
		
		assertTrue(campo.objetivoAlcancado());
	}
	
	@Test
	@DisplayName(value = "Retornar falso quando os campos minados nao forem protegidos")
	void retornarFalso_QuandoOsCamposNaoEstiveremProtegidos() {
		campo.setAberto(false);
		
		campo.setMinado(true);
		
		campo.setMarcado(false);
		
		assertFalse(campo.objetivoAlcancado());
	}
	
	@Test
	@DisplayName(value = "Retornar verdadeiro quando reiniciar o jogo, resetar os atributos do campo")
	void retornarVerdadeiro_ResetarAtributosCampo() {
		campo.reiniciar();
		
		boolean campoFechado = !campo.isAberto();
		
		boolean campoDesmarcado = !campo.isMarcado();
		
		boolean campoNaoMinado = !campo.isMinado();
		
		assertTrue(campoFechado && campoDesmarcado && campoNaoMinado);
	}
}