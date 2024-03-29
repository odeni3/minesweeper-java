package boards;


import java.util.Random;

import elements.Bomb;
import elements.Cell;
import elements.EmptySpace;
import elements.Neighborhood;
import exceptions.InvalidAttributeValueException;
import players.Player;

public class CrazyBoard extends NormalBoard implements InterfaceBoard {
	
	//definindo atributos
	
	private int numCrazyCell;
	
	public int getNumCrazyCell() {
		return numCrazyCell;
	}

	public void setNumCrazyCell(int numCrazyCell) {
		this.numCrazyCell = numCrazyCell;
	}
	
	//definindo método construtor do tabuleiro maluco
	
	public CrazyBoard(int line, int column, int numBomb, int numCrazyCell) {
		super(line,column,numBomb);
		this.numCrazyCell = numCrazyCell;
	}
	
	//definindo método para inicializar jogo
	
	public void startGame() {
	    square = new Cell[line][column];

	    for (int i = 0; i < line; i++) {
	        for (int j = 0; j < column; j++) {
                square[i][j] = new Neighborhood();
	        }
	    }
	    
	    try {
			addBombBoard();
		} catch (InvalidAttributeValueException e) {
			e.printStackTrace();
		}
	    
	    addCrazyCellBoard();
	    
	    for (int i = 0; i < line; i++) {
	        for (int j = 0; j < column; j++) {
	        	if (calculateBombs(i,j) == 0 && square[i][j] instanceof Neighborhood) {
	        		square[i][j] = new EmptySpace();
	        	}
	        }
	    }
	}
	
	//definindo método para adicionar células malucas
	
	public void addCrazyCellBoard() {
		Random rn = new Random();
		
		int cont = 0;

	    while(cont < numCrazyCell) {
	        int lineRandom = rn.nextInt(getLine());
	        int columnRandom = rn.nextInt(getColumn());

	        if (!(getSquare()[lineRandom][columnRandom]).checkCrazyCell()) {
	        	getSquare()[lineRandom][columnRandom].setCrazyCell(true);
	        	cont++;
	        }
	    }
	}
    
	//definindo método para selecionar as células
	
	public void userSelect(int selectedLine, int selectedColumn, int intention, Player player) {
	    selectedLine--;
	    selectedColumn--;

	    if (selectedLine < 0 || selectedLine >= line || selectedColumn < 0 || selectedColumn >= column) {
	        System.out.println();
	        System.out.println("Posição inválida! Tente novamente!!");
	        System.out.println();
	        return;
	    }

	    // adicionar flag
	    
	    else if (intention == 1) {
	        if (!(square[selectedLine][selectedColumn].checkSelected()) && !(square[selectedLine][selectedColumn].checkCrazyCell())) {
	            square[selectedLine][selectedColumn].setHasFlag(true);
	            square[selectedLine][selectedColumn].selecting();
	            
	            // Incrementar o número de bombas marcadas com bandeiras
	            
	            if (square[selectedLine][selectedColumn] instanceof Bomb) {
	                numBombsFlagged++;
	            }

	            // Verificar se todas as bombas foram marcadas com bandeiras para vencer o jogo
	            
	            if (numBombsFlagged == numBomb) {
	            	System.out.println(this);
	            	System.out.println("[ "+player.getName()+" ]" + " VENCEU!!!"+ " | SCORE: " + (player.getScore()+1));
	            	System.exit(0);
	            }
	        } 
	        
	   // lógica para fazer a probabilidade da célula maluca alterar seu status de bomba
	        
	        else if (!(square[selectedLine][selectedColumn].checkSelected()) && (square[selectedLine][selectedColumn].checkCrazyCell())) {
	        	handleCrazyCell(selectedLine, selectedColumn, player);
	        }
	    }

	    // propagação automática
	    
	    else if (intention == 0) {
	        automaticPropagation(selectedLine, selectedColumn);
	        if ((square[selectedLine][selectedColumn] instanceof Bomb) && (square[selectedLine][selectedColumn].checkSelected()) && (!square[selectedLine][selectedColumn].checkFlag())) {
	            System.out.println(this);
	            gameOver(player);
	        }
	    }

	    // remover flag
	    
	    else if (intention == 2) {
	        if (square[selectedLine][selectedColumn].checkFlag()) {
	            square[selectedLine][selectedColumn].setHasFlag(false);
	            square[selectedLine][selectedColumn].unselecting();
	        }
	    }

	    // revelar casa
	    
	    else {
	        if (square[selectedLine][selectedColumn] instanceof Bomb) {
	            System.out.println(this);
	            gameOver(player);
	        } 
	        else {
	            square[selectedLine][selectedColumn].selecting();
	        }
	    }
	}
	
	public void handleCrazyCell(int selectedLine, int selectedColumn, Player player) {
	    Random randomCrazyCell = new Random();
	    double probabilidadeAlternar = 0.5; 
	    square[selectedLine][selectedColumn].setHasFlag(true);
	    square[selectedLine][selectedColumn].selecting();
	    
	    if (randomCrazyCell.nextDouble() <= probabilidadeAlternar) {
	        if (square[selectedLine][selectedColumn] instanceof Bomb) {
	            numBombsFlagged++;
	            /*if (numBombsFlagged == numBomb) {
	                System.out.println(this);
	                System.out.println("[ " + player.getName() + " ]" + " VENCEU!!!" + " | SCORE: " + (player.getScore() + 1));
	                System.exit(0);
	            } 
	            else {*/
	                if (calculateBombs(selectedLine, selectedColumn) == 0) {
	                    square[selectedLine][selectedColumn] = new EmptySpace();
	                } 
	                else {
	                    square[selectedLine][selectedColumn] = new Neighborhood();
	                }
	                square[selectedLine][selectedColumn].setSelected(false);
	                square[selectedLine][selectedColumn].setHasFlag(true);
	            //}
	        }
	    }
	}
}

	


