package br.com.cod3r.minado.view;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
			if(ganhouPartida) {
				JOptionPane.showMessageDialog(null, "PARABENS, VOCE GANHOU", "CAMPO MINADO", JOptionPane.INFORMATION_MESSAGE);
			} else {
				removerMouseListenerGameOver();
				
				JOptionPane.showMessageDialog(null, "FIM DE JOGO, VOCE PERDEU", "CAMPO MINADO", JOptionPane.ERROR_MESSAGE);
				
				reiniciarTabuleiro();
			}
		});
	}
	
	private void reiniciarTabuleiro() {
		int confirmacao = JOptionPane.showConfirmDialog(null, "DESEJA JOGAR NOVAMENTE?", "CAMPO MINADO", JOptionPane.YES_NO_OPTION);
		
		if(confirmacao == JOptionPane.YES_OPTION) {
			System.out.println("Reiniciando...");
		} else {
			System.exit(0);
		}
	}

	private void removerMouseListenerGameOver() {
		Component[] components = super.getComponents();
		
		for(int i = 0; i < components.length; i++) {
			BotaoCampo botao = (BotaoCampo) components[i];
								
			botao.removeMouseListener(botao);
		}
	}
}