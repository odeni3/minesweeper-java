package zminesweeper;

public class Bomba extends Celula {
	
	private boolean hasBomb;
	
	public Bomba() {
        super();
        addBombCelula();
    }
	
	public boolean checkCelula() {
		return hasBomb;	
	}
	
	public void addBombCelula() {
		this.hasBomb = true;
	}

    @Override
    public String toString() {
        if (checkSelected() && checkCelula() && !checkFlag()) {
            return "X";
        } 
        else if(checkSelected() && checkFlag()) {
        	return "=";
        }
        else {
            return "□";
        }
    }
}


