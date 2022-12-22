package br.com.cod3r.minado.excecao;

@SuppressWarnings("serial")
public class ExplosaoException extends RuntimeException {
	
	public ExplosaoException() {}
	
	public ExplosaoException(String mensagemErro) {
		super(mensagemErro);
	}
}