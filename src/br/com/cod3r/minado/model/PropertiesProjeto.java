package br.com.cod3r.minado.model;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class PropertiesProjeto {
	private static final String PATH = System.getProperty("user.dir");

	public static Image getImagem(String nomeImagemComExtensao) {
		return Toolkit.getDefaultToolkit().getImage(PATH.concat("/imagens/" + nomeImagemComExtensao));
	}
	
	public static ImageIcon getIcone(String nomeIconeComExtensao) {
		return new ImageIcon(PATH.concat("/imagens/" + nomeIconeComExtensao));
	}
}