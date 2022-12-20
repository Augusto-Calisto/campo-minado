package br.com.cod3r.minado.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.cod3r.minado.excecao.ExplosaoException;

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
	
	public void abrirCampo(int linha, int coluna) {
		try {
			campos.stream()
				.filter(campo -> campo.getLinha() == linha && campo.getColuna() == coluna)
				.findFirst()
				.ifPresent(campo -> campo.abrir());
			
		} catch(ExplosaoException explosaoException) {
			campos.forEach(campo -> campo.setAberto(true));
			
			System.out.println(this.toString());
			
			throw explosaoException;
		}
	}
	
	public void marcarCampo(int linha, int coluna) {
		campos.stream()
			.filter(campo -> campo.getLinha() == linha && campo.getColuna() == coluna)
			.findFirst()
			.ifPresent(campo -> campo.alternarMarcacao());
	}
	
	private void gerarCampos() {
		// matriz 10x10  linha = 1 ate 10  ||  coluna = 1 ate 10
		
		for(int linha = 1; linha <= this.quantidadeDeLinhas; linha++) {
			for(int coluna = 1; coluna <= this.quantidadeDeColunas; coluna++) {		
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
		Random gerador = new Random();
		
		for(int i = 0; i < this.quantidadeDeMinas; i++) {
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
}