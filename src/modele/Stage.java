package modele;

import java.util.LinkedList;
import java.util.List;

import vue.Affichable;

public abstract class Stage implements Affichable {

	private List<Plateforme> plateformes;

	public Stage () {
		plateformes = new LinkedList<Plateforme>();
	}

	public List<Plateforme> getPlateformes () {
		return this.plateformes;
	}

	public void ajouterPlateforme (Plateforme plateforme) {
		plateformes.add(plateforme);
	}

	public void supprimerPlateforme(Plateforme plateforme) {
		plateformes.remove(plateforme);
	}


	public boolean equals(Object obj) {
		Stage st = (Stage)obj;
		return st.getNom().equals(this.getNom());
	}
	
	@Override
	public Point getPositionTranslatee() {
		return new Point(0,0);
	}
}
