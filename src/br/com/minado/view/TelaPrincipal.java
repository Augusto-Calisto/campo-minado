package br.com.minado.view;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import br.com.minado.model.Dificuldade;
import br.com.minado.model.PropertiesProjeto;
import br.com.minado.model.Tabuleiro;

public class TelaPrincipal extends JFrame implements ItemListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	private JTextField txtQuantidadeDeLinhas;
	private JTextField txtQuantidadeDeColunas;
	private JTextField txtQuantidadeDeMinas;
	private JLabel lblCampoMinado;
	private JRadioButton radioDificuldadeFacil;
	private JRadioButton radioDificuldadeNormal;
	private JRadioButton radioDificuldadeDificil;
	private JRadioButton radioDificuldadePersonalizado;
	private JButton btnComecar;
	private ButtonGroup groupRadios;
	private JPanel panelOpcaoDificuldadePersonalizada;
	private JLabel lblQuantidadeDeLinhas;
	private JLabel lblQuantidadeDeColunas;
	private JLabel lblQuantidadeDeMinas;
	private JLabel lblDificuldadeJogo;
		
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			panelOpcaoDificuldadePersonalizada.setVisible(true); // Ira aparecer quando a opcao PERSONALIZADO for escolhida
			btnComecar.setEnabled(false);
        } else {
        	panelOpcaoDificuldadePersonalizada.setVisible(false);
			btnComecar.setEnabled(true);
        }
	}
	
	@Override
	public void keyTyped(KeyEvent ke) {
		char input = ke.getKeyChar();
		
		if((input < '0' || input > '9') && input != '\b') {
			ke.consume();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent ke) {
		boolean inputLinhaEstaVazio = txtQuantidadeDeLinhas.getText().isBlank();
		boolean inputColunaEstaVazio = txtQuantidadeDeColunas.getText().isBlank();
		boolean inputMinaEstaVazio = txtQuantidadeDeMinas.getText().isBlank();
				
		if(inputLinhaEstaVazio || inputColunaEstaVazio || inputMinaEstaVazio) {
			btnComecar.setEnabled(false);
		} else {
			btnComecar.setEnabled(true);
		}
	}
	
	public TelaPrincipal() {
		configuracoesIniciaisFrame();
		
		addLabelTituloJogo();
		
		addRadiosDificuldadeFrame();
		
		addBotaoIniciarJogo();
				
	    addPanelComOpcoesDificuldadePersonalizada();
	    
	    super.setVisible(true);
	}

	private void addBotaoIniciarJogo() {
		btnComecar = new JButton("INICIAR PARTIDA");
		btnComecar.setBounds(80, 310, 304, 23);
		
		btnComecar.addActionListener((e) -> {
			ButtonModel selection = groupRadios.getSelection();
			
			Enumeration<AbstractButton> elements = selection.getGroup().getElements();
			
			for(Iterator<AbstractButton> element = elements.asIterator(); elements.hasMoreElements();) {
				JRadioButton radio = (JRadioButton) element.next();
																
				if(radio.isSelected()) {
					String dificuldadeJogo = radio.getText();
															
					super.dispose();
					
					if(dificuldadeJogo.equals("PERSONALIZADO")) {
						int linhas = Integer.parseInt(txtQuantidadeDeLinhas.getText());
						
						int colunas = Integer.parseInt(txtQuantidadeDeColunas.getText());

						int minas = Integer.parseInt(txtQuantidadeDeMinas.getText());

						new TelaJogo(new Tabuleiro(linhas, colunas, minas));
						
					} else {
						Dificuldade dificuldade = Dificuldade.valueOf(dificuldadeJogo);
						
						new TelaJogo(dificuldade.getTabuleiro());
					}
					
					return ;
				}
			}
		});
		
		super.getContentPane().add(btnComecar);
	}
	
	private void configuracoesIniciaisFrame() {
		super.setSize(480, 400);
		super.setLocationRelativeTo(null); // Centralizando a tela
		super.setResizable(false);
		super.setTitle("CAMPO MINADO");
		super.setIconImage(PropertiesProjeto.getImagem("logo.png"));
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.getContentPane().setLayout(null);
	}

	private void addLabelTituloJogo() {
		lblCampoMinado = new JLabel("     CAMPO MINADO");
		lblCampoMinado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCampoMinado.setIcon(PropertiesProjeto.getIcone("logo.png"));
		lblCampoMinado.setBounds(86, -35, 264, 116);
		
		lblDificuldadeJogo = new JLabel("SELECIONE A DIFICULDADE DO JOGO:");
		lblDificuldadeJogo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDificuldadeJogo.setBounds(109, 59, 236, 14);
		
		super.getContentPane().add(lblCampoMinado);
		super.getContentPane().add(lblDificuldadeJogo);
	}

	private void addPanelComOpcoesDificuldadePersonalizada() {
		panelOpcaoDificuldadePersonalizada = new JPanel();
		panelOpcaoDificuldadePersonalizada.setVisible(false);
		panelOpcaoDificuldadePersonalizada.setBounds(135, 188, 345, 105);
		panelOpcaoDificuldadePersonalizada.setLayout(null);
		
		lblQuantidadeDeLinhas = new JLabel("Linhas:");
		lblQuantidadeDeLinhas.setBounds(10, 14, 68, 14);
		panelOpcaoDificuldadePersonalizada.add(lblQuantidadeDeLinhas);
		
		lblQuantidadeDeColunas = new JLabel("Colunas:");
		lblQuantidadeDeColunas.setBounds(10, 45, 58, 14);
		panelOpcaoDificuldadePersonalizada.add(lblQuantidadeDeColunas);
		
		lblQuantidadeDeMinas = new JLabel("Minas:");
		lblQuantidadeDeMinas.setBounds(10, 76, 68, 14);
		panelOpcaoDificuldadePersonalizada.add(lblQuantidadeDeMinas);
		
		txtQuantidadeDeLinhas = new JTextField();
		txtQuantidadeDeLinhas.setBounds(67, 11, 100, 20);
		txtQuantidadeDeLinhas.setToolTipText("QUANTIDADE DE LINHAS");
		txtQuantidadeDeLinhas.addKeyListener(this);
		txtQuantidadeDeLinhas.setColumns(10);
		panelOpcaoDificuldadePersonalizada.add(txtQuantidadeDeLinhas);
		
		txtQuantidadeDeColunas = new JTextField();
		txtQuantidadeDeColunas.setBounds(67, 42, 100, 20);
		txtQuantidadeDeColunas.setToolTipText("QUANTIDADE DE COLUNAS");
		txtQuantidadeDeColunas.addKeyListener(this);
		txtQuantidadeDeColunas.setColumns(10);
		panelOpcaoDificuldadePersonalizada.add(txtQuantidadeDeColunas);
		
		txtQuantidadeDeMinas = new JTextField();
		txtQuantidadeDeMinas.setBounds(67, 73, 100, 20);
		txtQuantidadeDeMinas.setToolTipText("QUANTIDADE DE MINAS");
		txtQuantidadeDeMinas.addKeyListener(this);
		txtQuantidadeDeMinas.setColumns(10);
		panelOpcaoDificuldadePersonalizada.add(txtQuantidadeDeMinas);
		
		super.getContentPane().add(panelOpcaoDificuldadePersonalizada);
	}
	
	private void addRadiosDificuldadeFrame() {
		radioDificuldadeFacil = new JRadioButton("FACIL");
		radioDificuldadeFacil.setSelected(true);
		radioDificuldadeFacil.setBounds(155, 80, 145, 23);
		
		radioDificuldadeNormal = new JRadioButton("NORMAL");
		radioDificuldadeNormal.setBounds(155, 106, 145, 23);
		
		radioDificuldadeDificil = new JRadioButton("DIFICIL");
		radioDificuldadeDificil.setBounds(155, 132, 145, 23);
		
		radioDificuldadePersonalizado = new JRadioButton("PERSONALIZADO");
		radioDificuldadePersonalizado.setBounds(155, 158, 145, 23);
		radioDificuldadePersonalizado.addItemListener(this);
		
		groupRadios = new ButtonGroup();
	    groupRadios.add(radioDificuldadeFacil);
	    groupRadios.add(radioDificuldadeNormal);
	    groupRadios.add(radioDificuldadeDificil);
	    groupRadios.add(radioDificuldadePersonalizado);
		
		super.getContentPane().add(radioDificuldadeFacil);
		super.getContentPane().add(radioDificuldadeNormal);
		super.getContentPane().add(radioDificuldadeDificil);
		super.getContentPane().add(radioDificuldadePersonalizado);
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {}
}