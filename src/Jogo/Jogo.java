package Jogo;

import java.util.HashMap;
import java.util.Map;

public class Jogo {
	private Palavra geradorDePalavra;
	private ReceberEntrada receiver;
	private String palavra;
	private String palavraEscondida;
	private String tema;
	private int chances;
	private int MaximoDeChances;
	
	public Jogo() {
		this.receiver = new ReceberEntrada();
		this.geradorDePalavra = new Palavra();
		this.tema = this.iniciarJogo();
		int numeroMaximoDeChances = this.selecionarDificuldade();
		this.palavra = this.geradorDePalavra.gerarPalavra(this.tema);
		this.palavraEscondida = this.geradorDePalavra.gerarPalavraEscondida(palavra);
		this.chances = numeroMaximoDeChances;
		this.MaximoDeChances = numeroMaximoDeChances;
	}
	
	
	private String iniciarJogo() {
		this.receiver.modeloDeTexto("| JOGO DA FORCA |");
		this.receiver.modeloDeTexto("| TEMAS |");
		this.receiver.modeloDeTexto("| (Frutas) (Geral) |");
		String resposta;
		while (true) {
			System.out.print("---> ");
			resposta = this.receiver.receberResposta();
			if (resposta.toLowerCase().equals("frutas") || resposta.toLowerCase().equals("geral")) {
				return resposta.toLowerCase();
			}
			this.receiver.modeloDeTexto("| Insira uma opção válida |");
		}
	}
	
	private int selecionarDificuldade() {
		this.receiver.modeloDeTexto("| DIFICULDADE |");
		this.receiver.modeloDeTexto("| (Facil - 8 Chances) |");
		this.receiver.modeloDeTexto("| (Medio - 6 Chances) |");
		this.receiver.modeloDeTexto("| (Dificil - 4 Chances) |");
		String resposta;
		Map<String, Integer> map = new HashMap<>();
		map.put("facil", 8);
		map.put("medio", 6);
		map.put("dificil", 4);
		while (true) {
			System.out.print("---> ");
			resposta = this.receiver.receberResposta();
			if (resposta.toLowerCase().equals("facil") || resposta.toLowerCase().equals("medio") || resposta.toLowerCase().equals("dificil")) {
				return map.get(resposta.toLowerCase());
			}
			this.receiver.modeloDeTexto("| Insira uma opção válida |");
		}
	}
	
	public void jogar() {
		
		String resposta;
		boolean verificacaoDeJogo;
		
		while (true) {
			this.receiver.modeloDeTexto("| INSTRUÇÕES |");
			this.receiver.modeloDeTexto("| (JOGAR) - Continuar jogando |");
			this.receiver.modeloDeTexto("| (SAIR) - Encerrar o jogo |");
		
			// Se o jogador não inserir uma das duas respotas possíveis, o laço começa do inicio.
			System.out.print("---> ");
			resposta = this.receiver.receberResposta();
			if (!resposta.toLowerCase().equals("jogar") && !resposta.toLowerCase().equals("sair")) {
				this.receiver.modeloDeTexto("| OPÇÃO INVÁLIDA |");
				continue;
			}
			verificacaoDeJogo = Verificacao.verificarSairContinuarJogador(resposta);
			
			// É pedido ao jogador uma resposta. Caso seja jogar, o jogo começa. Caso seja sair, o jogo encerra.			
			while (verificacaoDeJogo) {
				
				String palavraEscondidaSave;
			
				// Se o número de chances for 0, a verificação retorna false e encerra o jogo		
				// Se a verificação retornar true, ele permite que o jogador realize uma nova jogada.
				if (Verificacao.verificarJogo(this.chances)) {
					
					// Receber a jogada o jogador
					this.receiver.modeloDeTexto("| " + this.palavraEscondida + "|");
					System.out.println();
					String jogada = this.receiver.receberJogada();
					
					// Verificação de jogada repetida, se a verificação retornar true, significa que a jogada se repetiu e continua o loop do inicio.
					if (Verificacao.verificarJogadaRepetida(this.palavraEscondida, jogada)) {
						continue;
					}
					
					// A palavraEscondidaSave é atualizada para a nova versão da palavraEscondida
					palavraEscondidaSave = String.copyValueOf(this.palavraEscondida.toCharArray());
					
					// A palavra Escondida é atualizada caso o jogador acerte o chute, ou será mantida caso ele erre.
					this.palavraEscondida = Verificacao.verificarPalavra(this.palavraEscondida, this.palavra, jogada);
					
					// Se a palavraEscondida for mantida, significa que o jogador errou seu chute, portanto ela é igual a palavraEscondidaSave.
					// Então suas chances diminuem em 1.
					if (palavraEscondidaSave.equals(this.palavraEscondida)) {
						this.receiver.modeloDeTexto("| ERROU |");
						this.chances--;
						this.receiver.modeloDeTexto(String.format("| CHANCES %d |", this.chances));				
					}

					// Verificação de jogo, se a palavra escondida não tiver a String "_ ", significa que a palavra foi completada. O jogador venceu.
					if (Verificacao.verificarSituacao(this.palavraEscondida)) {
						this.receiver.modeloDeTexto("| " + this.palavraEscondida + "|");
						this.receiver.modeloDeTexto("| VOCÊ VENCEU |");
						System.out.println();
						break;
					}
					continue;
				}
				this.receiver.modeloDeTexto("| SOLUÇÃO -> " + this.palavra + " |");
				this.receiver.modeloDeTexto("| FIM DE JOGO |");
				System.out.println();
				break;
			}
			if (verificacaoDeJogo) {
				this.palavra = this.geradorDePalavra.gerarPalavra(this.tema);
				this.palavraEscondida = this.geradorDePalavra.gerarPalavraEscondida(palavra);
				this.chances = this.MaximoDeChances;
				continue;
			}
			break;
		}
		this.receiver.fecharScan();
	}
	
}
