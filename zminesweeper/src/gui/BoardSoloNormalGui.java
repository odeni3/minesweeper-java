package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import boards.NormalBoard;
import elements.Bomb;
import elements.EmptySpace;
import elements.Neighborhood;
import leaderboard.RecordManager;
import players.Player;

public class BoardSoloNormalGui extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    
    private ImageIcon image = new ImageIcon("C:\\Users\\User\\git\\repository\\zminesweeper\\src\\images\\logo.png");
    private ImageIcon bombImage = new ImageIcon("C:\\Users\\User\\git\\repository\\zminesweeper\\src\\images\\bomb.png");
    private ImageIcon flagImage = new ImageIcon("C:\\Users\\User\\git\\repository\\zminesweeper\\src\\images\\flag.png");
    private Player player1;
    private JButton[][] button;
    private NormalBoard board;
    private JLabel infoLabel1;
    private RecordManager recordManager;
    private int numBombsFlagged = 0;
    private int totalBombs;

    public BoardSoloNormalGui(NormalBoard board) {
        this.board = board;
        
        //exibir diálogo de entrada personalizado
        String playerName = showCustomInputDialog();
        if (playerName == null || playerName.isEmpty()) {
            playerName = "";
        }
        
        //inicializando jogador
        player1 = new Player(playerName,0);
        
        //inicializando recorde
        recordManager = RecordManager.getInstance();
        recordManager.addPlayer(player1);
        
        this.setSize(500, 500);
        this.setResizable(false);
        this.setTitle("zminesweeper");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(0, 0, 15));

        //inicializando array de botões
        button = new JButton[board.getLine()][board.getColumn()];

        //definindo o GridLayout
        this.setLayout(new GridLayout(board.getLine(), board.getColumn()));
        
        totalBombs = board.getNumBomb();
        
        for (int i = 0; i < board.getLine(); i++) {
            for (int j = 0; j < board.getColumn(); j++) {
                button[i][j] = new JButton();
                button[i][j].setFocusable(false);
                button[i][j].setFont(new Font("Verdana", Font.BOLD, 20));
                button[i][j].setBackground(new Color(0, 0, 40));
                button[i][j].setForeground(new Color(0, 0, 250));
                button[i][j].setBorder(BorderFactory.createLineBorder(new Color(0, 50, 100), 3));
                button[i][j].setLayout(new BorderLayout());

                this.add(button[i][j]);

                final int row = i;
                final int col = j;

                //definindo método ao clicar com o botão esquerdo
                button[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //se for um clique esquerdo, faça a lógica existente
                        JButton clickedButton = (JButton) e.getSource();
                        int clickedRow = -1, clickedCol = -1;

                        for (int row = 0; row < board.getLine(); row++) {
                            for (int col = 0; col < board.getColumn(); col++) {
                                if (button[row][col] == clickedButton) {
                                    clickedRow = row;
                                    clickedCol = col;
                                    break;
                                }
                            }
                            if (clickedRow != -1) {
                                break;
                            }
                        }

                        //verificando se a célula correspondente é uma bomba
                        if ((board.getSquare()[clickedRow][clickedCol] instanceof Bomb) && (!board.getSquare()[clickedRow][clickedCol].checkFlag())) {
                        	board.getSquare()[clickedRow][clickedCol].selecting();
                        	
                        	ImageIcon bombIcon = new ImageIcon(bombImage.getImage());
                            Image scaledImage = bombIcon.getImage().getScaledInstance(clickedButton.getWidth()-10, clickedButton.getHeight()-10, Image.SCALE_SMOOTH);
                            bombIcon = new ImageIcon(scaledImage);
                            clickedButton.setLayout(new BorderLayout());
                            clickedButton.add(new JLabel(bombIcon), BorderLayout.CENTER);
                            
                            updateButtons();
                            
                            gameOver(player1);
                            
                        } 
                        else if ((board.getSquare()[clickedRow][clickedCol] instanceof EmptySpace) && (!board.getSquare()[clickedRow][clickedCol].checkFlag())) {
                            board.automaticPropagation(clickedRow, clickedCol);
                            updateButtons();
                        } 
                        else if ((board.getSquare()[clickedRow][clickedCol] instanceof Neighborhood) && (!board.getSquare()[clickedRow][clickedCol].checkFlag())) {

                            //exibindo o número de bombas ao redor da célula
                            int bombsAround = board.calculateBombs(clickedRow, clickedCol);
                            clickedButton.setText(Integer.toString(bombsAround));
                            board.getSquare()[clickedRow][clickedCol].selecting();
                            updateButtons();
                        }
                        board.calculateScore(player1);
                    }
                });

              //definindo método ao clicar com o botão direito (bandeira)
                button[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //verificando se é um clique com o botão direito
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            if (!board.getSquare()[row][col].checkSelected()) {
                                if (!board.getSquare()[row][col].checkFlag()) {
                                    board.getSquare()[row][col].setHasFlag(true);
                                    board.getSquare()[row][col].selecting();
                                    if((board.getSquare()[row][col] instanceof Bomb) && (board.getSquare()[row][col].checkFlag())){
                                    	numBombsFlagged++;
                                    }
                                    
                                    
                                    ImageIcon flagIcon = new ImageIcon(flagImage.getImage());
                                    Image scaledImage = flagIcon.getImage().getScaledInstance(button[row][col].getWidth(), button[row][col].getHeight(), Image.SCALE_SMOOTH);
                                    flagIcon = new ImageIcon(scaledImage);
                                    button[row][col].setIcon(flagIcon);
                                    
                                    //verificando se todas as bombas foram marcadas com bandeiras para vencer o jogo
                                    if ((numBombsFlagged == totalBombs) && (allFlagsCorrect())) {
                                        gameWin(player1);
                                    }
                                    
                                } 
                                else {
                                    board.getSquare()[row][col].setHasFlag(false);
                                    board.getSquare()[row][col].unselecting();
                                    
                                    button[row][col].setText("");
                                    button[row][col].setIcon(null);
                                    button[row][col].setLayout(new BorderLayout());
                                }
                            } 
                            else if (board.getSquare()[row][col].checkSelected() && board.getSquare()[row][col].checkFlag()) {
                                board.getSquare()[row][col].setHasFlag(false);
                                board.getSquare()[row][col].unselecting();
                                
                                button[row][col].setText("");
                                button[row][col].setIcon(null);
                                button[row][col].setLayout(new BorderLayout());
                            }
                        }
                        board.calculateScore(player1);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        //hover on
                        if (!board.getSquare()[row][col].checkSelected()) {
                            button[row][col].setBackground(new Color(0, 0, 90));
                        } 
                        else {
                            button[row][col].setBackground(new Color(0, 0, 3));
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        //hover off
                        if (!board.getSquare()[row][col].checkSelected()) {
                            button[row][col].setBackground(new Color(0, 0, 40));
                        } 
                        else {
                            button[row][col].setBackground(new Color(0, 0, 3));
                        }
                    }
                });
            }
        }

        //inicializando o jogo
        board.startGame();
        
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        //criando um JDialog para exibir o nome da pessoa e o número de casas abertas
        JDialog infoDialog = new JDialog(this, "Informações do Jogador", false);
        infoDialog.setSize(482, 60);
        infoDialog.setResizable(false);
        infoDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        infoDialog.setLayout(new GridLayout(2, 1, 0,0));
        infoDialog.getContentPane().setBackground(new Color(0, 0, 40));
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 250), 2);
        infoDialog.getRootPane().setBorder(border);
        

        //label para exibir o nome da pessoa e o número de casas abertas
        JLabel infoLabel = new JLabel("Player: " + player1.getName());
        infoLabel1 = new JLabel("Score: "+ player1.getScore());
        infoLabel.setForeground(new Color(250, 250, 250));
        infoLabel1.setForeground(new Color(250, 250, 250));
        infoLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        infoLabel1.setFont(new Font("Verdana", Font.BOLD, 15));
        infoDialog.add(infoLabel);
        infoDialog.add(infoLabel1);

        //posicionando o JDialog abaixo da janela principal
        int mainFrameX = this.getLocation().x;
        int mainFrameY = this.getLocation().y;
        int mainFrameHeight = this.getHeight();

        int dialogX = mainFrameX;
        int dialogY = mainFrameY + mainFrameHeight;
        infoDialog.setLocation(dialogX + 9, dialogY - 6);
        
        infoDialog.setResizable(false);
        infoDialog.setUndecorated(true);
        infoDialog.setVisible(true);
    }
    
    //definindo método para atualizar os pontos do jogador
    
    public void updateOpenedCellsCount(int count) {
        infoLabel1.setText("Score: " + count);
    }

    //definindo método para atualizar os textos dos botões após a propagação automática
    public void updateButtons() {
        int openedCount = 0;
        for (int i = 0; i < button.length; i++) {
            for (int j = 0; j < button[i].length; j++) {
                if (board.getSquare()[i][j].checkSelected()) {
                    if (board.getSquare()[i][j] instanceof Bomb && !board.getSquare()[i][j].checkFlag()) {
                    	button[i][j].setText(".");
                    } 
                    else if (board.getSquare()[i][j] instanceof EmptySpace && !board.getSquare()[i][j].checkFlag()) {
                        button[i][j].setText("");
                        openedCount++;
                    } 
                    else if (board.getSquare()[i][j] instanceof Neighborhood && !board.getSquare()[i][j].checkFlag()) {
                        int bombsAround = board.calculateBombs(i, j);
                        button[i][j].setText(Integer.toString(bombsAround));
                        openedCount++;
                    }
                    //escurecendo botão se tiver selecionado
                    button[i][j].setBackground(new Color(0, 0, 3));
                } 
                else {
                    //clareando botão se não tiver selecionado
                    button[i][j].setBackground(new Color(0, 0, 40));
                }
            }
        }
        updateOpenedCellsCount(openedCount);
    }
    
    //método para exibir diálogo de entrada personalizado
    private String showCustomInputDialog() {
        JDialog dialog = new JDialog(this, "Username", true);
        JPanel panel = new JPanel(new FlowLayout());
        JTextField textField = new JTextField(15);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.getContentPane().setBackground(new Color(0, 0, 40));
        panel.setBackground(new Color(0, 0, 40));
        
        textField.setBackground(new Color(255, 255, 255));
        textField.setForeground(new Color(0, 0, 0));

        okButton.setBackground(new Color(100, 100, 255));
        okButton.setForeground(new Color(255, 255, 255));

        //adicionando componentes ao JDialog
        JLabel enter = new JLabel("Enter your username:");
        enter.setForeground(new Color(250, 250, 250));
        enter.setFont(new Font("Verdana", Font.BOLD, 13));
        panel.add(enter);
        panel.add(textField);
        panel.add(okButton);
        panel.setForeground(new Color(250, 250, 250));
        panel.setFont(new Font("Verdana", Font.BOLD, 15));
        dialog.add(panel);
        dialog.pack();
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        //centralizando o diálogo
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        //retornando o texto inserido ou null se o diálogo for fechado sem inserção
        if (textField.getText().isEmpty()) {
            return null;
        } else {
            return textField.getText();
        }
    }
    
    public void gameOver(Player player) {
        try {
            //exibindo mensagem indicando que o jogo acabou e o jogador perdeu
            JDialog gameOverDialog = new JDialog(this, "Game Over", true);
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(new Color(0, 0, 40));
            gameOverDialog.setContentPane(mainPanel);
            Border border = BorderFactory.createLineBorder(new Color(0, 0, 250), 2);
            gameOverDialog.getRootPane().setBorder(border);
            
            JLabel gameOverLabel = new JLabel(player.getName() + " LOST!!!");
            gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
            gameOverLabel.setFont(new Font("Verdana", Font.BOLD, 20));
            gameOverLabel.setForeground(new Color(250,250,250));
            mainPanel.add(gameOverLabel, BorderLayout.CENTER);
            
            gameOverDialog.setSize(300, 100);
            gameOverDialog.setLocationRelativeTo(this);
            gameOverDialog.setVisible(true);
            gameOverDialog.setResizable(false);
            
            
            //revelando as bombas
            for (int i = 0; i < button.length; i++) {
                for (int j = 0; j < button[i].length; j++) {
                    button[i][j].setEnabled(false);
                    if (board.getSquare()[i][j] instanceof Bomb) {
                        ImageIcon bombIcon = new ImageIcon(bombImage.getImage());
                        Image scaledImage = bombIcon.getImage().getScaledInstance(button[i][j].getWidth()-10, button[i][j].getHeight()-10, Image.SCALE_SMOOTH);
                        bombIcon = new ImageIcon(scaledImage);
                        button[i][j].setLayout(new BorderLayout());
                        button[i][j].add(new JLabel(bombIcon), BorderLayout.CENTER);
                        button[i][j].setText(".");
                        updateButtons();
                    }
                }
            }
            
            recordManager.saveRecords("C:\\Users\\User\\git\\repository\\zminesweeper\\src\\leaderboard\\leaderboardMinesweeper.txt");

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                	dispose();
                    new MainFrame();
                }
            }, 5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void gameWin(Player player) {
        try {
            //exibindo mensagem indicando que o jogo acabou e o jogador perdeu
            JDialog gameWinDialog = new JDialog(this, "Victory", true);
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(new Color(0, 0, 40));
            gameWinDialog.setContentPane(mainPanel);
            Border border = BorderFactory.createLineBorder(new Color(0, 0, 250), 2);
            gameWinDialog.getRootPane().setBorder(border);
            
            JLabel gameWinLabel = new JLabel(player.getName() + " WON!!!");
            gameWinLabel.setHorizontalAlignment(JLabel.CENTER);
            gameWinLabel.setFont(new Font("Verdana", Font.BOLD, 20));
            gameWinLabel.setForeground(new Color(250,250,250));
            mainPanel.add(gameWinLabel, BorderLayout.CENTER);
            
            gameWinDialog.setSize(300, 100);
            gameWinDialog.setLocationRelativeTo(this);
            gameWinDialog.setVisible(true);
            gameWinDialog.setResizable(false);
            
            
            //revelando as bombas
            for (int i = 0; i < button.length; i++) {
                for (int j = 0; j < button[i].length; j++) {
                    button[i][j].setEnabled(false);
                    if (board.getSquare()[i][j] instanceof Bomb) {
                        ImageIcon bombIcon = new ImageIcon(bombImage.getImage());
                        Image scaledImage = bombIcon.getImage().getScaledInstance(button[i][j].getWidth()-10, button[i][j].getHeight()-10, Image.SCALE_SMOOTH);
                        bombIcon = new ImageIcon(scaledImage);
                        button[i][j].setLayout(new BorderLayout());
                        button[i][j].add(new JLabel(bombIcon), BorderLayout.CENTER);
                        button[i][j].setText(".");
                        updateButtons();
                    }
                }
            }
            
            recordManager.saveRecords("C:\\Users\\User\\git\\repository\\zminesweeper\\src\\leaderboard\\leaderboardMinesweeper.txt");

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                	dispose();
                    new MainFrame();
                }
            }, 5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //método para verificar se todas as bandeiras estão corretamente colocadas em bombas
    private boolean allFlagsCorrect() {
        for (int i = 0; i < board.getLine(); i++) {
            for (int j = 0; j < board.getColumn(); j++) {
                if (board.getSquare()[i][j] instanceof Bomb) {
                    if (!board.getSquare()[i][j].checkFlag()) {
                        return false; 
                    }
                } 
                else {
                    if (board.getSquare()[i][j].checkFlag()) {
                        return false; 
                    }
                }
            }
        }
        return true; 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //...
    }
}
