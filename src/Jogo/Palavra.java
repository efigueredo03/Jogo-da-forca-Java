package Jogo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Palavra {

	private Map<String, ArrayList<String>> mapPalavras = new HashMap<String, ArrayList<String>>();
	
	public Palavra() {
		this.adicionarTemasEPalavras();
	}
	
	private void adicionarTemasEPalavras() {
		ArrayList<String> frutas = new ArrayList<String>();
		frutas.add("banana");
		frutas.add("maça");
		frutas.add("melancia");
		frutas.add("jabuticaba");
		frutas.add("amora");
		frutas.add("abacaxi");
		frutas.add("uva");
		frutas.add("melao");
		frutas.add("tomate");
		frutas.add("kiwi");
		frutas.add("abacate");
		frutas.add("manga");
		frutas.add("laranja");
		
		ArrayList<String> geral = new ArrayList<String>();
		geral.add("televisao");
		geral.add("martelo");
		geral.add("computador");
		geral.add("xicara");
		geral.add("prato");
		geral.add("aviao");
		geral.add("montanha russa");
		geral.add("refrigerante");
		geral.add("teclado");
		geral.add("guitarra");
		geral.add("avenida");
		geral.add("telefone");
		geral.add("cadeira");
		geral.add("predio");
		geral.add("escola");
		geral.add("caderno");
		geral.add("picole");
		geral.add("trem");
		geral.add("planeta");
		
		this.mapPalavras.put("geral", geral);
		this.mapPalavras.put("frutas", frutas);
				
	}
	
	public String gerarPalavra(String tema) {
		ArrayList<String> lista = this.mapPalavras.get(tema);
		Random geradorAleatorio = new Random();
		int valorAleatorio = geradorAleatorio.nextInt(lista.size());
		return lista.get(valorAleatorio);
	}
	
	public String gerarPalavraEscondida(String palavra) {
		String palavraEscondida = "";
		for (int i = 0; i < palavra.length(); i++) {
			palavraEscondida += "_ ";
		}
		return palavraEscondida;
	}
	
}
