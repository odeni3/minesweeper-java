package zminesweeper;

import java.util.Scanner;

import boards.Board;
import boards.CrazyBoard;
import players.Player;

public class Main {

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("Bem-vindo ao Jogo!");
        System.out.println("Selecione o modo:");
        System.out.println("0 - Modo 1 Jogador");
        System.out.println("1 - Modo 2 Jogadores");

        int escolhaModo = sc.nextInt();

        switch (escolhaModo) {
            case 0:
                System.out.println("Modo 1 Jogador selecionado.");
                break;
            case 1:
                System.out.println("Modo 2 Jogadores selecionado.");
                break;
            default:
                System.out.println("Opção inválida. Encerrando o jogo.");
                sc.close();
                return;
        }

        System.out.println("Selecione o tipo de jogo:");
        System.out.println("0 - Modo Normal");
        System.out.println("1 - Modo Maluco");

        int escolhaTipoJogo = sc.nextInt();
        System.out.println("");
        
        switch (escolhaTipoJogo) {
            case 0:
                System.out.println("Modo Normal selecionado. Iniciando o jogo...");
                if(escolhaModo == 1) {
                	System.out.println("Digite o nome do primeiro jogador:");
                	String namePlayer1 = sc.nextLine();

                	
                	
                	System.out.println("Digite o nome do segundo jogador:");
                	String namePlayer2 = sc.nextLine();
                	System.out.println();

                    Player player1 = new Player(namePlayer1.toUpperCase(), 0);
                    Player player2 = new Player(namePlayer2.toUpperCase(), 0);

                    Board tabMain = new Board(7,7,2);
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
                else {
                	if(escolhaModo == 0) {
                		System.out.println("digite o nome do jogador:");
                		String namePlayer1 = sc.nextLine();
                		Player player1 = new Player(namePlayer1.toUpperCase(), 0);
                		Board tabMain = new Board(7,7,17);
                		tabMain.startGame();
                		System.out.println();
                		System.out.println(tabMain);
                		
                		while (true) {
                			playerTurn(tabMain,sc, player1);
                			System.out.println("| " + player1.getName() + " | SCORE: " + player1.getScore());
                            System.out.println();
                			
                		}
                	}
                }
                break;
            case 1:
                System.out.println("Modo Maluco selecionado. Iniciando o jogo...");
                if(escolhaModo == 0) {
                	
                	System.out.println("digite o nome do jogador:");
            		String namePlayer1 = sc.nextLine();
            		Player player1 = new Player(namePlayer1.toUpperCase(), 0);
            		Board tabMain = new CrazyBoard(7,7,17,1);
            		tabMain.startGame();
            		System.out.println();
            		System.out.println(tabMain);
            		
            		while (true) {
            			playerTurn(tabMain,sc, player1);
            			System.out.println("| " + player1.getName() + " | SCORE: " + player1.getScore());
                        System.out.println();
            			
                	
            		}
                }
            		else {
                	if(escolhaModo == 1) {
                		
                	}
                }
                break;
            default:
                System.out.println("Opção inválida. Encerrando o jogo.");
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
