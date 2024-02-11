package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class PlayersFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ImageIcon image = new ImageIcon("C:\\Users\\User\\git\\repository\\zminesweeper\\src\\images\\logo.png");
	private JButton backButton;
	private JButton multiplayerButton;
	private JButton singlePlayerButton;
	
	public PlayersFrame() {
		
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

        singlePlayerButton = new JButton("Singleplayer");
        singlePlayerButton.setFont(new Font("Verdana", Font.BOLD, 20));
        singlePlayerButton.setBackground(new Color(0, 30, 0));
        singlePlayerButton.setForeground(new Color(30, 120, 30));
        singlePlayerButton.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 50), 3));
        singlePlayerButton.setMaximumSize(new Dimension(230, 100));
        singlePlayerButton.setFocusable(false);
        singlePlayerButton.addActionListener(this);

        multiplayerButton = new JButton("Multiplayer");
        multiplayerButton.setFont(new Font("Verdana", Font.BOLD, 20));
        multiplayerButton.setBackground(new Color(30, 0, 0));
        multiplayerButton.setForeground(new Color(120, 30, 30));
        multiplayerButton.setBorder(BorderFactory.createLineBorder(new Color(100, 50, 0), 3));
        multiplayerButton.setMaximumSize(new Dimension(230, 100));
        multiplayerButton.setFocusable(false);
        multiplayerButton.addActionListener(this);
        
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
        box.add(singlePlayerButton);
        box.add(Box.createVerticalStrut(10));
        box.add(multiplayerButton);
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
        
        singlePlayerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                singlePlayerButton.setBackground(new Color(0, 70, 0));
                singlePlayerButton.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                singlePlayerButton.setBackground(new Color(0, 30, 0));
                singlePlayerButton.setForeground(new Color(30, 120, 30));
            }
        });

        multiplayerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                multiplayerButton.setBackground(new Color(70, 0, 0));
                multiplayerButton.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                multiplayerButton.setBackground(new Color(30, 0, 0));
                multiplayerButton.setForeground(new Color(120, 30, 30));
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
	
	
	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==singlePlayerButton) {
			dispose();
			SingleModesFrame single = new SingleModesFrame();
		}
		if(e.getSource()==multiplayerButton) {
			dispose();
			MultiModesFrame multi = new MultiModesFrame();
		}
		if(e.getSource()==backButton){
			dispose();
            new MainFrame();
		}
	}

}
