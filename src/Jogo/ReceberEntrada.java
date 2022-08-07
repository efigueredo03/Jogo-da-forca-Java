package Jogo;

import java.util.Scanner;

public class ReceberEntrada {
	
	Scanner scan;
	
	public ReceberEntrada() {
		this.scan = new Scanner(System.in);
	}
	
	public void modeloDeTexto(String output) {
		String texto = output;
		String espaco = "";
		int valor = 50 - texto.length();
		for (int i = 0; i < (valor / 2); i++) {
			espaco += "-";
		}
		System.out.format("%s%s%s%n", espaco, texto, espaco);
	}
	
	public String receberResposta() {
		return this.scan.nextLine();
	}

	public String receberJogada() {
		this.modeloDeTexto("| INSIRA SUA JOGADA |");
		while (true) {	
			System.out.print("---> ");
			String entrada = this.scan.nextLine(); 	
			if (entrada.length() != 1) {
				this.modeloDeTexto("| INSIRA SOMENTE UMA LETRA |");
				continue;
			}
			return entrada;
		}
	}
	
	public void fecharScan() {
		this.scan.close();
	}
	
}
