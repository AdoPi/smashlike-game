package modele;

import jeu.Jeu;
import modele.Point;

public class StageRue extends Stage {
	public final static int HAUTEUR_PLATEFORME = 200;
	public StageRue() {
		super();
		this.ajouterPlateforme(new PlateformeRectangleDeBase(new Point(Jeu.LARGEUR_PX/2,Jeu.HAUTEUR_PX - 200)));
	}

	@Override
	public String getNom() {
		return "StageRue";
	}


}