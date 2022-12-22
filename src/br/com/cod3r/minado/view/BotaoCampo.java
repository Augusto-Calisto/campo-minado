package br.com.cod3r.minado.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import br.com.cod3r.minado.TelaPrincipal;
import br.com.cod3r.minado.excecao.ExplosaoException;
import br.com.cod3r.minado.model.Campo;
import br.com.cod3r.minado.model.CampoEvento;
import br.com.cod3r.minado.model.ICampoObservador;

@SuppressWarnings({"serial", "unused"})
public class BotaoCampo extends JButton implements ICampoObservador, MouseListener {
	private final Color BG_PADRAO = new Color(184, 184, 184);

	private Campo campo;
	
	public BotaoCampo(Campo campo) {
		this.campo = campo;
		super.setBorder(BorderFactory.createBevelBorder(0));
		super.setBackground(BG_PADRAO);
		super.addMouseListener(this);
		campo.registrarObservador(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {		
		try {
			if(e.getButton() == 1) { // MOUSE ESQUERDO				
				System.out.println(campo);
				
				System.out.println("QUAIS SAO OS VIZINHOS");
				
				campo.abrir();
				
			} else if(e.getButton() == 3) { // MOUSE DIREITO
				campo.alternarMarcacao();
			}
		} catch(ExplosaoException explosao) {
			System.out.println("Erro -> ExplosaoException");
		}
	}

	@Override
	public void dispararEvento(Campo campo, CampoEvento eventoCampo) {				
		switch(eventoCampo) {
			case ABRIR:
				aplicarEstiloAbrirCampo();
			break;
			
			case MARCAR:
				aplicarEstiloMarcarCampo();
			break;
			
			case DESMARCAR:
				aplicarEstiloDesmarcarCampo();
			break;
			
			case EXPLODIR:
				aplicarEstiloExplodir();
			break;

			default:
				aplicarEstiloPadrao();
			break;
		}
	}
	
	private void aplicarEstiloAbrirCampo() {
		if(campo.isAberto() && campo.isMinado()) {
			aplicarEstiloExplodir();
		} else {
			setBackground(BG_PADRAO);
			
			setBorder(BorderFactory.createLineBorder(Color.GRAY));
			
			int vizinhosComMinas = (int) campo.vizinhosComMinas();
										
			switch(vizinhosComMinas) {
				case 1:
					setForeground(new Color(0, 149, 85)); // VERDE
				break;
				
				case 2:
					setForeground(Color.BLUE);
				break;
				
				case 3:
					setForeground(new Color(63, 19, 115)); // ROXO
				break;
				
				case 4:
				case 5:
				case 6:
					setForeground(Color.RED);
				break;
		
				default:
					setForeground(Color.BLACK);
				break;
			}
			
			if(!campo.vizinhancaSegura()) {
				String texto = String.valueOf(vizinhosComMinas);
				super.setText(texto);
			} else {
				super.setText("");
			}
		}
	}

	private void aplicarEstiloMarcarCampo() {		
		super.setIcon(new ImageIcon(TelaPrincipal.PATH.concat("/imagens/flag.png")));
	}

	private void aplicarEstiloDesmarcarCampo() {
		super.setIcon(null);
	}
	
	private void aplicarEstiloExplodir() {
		super.setBackground(BG_PADRAO);

		super.setBorder(BorderFactory.createLineBorder(Color.GRAY));
					
		super.setIcon(new ImageIcon(TelaPrincipal.PATH.concat("/imagens/mina.jpg")));
	}
	
	private void aplicarEstiloPadrao() {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}