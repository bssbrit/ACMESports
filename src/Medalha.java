import java.util.ArrayList;

public class Medalha {

	private int codigo;

	private int tipo;

	private boolean individual;

	private String modalidade;

	//private Atleta atleta;
	private ArrayList<Atleta> atletas;

	public Medalha(int codigo,int  tipo,boolean individual,String modalidade) {
		this.codigo = codigo;
		this.tipo = tipo;
		this.individual = individual;
		this.modalidade = modalidade;
		atletas = new ArrayList<Atleta>();
	}
	public void adicionaAtleta(Atleta atleta) {
        atletas.add(atleta);
	}
    public ArrayList<Atleta> getAtleta(){
		return this.atletas;
	}
	public int getCodigo(){
		return this.codigo;
	}
	public String getModalidade(){
		return this.modalidade;
	}
    public boolean getIndividual() {
		return this.individual;
	}
	public int getTipo(){
		return this.tipo;
	}
}
