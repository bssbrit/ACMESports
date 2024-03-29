//Imports

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
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
		mostrarAtletas();
		mostrarMedalha();
		comissaoPais();
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

			if(medalha == null) {
				codigo = entrada.nextInt();
			} else{
				Atleta atleta = plantel.consultaAtleta(numero);
				if(atleta != null){
					medalha.adicionaAtleta(atleta);
					atleta.adicionaMedalha(medalha);
					System.out.println("3:" + codigo + "," + numero);
					codigo = entrada.nextInt();
				}
				else{
					codigo = entrada.nextInt();
				}
			}
		}
	}


private void mostrarAtletas() {
	int numero;
	String nome;
	numero = entrada.nextInt();
    Atleta atleta = plantel.consultaAtleta(numero);
    if(atleta == null) {
		System.out.println("4:Nenhum atleta encontrado.");
	} else{
		System.out.println("4:" + atleta.getNumero() +"," +atleta.getNome() + "," + atleta.getPais());
	}
	entrada.nextLine();
    nome = entrada.nextLine();
	atleta = plantel.consultaAtleta(nome);
	if(atleta == null){
		System.out.println("5:Nenhum atleta encontrado.");
	}else{
		System.out.println("5:" + atleta.getNumero() +"," +atleta.getNome() + "," + atleta.getPais());
	}
	}

/*
* 7. Mostrar os dados dos atletas de um determinado país: lê o nome de um país.
Se não existir nenhum país com o nome indicado, mostra a mensagem de erro:
“7:Pais nao encontrado.”. Se existir, mostra os dados de cada atleta no
formato: 7:número,nome,país*/
	private void comissaoPais(){
		String pais;
		pais = entrada.nextLine();
		ArrayList<Atleta> comissao =  plantel.comissaoAtletica(pais);
		if(comissao.isEmpty()){
			System.out.println("7:Pais nao encontrado.");
		} else{
			for(Atleta atleta : comissao) {
				System.out.println("7:" + atleta.getNumero() + ","+atleta.getNome()+","+atleta.getPais());
			}
		}
	}
/** Mostrar os dados de uma determinada medalha: lê um código de medalha. Se
 não existir uma medalha com o código indicado, mostra a mensagem de erro:
 “6:Nenhuma medalha encontrada.”. Se existir, mostra os dados da medalha
 no formato: 6:codigo,tipo,é individual?,modalidade*/
	private void mostrarMedalha() {
		int codigo;
		codigo = entrada.nextInt();
		Medalha medalha = medalheiro.consultaMedalha(codigo);
	    if(medalha == null){
			System.out.println("6:Nenhuma medalha encontrada.");
		}else {
			System.out.println("6:" + medalha.getCodigo() +"," +medalha.getTipo() + "," + medalha.getIndividual() + "," + medalha.getModalidade());
		}
		entrada.nextLine();
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
