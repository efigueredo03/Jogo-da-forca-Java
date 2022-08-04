package Jogo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Jogo {
	
	private String palavra;
	private String palavraEscondida;
	private int rodada;
	private int rodadaMaxima;
	
	public Jogo() {
		Palavra geradorDePalavra = new Palavra();
//		String tema = this.iniciarJogo();
//		int numeroMaximoDeRodadas = this.selecionarDificuldade();
		this.palavra = geradorDePalavra.gerarPalavra("geral");
		System.out.println(this.palavra);
		this.palavraEscondida = geradorDePalavra.gerarPalavraEscondida(palavra);
		this.rodada = 0;
		this.rodadaMaxima = 8;
	}
	
	private void modeloDeTexto(String output) {
		String texto = output;
		String espaco = "";
		int valor = 50 - texto.length();
		for (int i = 0; i < (valor / 2); i++) {
			espaco += "-";
		}
		System.out.format("%s%s%s%n", espaco, texto, espaco);
	}
	
	private String iniciarJogo() {
		this.modeloDeTexto("| JOGO DA FORCA |");
		this.modeloDeTexto("| TEMAS |");
		this.modeloDeTexto("| (Frutas) - (Geral) |");
		Scanner scan = new Scanner(System.in);
		String resposta;
		while (true) {
			System.out.print("---> ");
			resposta = scan.nextLine();
			if (resposta.toLowerCase().equals("frutas") || resposta.toLowerCase().equals("geral")) {
				scan.close();
				return resposta.toLowerCase();
			}
			this.modeloDeTexto("| Insira uma opção válida |");
		}
	}
	
	private int selecionarDificuldade() {
		this.modeloDeTexto("| DIFICULDADE |");
		this.modeloDeTexto("| (Facil) (Medio) (Dificil) |");
		Scanner scan = new Scanner(System.in);
		String resposta;
		Map<String, Integer> map = new HashMap<>();
		map.put("facil", 8);
		map.put("medio", 6);
		map.put("dificil", 4);
		while (true) {
			System.out.println("\n");
			System.out.print("---> ");
			resposta = scan.nextLine();
			if (resposta.toLowerCase().equals("facil") || resposta.toLowerCase().equals("medio") || resposta.toLowerCase().equals("dificil")) {
				scan.close();
				return map.get(resposta.toLowerCase());
			}
			this.modeloDeTexto("| Insira uma opção válida |");
		}
	}
	
	public void jogar() {
		boolean podeJogar;
		boolean venceu;
		while (true) {
			podeJogar = Verificacao.verificarJogo(this.rodadaMaxima, this.rodada);
			if (podeJogar) {
				System.out.println(this.palavraEscondida);
				System.out.println();
				String jogada = ReceberJogada.receberJogada();
				this.palavraEscondida = Verificacao.verificarPalavra(this.palavraEscondida, this.palavra, jogada);
				venceu = Verificacao.verificarSituacao(this.palavraEscondida);
				if (venceu) {
					this.modeloDeTexto("| VOCÊ VENCEU |");
					break;
				}
				continue;
			}
			this.modeloDeTexto("| FIM DE JOGO |");
			break;
		}
	}
	
}
