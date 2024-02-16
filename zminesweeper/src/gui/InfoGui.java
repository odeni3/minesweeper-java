package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class InfoGui extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private ImageIcon infoImage = new ImageIcon("src/images/info.png");
    private ImageIcon appIcon = new ImageIcon("src/images/logo.png");
    private JButton backButton;

    public InfoGui() {
        this.setSize(400, 435);
        this.setResizable(false);

        JLabel imageLabel = new JLabel(infoImage);
        this.add(imageLabel);
        this.setTitle("zminesweeper");
        this.setIconImage(appIcon.getImage());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        createAdditionalWindow();
    }

    private void createAdditionalWindow() {
        JDialog additionalWindow = new JDialog(this, "Additional Window", false);
        additionalWindow.setSize(80, 50);
        additionalWindow.setResizable(false);
        additionalWindow.getContentPane().setBackground(new Color(240, 240, 240));

        //adicionando botão "Back" na janela adicional
        backButton = new JButton("Back");
        backButton.setFont(new Font("Verdana", Font.BOLD, 20));
        backButton.setBackground(new Color(0, 0, 40));
        backButton.setForeground(new Color(0, 203, 255));
        backButton.setBorder(BorderFactory.createLineBorder(new Color(0, 203, 255), 3));
        backButton.setMaximumSize(new Dimension(230, 100));
        backButton.setFocusable(false);
        backButton.addActionListener(this);
        
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(0, 0, 100));
                backButton.setForeground(new Color(255, 255, 255));
                backButton.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 255), 3));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(0, 0, 40));
                backButton.setForeground(new Color(0, 203, 255));
                backButton.setBorder(BorderFactory.createLineBorder(new Color(0, 203, 255), 3));
            }
        });

        additionalWindow.add(backButton, BorderLayout.CENTER);

        //posicionando a janela abaixo da janela principal
        int mainFrameX = this.getLocation().x;
        int mainFrameY = this.getLocation().y;
        int mainFrameHeight = this.getHeight();

        int dialogX = mainFrameX;
        int dialogY = mainFrameY + mainFrameHeight;
        additionalWindow.setLocation(dialogX + 160, dialogY-7);
        additionalWindow.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        additionalWindow.setUndecorated(true);
        additionalWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource()==backButton) {
			dispose();
            new MainFrame();
		}
    }
}
