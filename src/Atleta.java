import java.util.ArrayList;

public class Atleta {

	private int numero;

	private String nome;

	private String pais;

	private ArrayList<Medalha> medalhas;
     public Atleta(int numero, String nome, String pais) {
		this.numero = numero;
		this.nome = nome;
		this.pais = pais;
		medalhas = new ArrayList<>();
	}
	public void adicionaMedalha(Medalha medalha) {
      medalhas.add(medalha);
	}

	public int consultaQuantidadeMedalhas() {
		 int tamanho = medalhas.size();
		return tamanho;
	}

	public String getNome() {
		return nome;
	}

	public int getNumero(){
		 return numero;
	}
    public String getPais(){
		 return pais;
	}
}
