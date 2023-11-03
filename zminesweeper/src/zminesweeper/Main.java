package zminesweeper;

public class Main {
	
	//método main

	public static void main(String[] args) {
		
		Board tabMain =  new Board(7,7,7);
		tabMain.startGame();
		System.out.println(tabMain);

	}

}
