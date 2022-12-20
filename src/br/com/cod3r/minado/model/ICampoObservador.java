package br.com.cod3r.minado.model;

@FunctionalInterface
public interface ICampoObservador {
	public void dispararEvento(Campo campo, CampoEvento eventoCampo);
}
