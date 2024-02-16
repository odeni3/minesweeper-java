package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import boards.EasyBoard;
import boards.HardBoard;
import boards.MediumBoard;
import boards.NormalBoard;
import players.Player;

public class ClassicMultiModesFrame extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ImageIcon image = new ImageIcon("src/images/logo.png");
	private JButton hardButton;
	private JButton mediumButton;
	private JButton easyButton;
	private JButton backButton;
	
	Scanner sc = new Scanner(System.in);
	
	public ClassicMultiModesFrame() {
		
		//janela principal
        this.setSize(400, 400);
        this.setResizable(false);
        this.setTitle("zminesweeper");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(0, 0, 15));

        //janela do nome do jogo
        Border labelBorder = BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(0, 50, 100));
        JLabel label = new JLabel("MINESWEEPER");
        label.setForeground(new Color(0, 100, 175));
        label.setFont(new Font("Verdana", Font.BOLD, 33));
        label.setHorizontalAlignment(JLabel.CENTER);
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textPanel.setBackground(new Color(0, 0, 45));
        textPanel.setBorder(labelBorder);
        textPanel.add(label);

        this.add(textPanel, BorderLayout.NORTH);

        //adicionando o botão "PLAY", botão "INFO" e botão "RECORD"
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        easyButton = new JButton("Easy");
        easyButton.setFont(new Font("Verdana", Font.BOLD, 20));
        easyButton.setBackground(new Color(0, 30, 0));
        easyButton.setForeground(new Color(30, 120, 30));
        easyButton.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 50), 3));
        easyButton.setMaximumSize(new Dimension(230, 100));
        easyButton.setFocusable(false);
        easyButton.addActionListener(this);

        mediumButton = new JButton("Normal");
        mediumButton.setFont(new Font("Verdana", Font.BOLD, 20));
        mediumButton.setBackground(new Color(15, 15, 0));
        mediumButton.setForeground(new Color(120, 120, 30));
        mediumButton.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 50), 3));
        mediumButton.setMaximumSize(new Dimension(230, 100));
        mediumButton.setFocusable(false);
        mediumButton.addActionListener(this);
        
        hardButton = new JButton("Hard");
        hardButton.setFont(new Font("Verdana", Font.BOLD, 20));
        hardButton.setBackground(new Color(30, 0, 0));
        hardButton.setForeground(new Color(120, 30, 30));
        hardButton.setBorder(BorderFactory.createLineBorder(new Color(100, 50, 0), 3));
        hardButton.setMaximumSize(new Dimension(230, 100));
        hardButton.setFocusable(false);
        hardButton.addActionListener(this);
        
        backButton = new JButton("Back");
        backButton.setFont(new Font("Verdana", Font.BOLD, 20));
        backButton.setBackground(new Color(0, 0, 30));
        backButton.setForeground(new Color(30, 30, 120));
        backButton.setBorder(BorderFactory.createLineBorder(new Color(0, 50, 100), 3));
        backButton.setMaximumSize(new Dimension(230, 100));
        backButton.setFocusable(false);
        backButton.addActionListener(this);
        
        //espaçamento dos botões
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalGlue());
        box.add(easyButton);
        box.add(Box.createVerticalStrut(10));
        box.add(mediumButton);
        box.add(Box.createVerticalStrut(10));
        box.add(hardButton);
        box.add(Box.createVerticalStrut(10));
        box.add(backButton);
        box.add(Box.createVerticalStrut(50));
        
        //centralizando botões horizontalmente
        JPanel horizontalCenterPanel = new JPanel();
        horizontalCenterPanel.setLayout(new BoxLayout(horizontalCenterPanel, BoxLayout.X_AXIS));
        horizontalCenterPanel.setOpaque(false);
        horizontalCenterPanel.add(Box.createHorizontalGlue());
        horizontalCenterPanel.add(box);
        horizontalCenterPanel.add(Box.createHorizontalGlue());

        centerPanel.add(horizontalCenterPanel);

        this.add(centerPanel, BorderLayout.CENTER);
        
        //creditos
        JLabel bottomLabel = new JLabel("by odeni and mark");
        bottomLabel.setForeground(new Color(0, 100, 175));
        bottomLabel.setFont(new Font("Verdana", Font.BOLD, 10));
        bottomLabel.setHorizontalAlignment(JLabel.CENTER);
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(new Color(0, 0, 15));
        bottomPanel.add(bottomLabel);

        this.add(bottomPanel, BorderLayout.SOUTH);

        // Adicionando borda ao redor de toda a janela
        Border frameBorder = BorderFactory.createLineBorder(new Color(0, 50, 100), 4);
        this.getRootPane().setBorder(frameBorder);
        
        easyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	easyButton.setBackground(new Color(0, 70, 0));
                easyButton.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	easyButton.setBackground(new Color(0, 30, 0));
            	easyButton.setForeground(new Color(30, 120, 30));
            }
        });
        
        mediumButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mediumButton.setBackground(new Color(70, 70, 0));
                mediumButton.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                mediumButton.setBackground(new Color(30, 30, 0));
                mediumButton.setForeground(new Color(120, 120, 30));
            }
        });

        hardButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	hardButton.setBackground(new Color(70, 0, 0));
            	hardButton.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	hardButton.setBackground(new Color(30, 0, 0));
            	hardButton.setForeground(new Color(120, 30, 30));
            }
        });

        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(0, 0, 70));
                backButton.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(0, 0, 30));
                backButton.setForeground(new Color(30, 30, 120));
            }
        });
        
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
	
	//Adicionando método para escolher o tipo da dificuldade do tabuleiro no modo normal
	
	private static NormalBoard selecionarTabuleiro(int escolha) {
        switch (escolha) {
            case 0:
                return new EasyBoard(6, 6, 6);
            case 1:
                return new MediumBoard(12, 12, 15); 
            case 2:
                return new HardBoard(18, 18, 30); 
            default:
                System.out.println("Opção inválida. Encerrando o jogo.");
                System.exit(0);
                return null; 
        }
    }
	
	//definindo método para criar turno de cada jogador
    
    @SuppressWarnings("unused")
	private static void playerTurn(NormalBoard tabMain, Scanner sc, Player player) {
        System.out.println("[ Vez de " + player.getName()+" ]");
        System.out.println();
        System.out.println("Digite a linha:");
        int i = sc.nextInt();
        System.out.println("Digite a coluna:");
        int j = sc.nextInt();
        
        int k;
        do {
            System.out.println("Digite: 0 (REVELAR CASA) -> 1 (ADD BANDEIRA) -> 2 (RETIRAR BANDEIRA):");
            k = sc.nextInt();
            if (k < 0 || k > 2) {
            	System.out.println();
                System.out.println("Comando inválido. Digite novamente.");
                System.out.println();
            }
        } while (k < 0 || k > 2);

        System.out.println();

        tabMain.userSelect(i, j, k, player);
        tabMain.calculateScore(player);
        System.out.println(tabMain);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==easyButton) {
			dispose();
			new BoardMultiNormalGui(selecionarTabuleiro(0));
		}
		
		if(e.getSource()==mediumButton) {
			dispose();
			new BoardMultiNormalGui(selecionarTabuleiro(1));
		}
		
		if(e.getSource()==hardButton){
			dispose();
			new BoardMultiNormalGui(selecionarTabuleiro(2));
		}
		if(e.getSource()==backButton){
			dispose();
            new MultiModesFrame();
		}
	}

}
