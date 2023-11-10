package zminesweeper;

public class Celula {
	
	//definindo atributos
	
	private boolean hasBomb;
	private boolean hasFlag;
	private boolean selected;
	private boolean flagMode;
	
	//definindo getters e setters
	
	
	public boolean getBomb() {
		return this.hasBomb;
	}
	public void setBomb(boolean bomb) {
		this.hasBomb = bomb;
	}
	public boolean getFlag() {
		return this.hasFlag;
	}
	public void setFlag(boolean flag) {
		this.hasFlag = flag;
	}
	public boolean getSelected() {
		return this.selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public boolean getFlagMode() {
		return this.flagMode;
	}
	public void setFlagMode(boolean flagMode) {
		this.flagMode = flagMode;
	}

	//método para checar se a célula tem bomba
	
	public boolean checkCelula() {
		return getBomb();	
	}
	
	public boolean checkSelected() {
		return getSelected();
	}
	
	//método para adicionar bomba na célula
	
	public void addBombCelula() {
		this.setBomb(true);
	}
	
	//método para selecionar célula
	
	public void selecting() { 
		this.setSelected(true);
	}
	
	//método para colocar bandeira na célula
	
	public void addFlag() {
		this.setFlag(true);
	}
	
	//método para tirar bandeira da célula
	
	public void removeFlag() {
		this.setFlag(false);
	}
	
	//método para printar as bombas e células
	
	@Override
	public String toString() {
		if ((checkSelected()) && (checkCelula())) {
			return "☼";
		}
		else if ((checkSelected()) && (!checkCelula())) {
		return "";
		}
		else {
			return "-";
		}
	}
}
