package elements;

public class Bomb extends Cell {
	
	//definindo atributos
	
	private boolean hasBomb;
	
	//definindo método construtor da bomba
	
	public Bomb() {
        super();
        addBombCell();
    }
	
	//definindo método para checar se a célula é bomba
	
	public boolean checkCell() {
		return hasBomb;	
	}
	
	//definindo método para adicionar bomba na célula
	
	public void addBombCell() {
		this.hasBomb = true;
	}
	
	//definindo método para printar as bombas, flags e espaços vazios

    @Override
    public String toString() {
        if (checkSelected() && checkCell() && !checkFlag()) {
            return "X";
        } 
        else if(checkFlag()) {
        	return "=";
        }
        else {
            return "□";
        }
    }
}


