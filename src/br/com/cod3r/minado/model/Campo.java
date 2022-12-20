package br.com.cod3r.minado.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import br.com.cod3r.minado.excecao.ExplosaoException;

public class Campo {
	private final int linha;
	private final int coluna;
	
	private boolean aberto;
	private boolean minado;
	private boolean marcado;
	
	private List<Campo> vizinhos;
	private Set<ICampoObservador> observadores;
	
	public Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
		this.vizinhos = new ArrayList<>();
		this.observadores = new LinkedHashSet<>(); // Para notificar na ordem que foi adicionado no conjunto
	}

	public boolean adicionarVizinho(Campo campoVizinho) {
		int deltaLinha = Math.abs(this.linha - campoVizinho.linha);
		int deltaColuna = Math.abs(this.coluna - campoVizinho.coluna);
		
		int deltaGeral = deltaLinha + deltaColuna;
		
		if(deltaGeral == 1 || deltaGeral == 2) {
			this.vizinhos.add(campoVizinho);
			
			return true;
		}

		return false;
	}
	
	public void alternarMarcacao() {
		if(!this.aberto) {
			setMarcado(!this.marcado);
		}
	}
	
	public boolean abrir() {
		if(!this.aberto && !this.marcado) {
			this.aberto = true;
			
			if(this.minado) {				
				notificarObservadores(CampoEvento.EXPLODIR);
				
				throw new ExplosaoException("Voce explodiu uma bomba :( \n\nFIM DE JOGO");
			}
			
			if(vizinhancaSegura()) {
				this.vizinhos.forEach(vizinho -> vizinho.abrir()); // recursao
			}
			
			return true;
		}
		
		return false;
	}
	
	private boolean vizinhancaSegura() {
		return this.vizinhos.stream().noneMatch(vizinho -> vizinho.minado);
	}
	
	public boolean objetivoAlcancado() {
		boolean desvendado = !this.minado && this.aberto;
		
		boolean protegido = this.minado && this.marcado;
		
		return desvendado || protegido;
	}
	
	public long vizinhosComMinas() {	
		return this.vizinhos.stream().filter(vizinho -> vizinho.minado).count();
	}
	
	public void reiniciar() {
		this.aberto = false;
		this.minado = false;
		this.marcado = false;
	}
	
	public void registrarObservador(ICampoObservador observador) {
		this.observadores.add(observador);
	}
	
	private void notificarObservadores(CampoEvento eventoCampo) {
		observadores.stream().forEach(observador -> observador.dispararEvento(this, eventoCampo));
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	public boolean isMinado() {
		return minado;
	}

	public void setVizinhos(List<Campo> vizinhos) {
		this.vizinhos = vizinhos;
	}

	public boolean isAberto() {
		return aberto;
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
		
		if(aberto) {
			notificarObservadores(CampoEvento.ABRIR);
		}
	}

	public void setMinado(boolean minado) {
		this.minado = minado;
	}
	
	public List<Campo> getVizinhos() {
		return vizinhos;
	}

	public boolean isMarcado() {
		return marcado;
	}

	public void setMarcado(boolean marcado) {
		if(marcado) {
			notificarObservadores(CampoEvento.MARCAR);
		} else {
			notificarObservadores(CampoEvento.DESMARCAR);
		}
		
		this.marcado = marcado;
	}
}