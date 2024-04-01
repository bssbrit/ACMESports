import java.util.ArrayList;

public class Plantel {

	private ArrayList<Atleta> atletas;
    public Plantel(){
		this.atletas = new ArrayList<Atleta>();
	}
	public ArrayList<Atleta> getAtletas(){
		return this.atletas;
	}
	public boolean cadastraAtleta(Atleta atleta) {
		if(consultaAtleta(atleta.getNumero()) == null){
			atletas.add(atleta);
			return true;
		}
		return false;

	}


	/** 7. Mostrar os dados dos atletas de um determinado país: lê o nome de um país.
	Se não existir nenhum país com o nome indicado, mostra a mensagem de erro:
			“7:Pais nao encontrado.”. Se existir, mostra os dados de cada atleta no
	formato: 7:número,nome,país*/
	public ArrayList<Atleta> comissaoAtletica(String pais){
		ArrayList<Atleta> comissao =  new ArrayList<Atleta>();
		for(Atleta atleta : atletas){
			if(atleta.getPais().equals(pais)){
				comissao.add(atleta);

			}
		}
		return comissao;
	}
	public Atleta consultaAtleta(String nome) {
		if(this.atletas.isEmpty()){
			return null;
		}
		Atleta atletaAchado;
		for (Atleta atleta : atletas) {
			atletaAchado = atleta;
			if (atletaAchado.getNome().equals(nome)) {
				return atletaAchado;
			}
		}

		return null;
	}

	public Atleta consultaAtleta(int numero) {
		if(this.atletas.isEmpty()){
			return null;
		}
		Atleta atletaAchado;
		for (Atleta atleta : atletas) {
			atletaAchado = atleta;
			if (atletaAchado.getNumero() == numero) {
				return atletaAchado;
			}
		}


		return null;
	}

}
