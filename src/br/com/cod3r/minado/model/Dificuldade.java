package br.com.cod3r.minado.model;

public enum Dificuldade {
	FACIL {
		@Override
		public Tabuleiro getTabuleiro() {
			return new Tabuleiro(7, 7, 2);
		}
	},
	
	NORMAL {
		@Override
		public Tabuleiro getTabuleiro() {
			return new Tabuleiro(15, 15, 20);
		}
	},
	
	DIFICIL {
		@Override
		public Tabuleiro getTabuleiro() {
			return new Tabuleiro(20, 20, 28);
		}
	};
	
	public abstract Tabuleiro getTabuleiro();
}