package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LoadingGui extends JFrame {
	
    private static final long serialVersionUID = 1L;
    private ImageIcon imagee = new ImageIcon("src/images/ziggs.png");
    private ImageIcon image = new ImageIcon("src/images/logo.png");
    
    public LoadingGui() {
        this.setSize(390, 565);
        this.setResizable(false);

        JLabel imageLabel = new JLabel(imagee);
        this.add(imageLabel);
        this.setIconImage(image.getImage());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setVisible(true);
    }
}
