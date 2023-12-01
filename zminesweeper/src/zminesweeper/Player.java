package zminesweeper;

public class Player {
    
	//definindo atributos
	
    private String name;
    private int score;

    //definindo método construtor da bomba
    
    public Player(String nome, int score) {
        this.name = nome;
        this.score = 0;
    }

    //definindo getters e setters
    
    public String getName() {
        return name;
    }
    
    public int getScore() {
        return score;
    }
    
    //definindo método para adicionar pontos
    
    public void addScore(int points) {
        this.score += points;
    }
}

