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

import boards.CrazyBoard;
import boards.NormalBoard;
import players.Player;

public class MultiModesFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ImageIcon image = new ImageIcon("src/images/logo.png");
	private JButton backButton;
	private JButton crazyButton;
	private JButton classicButton;
	Scanner sc = new Scanner(System.in);
	
	public MultiModesFrame() {
		
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

        classicButton = new JButton("Classic");
        classicButton.setFont(new Font("Verdana", Font.BOLD, 20));
        classicButton.setBackground(new Color(0, 30, 0));
        classicButton.setForeground(new Color(30, 120, 30));
        classicButton.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 50), 3));
        classicButton.setMaximumSize(new Dimension(230, 100));
        classicButton.setFocusable(false);
        classicButton.addActionListener(this);

        crazyButton = new JButton("Crazy Game");
        crazyButton.setFont(new Font("Verdana", Font.BOLD, 20));
        crazyButton.setBackground(new Color(30, 0, 0));
        crazyButton.setForeground(new Color(120, 30, 30));
        crazyButton.setBorder(BorderFactory.createLineBorder(new Color(100, 50, 0), 3));
        crazyButton.setMaximumSize(new Dimension(230, 100));
        crazyButton.setFocusable(false);
        crazyButton.addActionListener(this);
        
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
        box.add(classicButton);
        box.add(Box.createVerticalStrut(10));
        box.add(crazyButton);
        box.add(Box.createVerticalStrut(10));
        box.add(backButton);
        box.add(Box.createVerticalStrut(70));
        
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
        
        classicButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                classicButton.setBackground(new Color(0, 70, 0));
                classicButton.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	classicButton.setBackground(new Color(0, 30, 0));
            	classicButton.setForeground(new Color(30, 120, 30));
            }
        });

        crazyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                crazyButton.setBackground(new Color(70, 0, 0));
                crazyButton.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	crazyButton.setBackground(new Color(30, 0, 0));
            	crazyButton.setForeground(new Color(120, 30, 30));
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
		if(e.getSource()==classicButton) {
			dispose();
            new ClassicMultiModesFrame();
		}
		if(e.getSource()==crazyButton) {
			dispose();
			new BoardMultiCrazyGui(new CrazyBoard(12,12,20,60));	
		}
		
		if(e.getSource()==backButton){
			dispose();
            new PlayersFrame();
		}
	}

}
