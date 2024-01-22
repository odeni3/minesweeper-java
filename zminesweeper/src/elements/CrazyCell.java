package elements;

public class CrazyCell extends Cell {

	private boolean crazyCell;
	
	public CrazyCell() {
		addCrazyCell();
	}
	
	public boolean getCrazyCell(){
		return this.crazyCell;
	}
	
	public void addCrazyCell() {
		this.crazyCell = true;
	}
	
	
}