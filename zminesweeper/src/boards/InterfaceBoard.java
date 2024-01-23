package boards;

import players.Player;

public interface InterfaceBoard {
	
	public void startGame();
	
	public void userSelect(int selectedLine, int selectedColumn, int intention, Player player);
	
}
