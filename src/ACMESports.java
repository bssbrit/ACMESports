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
		atletasDaMedalha();
		atletasModalidade();
		quemTemMais();
		quadroGeral();
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
			if(plantel.cadastraAtleta(atleta)) {
				System.out.println("1:" + numero + "," + nome + "," + pais);
			}
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
			if(medalheiro.cadastraMedalha(medalha)) {
				System.out.println("2:" + codigo + "," + tipo + "," + individual + "," + modalidade);
			}
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
   private void atletasModalidade() {
	   boolean confirm = false;
	   String modalidade;
	   modalidade = entrada.nextLine();
	   ArrayList<Medalha> medalhas = medalheiro.consultaMedalhas(modalidade);
	   if (medalhas == null || medalhas.isEmpty() ) {
		   System.out.println("9:Modalidade nao encontrada.");
	   } else {
		   for (Medalha medalha : medalhas) {
			   if (medalha.getAtleta().isEmpty()) {
				   System.out.println("9:" + medalha.getModalidade() + "," + medalha.getTipo() + ",Sem atletas com medalha.");
			   }else{
			   for(Atleta atleta: medalha.getAtleta()) {
				   System.out.println("9:" + medalha.getModalidade() + "," + medalha.getTipo() + "," + atleta.getNumero() + "," + atleta.getNome() + "," + atleta.getPais());
			   }
			   }
		   }
	   }
   }
	/*
	Mostrar os dados atletas de um determinado tipo de medalha: lê o tipo de uma
medalha. Se não houver nenhum atleta com o tipo de medalha indicado, mostra a
mensagem de erro: “8:Nenhum atleta encontrado.”, caso contrário, mostra
os dados de cada atleta no formato: 8:número,nome,país
	*/
	private void atletasDaMedalha(){
		boolean confirm = false;
		int tipo;
		tipo = entrada.nextInt();
		entrada.nextLine();
		for(Atleta atleta : plantel.getAtletas()){
			if(atleta.buscarTipoMedalha(tipo)){
				System.out.println("8:"+atleta.getNumero()+","+ atleta.getNome()+","+atleta.getPais());
				confirm = true;
			}
		}
		if(!confirm) {
			System.out.println("8:Nenhum atleta encontrado.");
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

	/*
	* .Mostrar os dados do atleta com mais medalhas: localiza o atleta com maior
número de medalhas. Se não houver atletas com medalhas, mostra a mensagem
de erro: “10:Nenhum atleta com medalha.”. Caso contrário, mostra os dados
do atleta e medalhas no formato:
10:número,nome,país,Ouro:quantidade,Prata:quantidade,Bronze:
quantidade
* */
    private void quemTemMais(){

		Atleta maiorMedalhista = null;
		int contagem = 0;
		int ouro = 0;
		int prata = 0;
		int bronze = 0;
		for(Atleta atleta : plantel.getAtletas()){
				  if(atleta.getMedalhas().size() > contagem){
					  ouro = 0;
					  prata = 0;
					  bronze = 0;
					  maiorMedalhista = atleta;
					  contagem = atleta.getMedalhas().size();
					  for(Medalha medalha : atleta.getMedalhas()){
						  if(medalha.getTipo() == 1){
							  ouro++;
						  }
						  if(medalha.getTipo() == 2){
							  prata++;
						  }
						  if(medalha.getTipo() == 3){
							  bronze++;
						  }
					  }
				  }
			}
		if(contagem == 0) {
			System.out.println("10:Nenhum atleta com medalha.");
		} else{
			System.out.println("10:"+ maiorMedalhista.getNumero() + "," + maiorMedalhista.getNome() + "," + maiorMedalhista.getPais() + ",Ouro:" + ouro + ",Prata:" + prata + ",Bronze:" + bronze);
		}

	}

	/*
	* Mostrar o quadro geral de medalhas por país: nome de cada país, quantidade de
medalhas de ouro, quantidade de medalhas de prata, quantidade de medalha de
bronze.
o Mostrar o quadro geral de medalhas completo: para cada país o seu nome,
quantidade total de medalhas, e dados de cada atleta (nome do atleta e a
quantidade de cada tipo de medalha).
	* */
    public void quadroGeral(){
		ArrayList<String> paises = new ArrayList<String>();
		int ouro ;
		int prata ;
		int bronze;
		int count;
		for(Atleta atleta : plantel.getAtletas()){
			String pais = atleta.getPais();
			if(!paises.contains(pais)){
			paises.add(pais);
			}
		}
		ArrayList<Medalha> medalhasVerificadas = new ArrayList<>() ;
		for(String pais : paises){
			ouro = 0;
			prata = 0;
			bronze = 0;
			count = 0;
			for(Atleta atleta : plantel.comissaoAtletica(pais)) {
				if(!atleta.getMedalhas().isEmpty() ) {
					for(Medalha medalha : atleta.getMedalhas()){
		               if(!medalhasVerificadas.contains(medalha)) {
						   switch (medalha.getTipo()) {
							   case (1):
								   ouro++;
								   count++;
								   break;

							   case (2):
								   prata = prata + 1;
								   count++;
								   break;

							   case (3):
								   bronze++;
								   count++;
								   break;

						   }
						   medalhasVerificadas.add(medalha);
					   }
					}
				}

			}
			System.out.println("11.1:"+pais+",Ouro:"+ ouro+",Prata:"+ prata+",Bronze:"+ bronze);
		}
        QuadroGeralCompleto(paises);
	}

	public void QuadroGeralCompleto(ArrayList<String> paises) {
		ArrayList<Medalha> medalhasVerificadas;
		int ouro,prata,bronze;
		for (String pais : paises) {
			medalhasVerificadas = new ArrayList<>();
			for (Atleta atleta : plantel.comissaoAtletica(pais)) {
				if (!atleta.getMedalhas().isEmpty()) {
					for (Medalha medalha : atleta.getMedalhas()) {
						if (!medalhasVerificadas.contains(medalha)) {
							medalhasVerificadas.add(medalha);
						}
					}
				}
			}
			System.out.println("11.2:"+pais+","+medalhasVerificadas.size() + ",atletas:" );

			for(Atleta atleta : plantel.comissaoAtletica(pais)) {
				ouro = 0;
				prata = 0;
				bronze = 0;
				if (!atleta.getMedalhas().isEmpty()) {
					for (Medalha medalha : atleta.getMedalhas()) {
						switch (medalha.getTipo()) {
							case (1):
								ouro++;
								break;

							case (2):
								prata = prata + 1;
								break;

							case (3):
								bronze++;
								break;

						}

					}
				}
				System.out.println("---"+atleta.getNome()+ ",Ouro:"+ouro+",Prata:"+prata+",Bronze:"+bronze);
                medalhasVerificadas = null;
			}
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
