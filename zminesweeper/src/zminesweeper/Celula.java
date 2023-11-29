package zminesweeper;

public abstract class Celula {
	
	//definindo atributos
	
	private boolean hasFlag;
	private boolean selected;
	
	//definindo getters e setters
	
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
	
	//método para checar se a célula está selecionada
	
	public boolean checkSelected() {
		return getSelected();
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
		if (checkFlag()) {
			return "=";
		}
		else {
			return "□";
		}
	}
}