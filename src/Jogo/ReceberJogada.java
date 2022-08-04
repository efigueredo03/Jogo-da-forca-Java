package Jogo;

import java.util.Scanner;

public class ReceberJogada {

	public static String receberJogada() {
		String texto = "| Insira sua jogada |";
		String espaco = "";
		int valor = 50 - texto.length();
		for (int i = 0; i < (valor / 2); i++) {
			espaco.concat("-");
		}
		System.out.format("%s%s%s%n", espaco, texto, espaco);
		Scanner scan = new Scanner(System.in);	
		while (true) {	
			System.out.print("---> ");
			String entrada = scan.nextLine(); 	
			if (entrada.length() != 1) {
				texto = "| Insira somente uma letra |";
				espaco = "";
				valor = 50 - texto.length();
				for (int i = 0; i < (valor / 2); i++) {
					espaco.concat("-");
				}
				System.out.format("%s%s%s%n%n", espaco, texto, espaco);
				continue;
			}
			scan.close();
			return entrada;
		}
	}
	
}
