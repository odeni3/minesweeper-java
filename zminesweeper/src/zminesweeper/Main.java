package zminesweeper;

import java.util.Scanner;

import boards.Board;
import boards.CrazyBoard;
import players.Player;

public class Main {

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
        System.out.println("Selecione o modo: 0 (Um jogador) ->  1 (Dois jogadores): ");

        int singleOuMultiplayer = sc.nextInt();
        
        if ((singleOuMultiplayer != 0) && (singleOuMultiplayer != 1)) {
        	System.out.println("Opção inválida. Encerrando o jogo.");
        	System.exit(0);
        }
        
        System.out.println("Selecione o tipo de jogo: 0 (Modo normal) -> 1 (Modo maluco): ");
        
        int normalOuMaluco = sc.nextInt();
        sc.nextLine();
        
        
        switch (normalOuMaluco) {
            case 0:
            	
            	//dois jogadores e campo normal
                if(singleOuMultiplayer == 1) {
                	System.out.println("Digite o nome do primeiro jogador:");
                	String namePlayer1 = sc.nextLine();

                	System.out.println("Digite o nome do segundo jogador:");
                	String namePlayer2 = sc.nextLine();
                	System.out.println();

                    Player player1 = new Player(namePlayer1.toUpperCase(), 0);
                    Player player2 = new Player(namePlayer2.toUpperCase(), 0);

                    Board tabMain = new Board(7,7,10);
                    tabMain.startGame();
                    System.out.println();
                    System.out.println(tabMain);
                   
                    while (true) {
                        playerTurn(tabMain, sc, player1);
                        System.out.println("| " + player1.getName() + " | SCORE: " + player1.getScore());
                        System.out.println("| " + player2.getName() + " | SCORE: " + player2.getScore());
                        System.out.println();
                        playerTurn(tabMain, sc, player2);
                        System.out.println("| " + player1.getName() + " | SCORE: " + player1.getScore());
                        System.out.println("| " + player2.getName() + " | SCORE: " + player2.getScore());
                        System.out.println();
                    }
                	
                }
                
                //um jogador e campo normal
                else if (singleOuMultiplayer == 0){
            		System.out.println("digite o nome do jogador:");
            		String namePlayer1 = sc.nextLine();
            		Player player1 = new Player(namePlayer1.toUpperCase(), 0);
            		Board tabMain = new Board(7,7,10);
            		tabMain.startGame();
            		System.out.println();
            		System.out.println(tabMain);
            		
            		while (true) {
            			playerTurn(tabMain,sc, player1);
            			System.out.println("| " + player1.getName() + " | SCORE: " + player1.getScore());
                        System.out.println();
            			
            		}
                }
                break;
                
            case 1:
            	
            	//um jogador e campo maluco
                if(singleOuMultiplayer == 0) {
                	System.out.println("digite o nome do jogador:");
            		String namePlayer1 = sc.nextLine();
            		Player player1 = new Player(namePlayer1.toUpperCase(), 0);
            		Board tabMain = new CrazyBoard(7,7,10,10);
            		tabMain.startGame();
            		System.out.println();
            		System.out.println(tabMain);
            		
            		while (true) {
            			playerTurn(tabMain,sc, player1);
            			System.out.println("| " + player1.getName() + " | SCORE: " + player1.getScore());
                        System.out.println();
                	
            		}
                }
                
                //dois jogadores e campo maluco
            	else if (singleOuMultiplayer == 1){
            		System.out.println("Digite o nome do primeiro jogador:");
                	String namePlayer1 = sc.nextLine();

                	System.out.println("Digite o nome do segundo jogador:");
                	String namePlayer2 = sc.nextLine();
                	System.out.println();

                    Player player1 = new Player(namePlayer1.toUpperCase(), 0);
                    Player player2 = new Player(namePlayer2.toUpperCase(), 0);

                    Board tabMain = new CrazyBoard(7,7,10,10);
                    tabMain.startGame();
                    System.out.println();
                    System.out.println(tabMain);
                   
                    while (true) {
                        playerTurn(tabMain, sc, player1);
                        System.out.println("| " + player1.getName() + " | SCORE: " + player1.getScore());
                        System.out.println("| " + player2.getName() + " | SCORE: " + player2.getScore());
                        System.out.println();
                        playerTurn(tabMain, sc, player2);
                        System.out.println("| " + player1.getName() + " | SCORE: " + player1.getScore());
                        System.out.println("| " + player2.getName() + " | SCORE: " + player2.getScore());
                        System.out.println();
                    }	
            	}
                break;
                
            default:
                System.out.println("Opção inválida. Encerrando o jogo.");
                System.exit(0);
        }

    }
    
    //definindo método para criar turno de cada jogador
    
    private static void playerTurn(Board tabMain, Scanner sc, Player player) {
    	System.out.println("[ Vez de " + player.getName()+" ]");
    	System.out.println();
        System.out.println("Digite a linha:");
        int i = sc.nextInt();
        System.out.println("Digite a coluna:");
        int j = sc.nextInt();
        System.out.println("Digite: 0 (REVELAR CASA) -> 1 (ADD BANDEIRA) -> 2 (RETIRAR BANDEIRA):");
        int k = sc.nextInt();
        System.out.println();

        tabMain.userSelect(i, j, k, player);
        tabMain.calculateScore(player);
        System.out.println(tabMain);
    }
}
