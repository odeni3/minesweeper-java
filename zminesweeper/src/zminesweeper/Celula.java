package zminesweeper;

public class Celula {
	
	//definindo atributos
	
	private boolean hasBomb;
	private boolean hasFlag;
	private boolean selected;
	
	//definindo getters e setters
	
	public boolean getBomb() {
		return hasBomb;
	}
	public void setBomb(boolean bomb) {
		this.hasBomb = bomb;
	}
	public boolean getFlag() {
		return hasFlag;
	}
	public void setFlag(boolean flag) {
		this.hasFlag = flag;
	}
	public boolean getSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	

	//método para checar se a célula tem bomba
	
	public boolean checkCelula() {
		if (hasBomb) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//método para adicionar bomba na célula
	
	public void addBombCelula() {
		this.hasBomb = true;
	}
	
	//método para selecionar célula
	
	public void selecting() {
		this.selected = true;
	}
	
	//método para colocar bandeira na célula
	
	public void addFlag() {
		this.hasFlag = true;
	}
	
	//método para tirar bandeira da célula
	
	public void removeFlag() {
		this.hasFlag = false;
	}
	
	//método para printar as bombas e células
	
	@Override
	public String toString() {
		if (hasBomb == true) {
			return "☼";
		}
		else {
			return "▢";
		}
	}
}
