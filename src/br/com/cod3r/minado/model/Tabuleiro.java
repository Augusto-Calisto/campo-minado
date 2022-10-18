package br.com.cod3r.minado.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tabuleiro {
	private static List<Campo> campos = new ArrayList<>();

	private int quantidadeDeLinhas;
	private int quantidadeDeColunas;
	private int quantidadeDeMinas;
	
	public Tabuleiro(int quantidadeDeLinhas, int quantidadeDeColunas, int quantidadeDeMinas) {
		this.quantidadeDeLinhas = quantidadeDeLinhas;
		
		this.quantidadeDeColunas = quantidadeDeColunas;
		
		this.quantidadeDeMinas = quantidadeDeMinas;
	
		gerarCampos();
		
		criarOsVizinhosDoCampo();
		
		sortearMinas();
	}
	
	private void gerarCampos() {
		// matriz 10x10  linha = 1 ate 10  ||  coluna = 1 ate 10
		
		for(int linha = 1; linha <= quantidadeDeLinhas; linha++) {			
			for(int coluna = 1; coluna <= quantidadeDeColunas; coluna++) {				
				campos.add(new Campo(linha, coluna));
			}			
		}
	}

	private void criarOsVizinhosDoCampo() {
		for(Campo campo : campos) {
			for(Campo vizinho : campos) {
				campo.adicionarVizinho(vizinho);
			}
		}
	}
	
	private void sortearMinas() {
		int quantidadeDeMinasParaArmar = (this.quantidadeDeLinhas * this.quantidadeDeColunas) / 4;  // 	1/4 do tabuleiro
		
		Random gerador = new Random();
		
		for(int i = 0; i < quantidadeDeMinasParaArmar; i++) {
			int indiceAleatorio = gerador.ints(0, campos.size() - 1).findFirst().getAsInt();
					
			campos.get(indiceAleatorio).setMinado(true);
		}
	}
	
	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(campo -> campo.objetivoAlcancado());
	}
	
	public void reiniciarJogo() {
		campos.stream().forEach(campo -> campo.reiniciar());
		sortearMinas();
	}

	@Override
	public String toString() {
		return "Tabuleiro [quantidadeDeLinhas=" + quantidadeDeLinhas + ", quantidadeDeColunas=" + quantidadeDeColunas + ", quantidadeDeMinas=" + quantidadeDeMinas + "]";
	}
}