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
	
	//definindo método para entrar no modo flag para adicionar flags
	/*
	public void userFlagMode(String mode) {
		boolean boolMode;
		boolMode = Boolean.getBoolean(mode);
		if( mode == "TRUE" ) {
			boolMode.setFlagMode(true);
		}	
	}
	*/
	
	//definindo método para selecionar as células
	
	public void userSelect(int selectedLine, int selectedColumn) {
		square[selectedLine-1][selectedColumn-1].selecting();
	}
	
	//definindo método para calcular o número de bombas ao redor (NAO ESTA TOTALMENTE CERTO)
	
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
				if ((square[i][j].checkSelected()) && (!square[i][j].checkCelula())) {
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
