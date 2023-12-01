package zminesweeper;

public class Neighborhood extends Cell {

	//definindo atributos
	
	private boolean isNeighboor;
	
	//definindo método construtor da célula vizinha
	
	public Neighborhood() {
        super();
    }

	//definindo getters e setters
	
	public boolean getNeighboor() {
		return isNeighboor;
	}

	public void setNeighboor(boolean isNeighboor) {
		this.isNeighboor = isNeighboor;
	}
	
	//definindo método para printar as flags e espaços vazios

	@Override
    public String toString() {
        if (checkSelected() && !checkFlag()) {
            return "";
        } 
        else if(checkFlag()) {
        	return "=";
        }
        else {
            return "□";
        }
    }
}

