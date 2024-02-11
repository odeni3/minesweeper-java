package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MainFrame extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ImageIcon image = new ImageIcon("C:\\Users\\User\\git\\repository\\zminesweeper\\src\\images\\logo.png");
	private JButton recordButton;
	private JButton infoButton;
	private JButton playButton;
	
	public MainFrame() {
		
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

        playButton = new JButton("Play");
        playButton.setFont(new Font("Verdana", Font.BOLD, 20));
        playButton.setBackground(new Color(0, 0, 30));
        playButton.setForeground(new Color(30, 30, 120));
        playButton.setBorder(BorderFactory.createLineBorder(new Color(0, 50, 100), 3));
        playButton.setMaximumSize(new Dimension(230, 100));
        playButton.setFocusable(false);
        playButton.addActionListener(this);

        infoButton = new JButton("Info");
        infoButton.setFont(new Font("Verdana", Font.BOLD, 20));
        infoButton.setBackground(new Color(0, 0, 30));
        infoButton.setForeground(new Color(30, 30, 120));
        infoButton.setBorder(BorderFactory.createLineBorder(new Color(0, 50, 100), 3));
        infoButton.setMaximumSize(new Dimension(230, 100));
        infoButton.setFocusable(false);
        
        recordButton = new JButton("Leaderboard");
        recordButton.setFont(new Font("Verdana", Font.BOLD, 20));
        recordButton.setBackground(new Color(0, 0, 30));
        recordButton.setForeground(new Color(30, 30, 120));
        recordButton.setBorder(BorderFactory.createLineBorder(new Color(0, 50, 100), 3));
        recordButton.setMaximumSize(new Dimension(230, 100));
        recordButton.setFocusable(false);
        
        //espaçamento dos botões
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalGlue());
        box.add(playButton);
        box.add(Box.createVerticalStrut(10));
        box.add(infoButton);
        box.add(Box.createVerticalStrut(10));
        box.add(recordButton);
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

        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
	
	public static void main(String[] args) {
        
		//criando uma instância de LoadingGui
        LoadingGui loadingGui = new LoadingGui();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                loadingGui.dispose();
                new MainFrame();
            }
        }, 4000);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==playButton) {
			dispose();
            new PlayersFrame();
		}
		/*if(e.getSource()==infoButton) {
			
		}
		if(e.getSource()==recordButton){
			
		}*/
	}

}
