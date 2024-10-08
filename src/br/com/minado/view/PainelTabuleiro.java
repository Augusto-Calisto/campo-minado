package br.com.minado.view;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.minado.model.PropertiesProjeto;
import br.com.minado.model.Tabuleiro;

public class PainelTabuleiro extends JPanel {
	private static final long serialVersionUID = 1L;

	public PainelTabuleiro(Tabuleiro tabuleiro) {
		int quantidadeDeLinhas = tabuleiro.getQuantidadeDeLinhas();
		
		int quantidadeDeColunas = tabuleiro.getQuantidadeDeColunas();
	
		GridLayout layout = new GridLayout(quantidadeDeLinhas, quantidadeDeColunas);
				
		super.setLayout(layout);
		
		tabuleiro.adicionarCampoNoBotao(campo -> super.add(new BotaoCampo(campo)));
		
		tabuleiro.registrarObservadores(ganhouPartida -> {
			SwingUtilities.invokeLater(() -> {
				if(ganhouPartida) {
					JOptionPane.showMessageDialog(this, "PARABENS, VOCE GANHOU", "CAMPO MINADO", JOptionPane.INFORMATION_MESSAGE, PropertiesProjeto.getIcone("logo.png"));
				} else {
					JOptionPane.showMessageDialog(this, "FIM DE JOGO, VOCE PERDEU", "CAMPO MINADO", JOptionPane.ERROR_MESSAGE, PropertiesProjeto.getIcone("logo.png"));
				}
				
				reiniciarJogo(tabuleiro);
			});
		});
	}
	
	private void reiniciarJogo(Tabuleiro tabuleiro) {
		int confirmacao = JOptionPane.showConfirmDialog(this, "DESEJA JOGAR NOVAMENTE?", "CAMPO MINADO", JOptionPane.YES_NO_OPTION);
				
		if(confirmacao == JOptionPane.YES_OPTION) {
			tabuleiro.reiniciarJogo();			
		} else {			
			System.exit(0);
		}
	}
}