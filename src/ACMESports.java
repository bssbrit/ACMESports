//Imports

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;


public class ACMESports {

	private Medalheiro medalheiro;
	private Scanner entrada = new Scanner(System.in);
	private PrintStream saidaPadrao = System.out;
	private final String nomeArquivoEntrada = "dadosin.txt";
	private final String nomeArquivoSaida = "dadosout.txt";
	private Plantel plantel;

	public ACMESports() {
      medalheiro = new Medalheiro();
	  plantel = new Plantel();
	  redirecionaES();
	}

	public void executar() {
		cadastraAtletas();
		cadastraMedalhas();
	    distribuirMedalhas();
	}

    private void cadastraAtletas(){
		int numero;
		String nome;
		String pais;
		numero = entrada.nextInt();

		entrada.nextLine();
		while(numero != -1){
			nome = entrada.nextLine();
			pais = entrada.nextLine();
			Atleta atleta = new Atleta(numero,nome, pais);
			plantel.cadastraAtleta(atleta);
			System.out.println("1:"+ numero + "," + nome + "," + pais);

			numero = entrada.nextInt();
			entrada.nextLine();
		}
	}
	private void cadastraMedalhas(){
		Medalha x = new Medalha(122,1,true,"AAA");
		int codigo;
		int tipo;
		boolean individual;
		String modalidade;
		codigo = entrada.nextInt();
		entrada.nextLine();
		while(codigo != -1){
			tipo = entrada.nextInt();
			individual = entrada.nextBoolean();
			entrada.nextLine();
			modalidade = entrada.nextLine();
			Medalha medalha = new Medalha(codigo,tipo, individual,modalidade);
			medalheiro.cadastraMedalha(medalha);
			System.out.println("2:"+ codigo + "," + tipo + "," + individual + "," + modalidade);

			codigo = entrada.nextInt();
			entrada.nextLine();
		}
	}

	public void distribuirMedalhas() {
		int codigo;
		int numero;
		codigo = entrada.nextInt();
		while(codigo != -1){
			numero = entrada.nextInt();
			Medalha medalha = medalheiro.consultaMedalha(codigo);
			Atleta atleta = plantel.consultaAtleta(numero);
			medalha.adicionaAtleta(atleta);
			atleta.adicionaMedalha(medalha);
			System.out.println("3:" + codigo + "," + numero);
			codigo = entrada.nextInt();
			entrada.nextLine();
		}
	}
	private void redirecionaES() {
		try {
			BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
			entrada = new Scanner(streamEntrada);   // Usa como entrada um arquivo
			PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
			System.setOut(streamSaida);             // Usa como saida um arquivo
		} catch (Exception e) {
			System.out.println(e);
		}
		Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
		entrada.useLocale(Locale.ENGLISH);   // Ajusta para leitura para ponto decimal
	}
}
