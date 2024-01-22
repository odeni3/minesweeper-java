package boards;


import java.util.Random;

import elements.Bomb;
import elements.Cell;
import elements.EmptySpace;
import elements.Neighborhood;
import players.Player;

public class CrazyBoard extends Board{
	

	private int numCrazyCell;
	
	
	
	//definindo método construtor do tabuleiro
	

	public int getNumCrazyCell() {
		return numCrazyCell;
	}

	public void setNumCrazyCell(int numCrazyCell) {
		this.numCrazyCell = numCrazyCell;
	}

	public CrazyBoard(int line, int column, int numBomb, int numCrazyCell) {
		super(line,column,numBomb);
		this.numCrazyCell = numCrazyCell;
	}
	
	//fazendo com que o metodo startgame de board seja sobescrito
	
	public void startGame() {
	    square = new Cell[line][column];

	    for (int i = 0; i < line; i++) {
	        for (int j = 0; j < column; j++) {
                square[i][j] = new Neighborhood();
	        }
	    }
	    addBombBoard();
	    addCrazyCellBoard();
	    
	    for (int i = 0; i < line; i++) {
	        for (int j = 0; j < column; j++) {
	        	if (calculateBombs(i,j) == 0 && square[i][j] instanceof Neighborhood) {
	        		square[i][j] = new EmptySpace();
	        	}
	        }
	    }
	}
	
	public void addCrazyCellBoard() {
		Random rn = new Random();

	    for (int z = 0; z < numCrazyCell; z++) {
	        int lineRandom = rn.nextInt(getLine());
	        int columnRandom = rn.nextInt(getColumn());

	        if (!(getSquare()[lineRandom][columnRandom]).checkCrazyCell()) {
	        	getSquare()[lineRandom][columnRandom].setCrazyCell(true);
	        }
	    }
	}
        
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
	        } else if (!(square[selectedLine][selectedColumn].checkSelected()) && (square[selectedLine][selectedColumn].checkCrazyCell())) {
	            
	        	// Adicionar lógica para alternar o estado com base em uma probabilidade
	        	
	            Random random = new Random();
	            
	            double probabilidadeAlternar = 0.5; // Defina a probabilidade desejada (entre 0 e 1)

	            if (random.nextDouble() < probabilidadeAlternar) {
	                // Alternar o estado da célula
	            	if(calculateBombs(selectedLine,selectedColumn) == 0){
	            		square[selectedLine][selectedColumn] =  new EmptySpace();
	            	}
	            	else {
	            		square[selectedLine][selectedColumn] =  new Neighborhood();
	            	}
	            } 
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
}
	


