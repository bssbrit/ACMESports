import java.util.ArrayList;

public class Plantel {

	private ArrayList<Atleta> atletas;
    public Plantel(){
		this.atletas = new ArrayList<Atleta>();
	}
	public boolean cadastraAtleta(Atleta atleta) {
		if(consultaAtleta(atleta.getNumero()) == null){
			atletas.add(atleta);
			return true;
		}
		return false;

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
