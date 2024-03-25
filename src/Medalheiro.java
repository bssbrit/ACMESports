import java.util.ArrayList;

public class Medalheiro {

	private ArrayList<Medalha> medalheiro;
    public Medalheiro() {
		this.medalheiro = new ArrayList<Medalha>();
	}
	public boolean cadastraMedalha(Medalha m) {
		if(consultaMedalha(m.getCodigo()) == null){
			medalheiro.add(m);
			return true;
		}
		return false;
	}

	public Medalha consultaMedalha(int codigo) {
		if(medalheiro.isEmpty()){
			return null;
		}
		Medalha medalhaAchada;
        for (Medalha medalha : medalheiro) {
            medalhaAchada = medalha;
            if (medalhaAchada.getCodigo() == codigo ) {
                return medalhaAchada;
            }
        }
		return null;
	}

	public ArrayList<Medalha> consultaMedalhas(String modalidade) {
		ArrayList<Medalha> subMedalhas = null;
		for(Medalha medalha : medalheiro){
			if(medalha.getModalidade().equals(modalidade)){
				subMedalhas.add(medalha);
			}
		}
		if(!subMedalhas.isEmpty()) {
			return subMedalhas;
		}
		return null;
	}

}
