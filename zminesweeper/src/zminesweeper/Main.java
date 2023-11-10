package zminesweeper;

import java.util.Scanner;

public class Main {
	
	//definindo método main

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Board tabMain =  new Board(7,7,7);
		tabMain.startGame();
		System.out.println(tabMain);
		
		System.out.println("Digite a linha:");
		int i = sc.nextInt();
		System.out.println("Digite a coluna:");
		int j = sc.nextInt();
		
		tabMain.userSelect(i,j);
		System.out.println("tem "+tabMain.calculateBombs()+" bombas ao redor");
		
		while (true) {
			System.out.println(tabMain);
			System.out.println("Digite a linha:");
			i = sc.nextInt();
			System.out.println("Digite a coluna:");
			j = sc.nextInt();
			tabMain.userSelect(i,j);
			System.out.println("tem "+tabMain.calculateBombs()+" bombas ao redor");
		}
	}
}
