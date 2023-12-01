package zminesweeper;

public class EmptySpace extends Cell {

	//definindo atributos
	
	private boolean isEmpty;
	
	//definindo método construtor da célula vazia
	
	public EmptySpace() {
        super();
    }
	
	//definindo getters e setters
	
	public boolean getEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	//definindo método para printar espaço vazio e flags
	
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
