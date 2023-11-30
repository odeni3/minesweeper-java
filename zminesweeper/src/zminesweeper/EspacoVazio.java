package zminesweeper;

public class EspacoVazio extends Celula {

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
