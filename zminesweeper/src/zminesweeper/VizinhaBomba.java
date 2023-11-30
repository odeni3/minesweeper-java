package zminesweeper;

public class VizinhaBomba extends Celula {

    @Override
    public String toString() {
        if (checkSelected()) {
            return "";
        } 
        else {
            return "□";
        }
    }
}

