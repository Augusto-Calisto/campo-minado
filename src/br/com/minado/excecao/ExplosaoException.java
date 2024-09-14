package br.com.minado.excecao;

public class ExplosaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ExplosaoException() {}
	
	public ExplosaoException(String mensagemErro) {
		super(mensagemErro);
	}
}