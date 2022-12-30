package br.com.minado.model;

@FunctionalInterface
public interface ICampoObservador {
	public void dispararEvento(Campo campo, CampoEvento eventoCampo);
}
