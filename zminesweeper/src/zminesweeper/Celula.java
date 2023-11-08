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
	public boolean getFlag() {
		return this.hasBomb;
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
	
	//isso só foi usado para testar se as bombas realmente estavam funcionando, mas podemos deixar salvo para utilizar para mostrar as bombas no fim do jogo
	
	@Override
	public String toString() {
		/*if (hasBomb) {
			return "☼";
		}
		else {
			if ((!checkCelula()) && selected) {
				return "+";
			}
			else {
				return "-";
			}
		}*/
		if ((selected == true) && (hasBomb == true)) {
			return "☼";
		}
		else if ((selected == true) && (hasBomb == false)) {
				return "+";
		}
		else {
			return "-";
		}
	}
}
