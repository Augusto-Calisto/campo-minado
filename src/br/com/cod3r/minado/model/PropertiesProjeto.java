package br.com.cod3r.minado.model;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;

public class PropertiesProjeto {
	public static Image getImagem(String nomeImagemComExtensao) {
		URL resource = PropertiesProjeto.class.getClassLoader().getResource(nomeImagemComExtensao);
		return Toolkit.getDefaultToolkit().getImage(resource);
	}
	
	public static ImageIcon getIcone(String nomeIconeComExtensao) {
		URL resource = PropertiesProjeto.class.getClassLoader().getResource(nomeIconeComExtensao);
		return new ImageIcon(resource);
	}
}