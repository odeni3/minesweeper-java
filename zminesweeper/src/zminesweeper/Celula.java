package zminesweeper;

public class Celula {
	
	//definindo atributos
	
	private boolean hasBomb;
	private boolean hasFlag;
	private boolean selected;
	
	//definindo getters e setters
	
	public boolean getBomb() {
		return this.hasBomb;
	}
	public void setBomb(boolean bomb) {
		this.hasBomb = bomb;
	}
	
	public boolean getSelected() {
		return this.selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public boolean getHasFlag() {
		return this.hasFlag;
	}
	public void setHasFlag(boolean hasFlag) {
		this.hasFlag = hasFlag;
	}
	
	//método para ver se tem flag
	
	public boolean checkFlag() {
		return getHasFlag();
	}
	
	//método para colocar flag
	
	public void addFlag() {
		this.setHasFlag(true);
	}
	//método para ver se a célula tem bomba
	
	public boolean checkCelula() {
		return getBomb();	
	}
	
	//método para checar se a célula está selecionada
	
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
	
	//método para deselecionar célula
	
	public void unselecting() {
		this.setSelected(false);
	}

	//método para printar as bombas e células
	
	@Override
	public String toString() {
		if ((checkSelected()) && (checkCelula()) && (!checkFlag())) {
			return "X";
		}
		else if ((checkSelected()) && (!checkCelula()) && (!checkFlag())) {
			return "";
		}
		else if (checkFlag()) {
			return "=";
		}
		else {
			return "□";
		}
	}
}