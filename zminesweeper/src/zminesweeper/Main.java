package zminesweeper;

import java.util.Scanner;

public class Main {
	
	//definindo método main

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Board tabMain =  new Board(7,7,10);
		tabMain.startGame();
		System.out.println(tabMain);
		
		System.out.println("Digite a linha:");
		int i = sc.nextInt();
		System.out.println("Digite a coluna:");
		int j = sc.nextInt();
		System.out.println("Digite a sua inteção: digite -----> 0 para revelar a casa ou digite -----> 1 para jogar bandeira:");
		int k = sc.nextInt();
		
		tabMain.userSelect(i,j,k);
		
		while (true) {
			System.out.println(tabMain);
			System.out.println("Digite a linha:");
			i = sc.nextInt();
			System.out.println("Digite a coluna:");
			j = sc.nextInt();
			System.out.println("Digite a sua inteção: digite -----> 0 para revelar a casa ou digite -----> 1 para jogar bandeira:");
			k = sc.nextInt();
			tabMain.userSelect(i,j,k);
		}
	}
}
