package Jogo;

public class Verificacao {

	public static String verificarPalavra(String palavraEscondida, String palavra, String jogada) {
		if (palavra.contains(jogada)) {
			StringBuilder palavraRetorno = new StringBuilder(palavraEscondida);
			int valor = -1;
			while (true) {
				valor = palavra.indexOf(jogada, valor+1);
				if (valor == -1) {
					String resultado = palavraRetorno.toString();
					return resultado;
				}
				palavraRetorno.setCharAt((valor*2), (char) jogada.codePointAt(0));
			}
		} else {
			return palavraEscondida;
		}
	}
	
	public static boolean verificarJogo(int chances) {
		if (chances == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean verificarSituacao(String palavraEscondida) {
		if (palavraEscondida.contains("_")) {
			return false;
		}
		return true;
	}
	
	public static boolean verificarJogadaRepetida(String palavraEscondida, String jogada) {
		if (palavraEscondida.contains(jogada)) {
			return true;
		}
		return false;
	}
	
	public static boolean verificarSairContinuarJogador(String resposta) {
		if (resposta.toLowerCase().equals("jogar")) {
			return true;
		}
		return false;
	}
	
}
