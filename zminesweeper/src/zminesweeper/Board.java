package zminesweeper;

import java.util.Random;

public class Board {
	
	//definindo atributos
	
	private Celula[][] square;
	private int line;
	private int column;
	private int numBomb;
	
	//definindo método construtor do tabuleiro
	
	public Board(int line, int column, int numBomb) {
		this.line = line;
		this.column = column;
		this.numBomb = numBomb;
	}
	
	//definindo método para adicionar bombas randomicamente
	
	public void addBombBoard() {
		
		Random rn = new Random();
		
		for (int z = 0; z < numBomb; z++) {
			int lineRandom = rn.nextInt(line);
			int columnRandom = rn.nextInt(column);
			
			if (!square[lineRandom][columnRandom].checkCelula()) {
				square[lineRandom][columnRandom].addBombCelula();
			}
		}	
	}
	
	//definindo método para inicializar jogo
	
	public void startGame() {
		square = new Celula[line][column];
		
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < column; j++) {
				square[i][j] = new Celula();
			}
		}
		addBombBoard();
	}
	
	//definindo método para selecionar as células
	
    public void userSelect(int selectedLine, int selectedColumn, int intention) {
        selectedLine--;
        selectedColumn--;
        if (intention == 1) {
        	if (!square[selectedLine][selectedColumn].checkSelected()) {
        		square[selectedLine][selectedColumn].setHasFlag(true);
	        	square[selectedLine][selectedColumn].selecting();
        	}
        }
        else if(intention == 0) {
        	automaticPropagation(selectedLine, selectedColumn);
        }
        else if(intention == 2) {
        	if (square[selectedLine][selectedColumn].checkFlag()) {
	        	square[selectedLine][selectedColumn].setHasFlag(false);
	        	square[selectedLine][selectedColumn].unselecting();
        	}
        }
        else {
        	System.out.println("INVALIDO! TENTE NOVAMENTE!!");
        }
    }
    
    //definindo método para função de propagação automática

    public void automaticPropagation(int lineAuto, int columnAuto) {
        if (lineAuto >= 0 && lineAuto < square.length && columnAuto >= 0 && columnAuto < square[lineAuto].length) {
            if (!square[lineAuto][columnAuto].checkCelula() && calculateBombs(lineAuto, columnAuto) == 0 && !square[lineAuto][columnAuto].checkSelected()) {
                square[lineAuto][columnAuto].selecting();
                for (int i = lineAuto - 1; i <= lineAuto + 1; i++) {
                    for (int j = columnAuto - 1; j <= columnAuto + 1; j++) {
                        automaticPropagation(i, j);
                    }
                }
            } else {
                square[lineAuto][columnAuto].selecting();
            }
        }
    }	

	//definindo método para calcular o número de bombas ao redor
	
	public int calculateBombs(int calcSelectedLine, int calcSelectedColumn){
		int cont = 0;
		for (int i = calcSelectedLine - 1; i <= calcSelectedLine + 1; i++) {
		    for (int j = calcSelectedColumn - 1; j <= calcSelectedColumn + 1; j++) {
		        if (i >= 0 && i < square.length && j >= 0 && j < square[i].length) {
		            if (square[i][j].checkCelula()) {
		                cont++;
		            }
		        }
		    }
		}
		return cont;
	}
	
	//método para printar os quadradinhos
	
	@Override
	public String toString() {
		String c = "";
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				if ((square[i][j].checkSelected()) && (!square[i][j].checkCelula()) && (!square[i][j].checkFlag())) {
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