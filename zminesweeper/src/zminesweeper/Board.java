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
	
	//definindo método para inicializar o jogo
	
	//
	

	
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
	
	
	public void startGame() {
		square = new Celula[line][column];
		
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < column; j++) {
				square[i][j] = new Celula();
			}
		}
		addBombBoard();
	}
	
	//método para printar os quadradinhos
	
	@Override
	public String toString() {
		String c = "";
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				c += square[i][j] + " ";
			}
			c += "\n";
		}
		return c;
	}
	
}
