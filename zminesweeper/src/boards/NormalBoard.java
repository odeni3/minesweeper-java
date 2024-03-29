package boards;

import java.util.Random;

import elements.Bomb;
import elements.Cell;
import elements.EmptySpace;
import elements.Neighborhood;
import players.Player;
import exceptions.InvalidAttributeValueException;

public abstract class NormalBoard implements InterfaceBoard {
	
	//definindo atributos
	
	protected Cell[][] square;
	protected int line;
	protected int column;
	protected int numBomb;
	protected int numBombsFlagged;
	
	public Cell [][] getSquare() {
		return this.square;
	}
	
	public void setSquare (Cell[][] square) {
		this.square = square;
	}
	
	public int getLine() {
		return this.line;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public int getNumBomb() {
		return this.numBomb;
	}
	
	//definindo método construtor do tabuleiro normal
	
	public NormalBoard(int line, int column, int numBomb) {
		this.line = line;
		this.column = column;
		this.numBomb = numBomb;
	}
	
	//definindo método para adicionar bombas randomicamente
	
	public void addBombBoard() throws InvalidAttributeValueException {
	    Random rn = new Random();

	    //lançando exceção caso o número de bombas seja maior que o número de células
	    if (numBomb > (line * column)) {
	        throw new InvalidAttributeValueException("Número de bombas maior do que o número total de células no tabuleiro.");
	    }

	    //inicializa o número de bombas marcadas com bandeiras
	    numBombsFlagged = 0;
	    
	    int cont = 0;

	    //adicionando bombas até o número desejado ser alcançado
	    while (cont < numBomb) {
	        int lineRandom = rn.nextInt(line);
	        int columnRandom = rn.nextInt(column);

	        if (!(square[lineRandom][columnRandom] instanceof Bomb)) {
	            square[lineRandom][columnRandom] = new Bomb();
	            cont++;
	        }
	    }
	}

	
	//definindo método para inicializar jogo
	
	public void startGame() {
	    square = new Cell[line][column];

	    for (int i = 0; i < line; i++) {
	        for (int j = 0; j < column; j++) {
                square[i][j] = new Neighborhood();
	        }
	    }
	    
	    //realizando tratamento da exceção de atributo de valor do número de bombas
	    
	    try {
			addBombBoard();
		} catch (InvalidAttributeValueException e) {
			e.printStackTrace();
		}
	    
	    for (int i = 0; i < line; i++) {
	        for (int j = 0; j < column; j++) {
	        	if (calculateBombs(i,j) == 0 && square[i][j] instanceof Neighborhood) {
	        		square[i][j] = new EmptySpace();
	        	}
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
	    
	    if (intention == 1) {
	        if (!square[selectedLine][selectedColumn].checkSelected()) {
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

	//definindo método para função de GAMEOVER
    
	public void gameOver(Player player) {
	    try {
	    	
	        //Lógica para GAMEOVER
	    	
	    	Thread.sleep(500);
	    	System.out.println("3");
	        Thread.sleep(1100);
	        System.out.println("2");
	        Thread.sleep(1100);
	        System.out.println("1");
	        Thread.sleep(1100);
	        System.out.println("☢⚠☢ B O O M !!! ☢⚠☢");
	        Thread.sleep(1100);
	        System.out.println();
	        System.out.println("[ "+player.getName()+" ]" + " PERDEU!!!");
	        
	        for (int i = 0; i < line; i++) {
		        for (int j = 0; j < column; j++) {
		        	square[i][j].selecting();
		        }
	        }
	        System.out.println(this);
	    
	    System.exit(0);
	    }
	    catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	  }

    //definindo método para função de propagação automática

	public void automaticPropagation(int lineAuto, int columnAuto) {
	    if (lineAuto >= 0 && lineAuto < square.length && columnAuto >= 0 && columnAuto < square[lineAuto].length) {
	        if (!(square[lineAuto][columnAuto] instanceof Bomb) && (square[lineAuto][columnAuto] instanceof EmptySpace) && (!square[lineAuto][columnAuto].checkSelected())) {
	            square[lineAuto][columnAuto].selecting();
	            for (int i = lineAuto - 1; i <= lineAuto + 1; i++) {
	                for (int j = columnAuto - 1; j <= columnAuto + 1; j++) {
	                    if (i != lineAuto || j != columnAuto) {
	                        automaticPropagation(i, j);
	                    }
	                }
	            }
	        } 
	        else {
	            square[lineAuto][columnAuto].selecting();
	        }
	    }
	}

	//definindo método para calcular o número de bombas ao redor
	
	public int calculateBombs(int calcSelectedLine, int calcSelectedColumn) {
	    int cont = 0;

	    for (int i = calcSelectedLine - 1; i <= calcSelectedLine + 1; i++) {
	        for (int j = calcSelectedColumn - 1; j <= calcSelectedColumn + 1; j++) {
	            if (i >= 0 && i < square.length && j >= 0 && j < square[i].length && (i != calcSelectedLine || j != calcSelectedColumn)) {
	                if (square[i][j] instanceof Bomb) {
	                    cont++;
	                }
	            }
	        }
	    }

	    return cont;
	}

	
	//definindo método para calcular os pontos de cada jogador
	
	public void calculateScore(Player player) {
	    int cont = 0;

	    for (int i = 0; i < line; i++) {
	        for (int j = 0; j < column; j++) {
	            if (square[i][j].checkSelected() && !square[i][j].getCheckedToScore() && !square[i][j].checkFlag()) {
	                cont++;
	                square[i][j].setCheckedToScore(true);
	            }
	            else if (square[i][j].checkSelected() && !square[i][j].getCheckedToScore() && square[i][j] instanceof Bomb) {
	                cont++;
	                square[i][j].setCheckedToScore(true);
	            }
	        }
	    }
	    player.addScore(cont);
	}

	//método para printar os quadradinhos
	
	@Override
	public String toString() {
		String c = "";
		for (int i = 0; i < square.length; i++) {
			for (int j = 0; j < square[i].length; j++) {
				if ((square[i][j].checkSelected()) && !(square[i][j] instanceof Bomb) && (!(square[i][j].checkFlag()))) {
					c += square[i][j] + ""+ calculateBombs(i,j);
				}
				else {
					c += square[i][j] + "";
				}
			}
			c += "\n";
		}
		return c;
	}
}


