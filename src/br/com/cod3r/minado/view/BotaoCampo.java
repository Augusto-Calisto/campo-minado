package br.com.cod3r.minado.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import br.com.cod3r.minado.model.Campo;
import br.com.cod3r.minado.model.CampoEvento;
import br.com.cod3r.minado.model.ICampoObservador;

@SuppressWarnings({"serial", "unused"})
public class BotaoCampo extends JButton implements ICampoObservador, MouseListener {
	private final Color BG_PADRAO = new Color(184, 184, 184);
	private final Color BG_MARCAR = new Color(8, 179, 247);
	private final Color BG_EXPLODIR = new Color(189, 66, 68);

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
		if(e.getButton() == 1) { // MOUSE ESQUERDO
			campo.abrir();
			
		} else if(e.getButton() == 3) { // MOUSE DIREITO
			campo.alternarMarcacao();
		}
	}

	@Override
	public void dispararEvento(Campo campo, CampoEvento eventoCampo) {
		System.out.println(eventoCampo);
		
		switch(eventoCampo) {
			case ABRIR:
				aplicarEstiloAbrirCampo();
			break;
			
			case MARCAR:
				aplicarEstiloMarcarCampo();
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
		setBackground(BG_PADRAO);
		
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		int vizinhosComMinas = (int) campo.vizinhosComMinas();
		
		setForeground(Color.GREEN);
		
		/*switch(vizinhosComMinas) {
			case 1:
				setForeground(Color.GREEN);
			break;
			
			case 2:
				setForeground(Color.BLUE);
			break;
			
			case 3:
				setForeground(Color.YELLOW);
			break;
			
			case 4:
			case 5:
			case 6:
				setForeground(Color.RED);
			break;
	
			default:
				setForeground(Color.PINK);
			break;
		}*/
		
		if(campo.vizinhancaSegura()) {
			String texto = String.valueOf(vizinhosComMinas);
			setText(texto);
		} else {
			setText("");
		}
	}

	private void aplicarEstiloMarcarCampo() {
		
	}

	private void aplicarEstiloDesmarcarCampo() {
		
	}
	
	private void aplicarEstiloPadrao() {
		
	}
	
	private void aplicarEstiloExplodir() {
		
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