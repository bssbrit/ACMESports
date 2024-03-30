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

	public ArrayList<Medalha> getMedalhas(){
		 return this.medalhas;
	}

	public boolean buscarTipoMedalha(int tipo){
		 boolean confirm = false;
		 for(Medalha medalha : medalhas) {
			 if(medalha.getTipo() == tipo) {
				 confirm = true;
				 return confirm;
			 }
		 }
		 return confirm;
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
