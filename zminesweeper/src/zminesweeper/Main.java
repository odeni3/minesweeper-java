package zminesweeper;

import java.util.Scanner;

import boards.NormalBoard;
import boards.CrazyBoard;
import boards.HardBoard;
import boards.MediumBoard;
import boards.EasyBoard;
import players.Player;

public class Main {

    public static void main(String[] args) {
    	
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

        System.out.println("Selecione o modo: 0 (Um jogador) -> 1 (Dois jogadores): ");
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
            	
                if (singleOuMultiplayer == 1) {
                	System.out.println("Escolha o tipo de tabuleiro: 0 (EasyBoard) -> 1 (MediumBoard) -> 2 (HardBoard): ");
                    int escolhaTabuleiro = sc.nextInt();
                    sc.nextLine();
                    NormalBoard tabMain = selecionarTabuleiro(escolhaTabuleiro);

                    System.out.println("Digite o nome do primeiro jogador:");
                    String namePlayer1 = sc.nextLine();
                    System.out.println("Digite o nome do segundo jogador:");
                    String namePlayer2 = sc.nextLine();
                    System.out.println();

                    Player player1 = new Player(namePlayer1.toUpperCase(), 0);
                    Player player2 = new Player(namePlayer2.toUpperCase(), 0);

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
                
                else if (singleOuMultiplayer == 0) {
                	System.out.println("Escolha o tipo de tabuleiro: 0 (EasyBoard) -> 1 (MediumBoard) -> 2 (HardBoard): ");
                    int escolhaTabuleiro = sc.nextInt();
                    sc.nextLine();
                    NormalBoard tabMain = selecionarTabuleiro(escolhaTabuleiro);

                    System.out.println("Digite o nome do jogador:");
                    String namePlayer1 = sc.nextLine();
                    Player player1 = new Player(namePlayer1.toUpperCase(), 0);

                    tabMain.startGame();
                    System.out.println();
                    System.out.println(tabMain);

                    while (true) {
                        playerTurn(tabMain, sc, player1);
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
            		
            		CrazyBoard tabMain = new CrazyBoard(12,12,20,49);
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

                    CrazyBoard tabMain = new CrazyBoard(12,12,20,49);
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
    
    private static void playerTurn(NormalBoard tabMain, Scanner sc, Player player) {
        System.out.println("[ Vez de " + player.getName()+" ]");
        System.out.println();
        System.out.println("Digite a linha:");
        int i = sc.nextInt();
        System.out.println("Digite a coluna:");
        int j = sc.nextInt();
        
        int k;
        do {
            System.out.println("Digite: 0 (REVELAR CASA) -> 1 (ADD BANDEIRA) -> 2 (RETIRAR BANDEIRA):");
            k = sc.nextInt();
            if (k < 0 || k > 2) {
            	System.out.println();
                System.out.println("Comando inválido. Digite novamente.");
                System.out.println();
            }
        } while (k < 0 || k > 2);

        System.out.println();

        tabMain.userSelect(i, j, k, player);
        tabMain.calculateScore(player);
        System.out.println(tabMain);
    }

    
    //Adicionando método para escolher o tipo da dificuldade do tabuleiro no modo normal
    
    private static NormalBoard selecionarTabuleiro(int escolha) {
        switch (escolha) {
            case 0:
                return new EasyBoard(6, 6, 10);
            case 1:
                return new MediumBoard(12, 12, 20); 
            case 2:
                return new HardBoard(18, 18, 40); 
            default:
                System.out.println("Opção inválida. Encerrando o jogo.");
                System.exit(0);
                return null; 
        }
    }
}



