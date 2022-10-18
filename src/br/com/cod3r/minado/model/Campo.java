package br.com.cod3r.minado.model;

import java.util.ArrayList;
import java.util.List;

import br.com.cod3r.minado.excecao.ExplosaoException;

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

	public boolean adicionarVizinho(Campo campoVizinho) {
		int deltaLinha = Math.abs(this.linha - campoVizinho.linha);
		int deltaColuna = Math.abs(this.coluna - campoVizinho.coluna);
		
		int deltaGeral = deltaLinha + deltaColuna;
		
		if(deltaGeral == 1 || deltaGeral == 2) {
			vizinhos.add(campoVizinho);
			
			return true;
		}

		return false;
	}
	
	public void alternarMarcacao() {
		if(!this.aberto) {
			this.marcado = !this.marcado;
		}
	}
	
	public boolean abrir() {
		if(!this.aberto && !this.marcado) {
			this.aberto = true;
			
			if(this.minado) {
				throw new ExplosaoException("Voce explodiu uma bomba :( \n\n FIM DE JOGO");
			}
			
			if(vizinhancaSegura()) {
				vizinhos.forEach(vizinho -> vizinho.abrir()); // recursao
			}
			
			return true;
		}
		
		return false;
	}
	
	private boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(vizinho -> vizinho.minado);
	}

	public boolean isAberto() {
		return aberto;
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	public boolean isMinado() {
		return minado;
	}

	public void setMinado(boolean minado) {
		this.minado = minado;
	}

	public boolean isMarcado() {
		return marcado;
	}

	public void setMarcado(boolean marcado) {
		this.marcado = marcado;
	}

	public List<Campo> getVizinhos() {
		return vizinhos;
	}

	public void setVizinhos(List<Campo> vizinhos) {
		this.vizinhos = vizinhos;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
}