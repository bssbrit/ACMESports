public class Medalha {

	private int codigo;

	private int tipo;

	private boolean individual;

	private String modalidade;

	private Atleta atleta;

	public Medalha(int codigo,int  tipo,boolean individual,String modalidade) {
		this.codigo = codigo;
		this.tipo = tipo;
		this.individual = individual;
		this.modalidade = modalidade;
	}
	public void adicionaAtleta(Atleta atleta) {
        this.atleta = atleta;
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
