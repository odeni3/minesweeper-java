package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import boards.EasyBoard;
import boards.NormalBoard;
import elements.Bomb;
import elements.EmptySpace;
import elements.Neighborhood;

public class MineSweeperGui extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private ImageIcon image = new ImageIcon("C:\\Users\\User\\git\\repository\\zminesweeper\\src\\images\\logo.png");
    private JButton[][] button;
    private NormalBoard board;

    public MineSweeperGui(NormalBoard board) {
        this.board = board;

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

        for (int i = 0; i < board.getLine(); i++) {
            for (int j = 0; j < board.getColumn(); j++) {
                button[i][j] = new JButton();
                button[i][j].setFocusable(false);
                button[i][j].setFont(new Font("Verdana", Font.BOLD, 20));
                button[i][j].setBackground(new Color(0, 0, 40));
                button[i][j].setForeground(new Color(0, 0, 250));
                button[i][j].setBorder(BorderFactory.createLineBorder(new Color(0, 50, 100), 3));

                this.add(button[i][j]);

                final int row = i;
                final int col = j;
                
                //definindo método ao clicar com o botão esquerdo
                button[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Se for um clique esquerdo, faça a lógica existente
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
                            clickedButton.setText("X");
                            board.getSquare()[clickedRow][clickedCol].selecting();
                        } 
                        else if ((board.getSquare()[clickedRow][clickedCol] instanceof EmptySpace) && (!board.getSquare()[clickedRow][clickedCol].checkFlag())) {
                            board.automaticPropagation(clickedRow, clickedCol);
                            
                            //atualizando os botões após a propagação automática
                            updateButtons();
                        } 
                        else if ((board.getSquare()[clickedRow][clickedCol] instanceof Neighborhood) && (!board.getSquare()[clickedRow][clickedCol].checkFlag())) {
                            
                        	//exibindo o número de bombas ao redor da célula
                            int bombsAround = board.calculateBombs(clickedRow, clickedCol);
                            clickedButton.setText(Integer.toString(bombsAround));
                            board.getSquare()[clickedRow][clickedCol].selecting();
                        }
                }});

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
                                    button[row][col].setText("F");
                                } 
                                else if (board.getSquare()[row][col].checkFlag()){
                                    board.getSquare()[row][col].setHasFlag(false);
                                    board.getSquare()[row][col].unselecting();
                                    button[row][col].setText("");
                                }
                            }
                            else if (board.getSquare()[row][col].checkSelected() && board.getSquare()[row][col].checkFlag() ) {
                            	board.getSquare()[row][col].setHasFlag(false);
                                board.getSquare()[row][col].unselecting();
                                button[row][col].setText("");
                            }
                        }
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
    }

    //definindo método para atualizar os textos dos botões após a propagação automática
    public void updateButtons() {
        for (int i = 0; i < button.length; i++) {
            for (int j = 0; j < button[i].length; j++) {
                if (board.getSquare()[i][j].checkSelected()) {
                    if (board.getSquare()[i][j] instanceof Bomb && !board.getSquare()[i][j].checkFlag()) {
                        button[i][j].setText("X");
                    } else if (board.getSquare()[i][j] instanceof EmptySpace && !board.getSquare()[i][j].checkFlag()) {
                        button[i][j].setText(""); // Você pode querer definir um texto específico para EmptySpace
                    } else if (board.getSquare()[i][j] instanceof Neighborhood && !board.getSquare()[i][j].checkFlag()) {
                        int bombsAround = board.calculateBombs(i, j);
                        button[i][j].setText(Integer.toString(bombsAround));
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
    }

    public static void main(String[] args) {
        new MineSweeperGui(new EasyBoard(18, 18, 12));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //...
    }
}
