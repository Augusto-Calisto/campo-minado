package br.com.cod3r.minado.excecao;

public class ExplosaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ExplosaoException(String mensagemErro) {
		super(mensagemErro);
	}
}