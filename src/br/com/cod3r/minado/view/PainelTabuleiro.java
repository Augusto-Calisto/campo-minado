package br.com.cod3r.minado.view;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.cod3r.minado.model.Tabuleiro;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel {
		
	public PainelTabuleiro(Tabuleiro tabuleiro) {
		int quantidadeDeLinhas = tabuleiro.getQuantidadeDeLinhas();
		
		int quantidadeDeColunas = tabuleiro.getQuantidadeDeColunas();
		
		GridLayout layout = new GridLayout(quantidadeDeLinhas, quantidadeDeColunas);
				
		super.setLayout(layout);
		
		tabuleiro.adicionarCampoNoBotao(campo -> super.add(new BotaoCampo(campo)));
		
		tabuleiro.registrarObservadores(ganhouPartida -> {
			SwingUtilities.invokeLater(() -> {
				if(ganhouPartida) {
					JOptionPane.showMessageDialog(this, "PARABENS, VOCE GANHOU", "CAMPO MINADO", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "FIM DE JOGO, VOCE PERDEU", "CAMPO MINADO", JOptionPane.ERROR_MESSAGE);
				}
				
				reiniciarTabuleiro(tabuleiro);
			});
		});
	}
	
	private void reiniciarTabuleiro(Tabuleiro tabuleiro) {
		int confirmacao = JOptionPane.showConfirmDialog(this, "DESEJA JOGAR NOVAMENTE?", "CAMPO MINADO", JOptionPane.YES_NO_OPTION);
		
		if(confirmacao == JOptionPane.YES_OPTION) {
			tabuleiro.reiniciarJogo();			
		} else {
			System.exit(0);
		}
	}
}