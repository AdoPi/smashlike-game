package modele;

import jeu.Jeu;
import modele.Point;

public class StageForet extends Stage {
	public StageForet() {
		super();
		this.ajouterPlateforme(new PlateformeCarrePetite(new Point(Jeu.LARGEUR_PX/2-200,Jeu.HAUTEUR_PX - 350)));
		this.ajouterPlateforme(new PlateformeCarrePetite(new Point(Jeu.LARGEUR_PX/2+200,Jeu.HAUTEUR_PX - 350)));
		this.ajouterPlateforme(new PlateformeRectangleMoyenne(new Point(Jeu.LARGEUR_PX/2,Jeu.HAUTEUR_PX - 450)));
		this.ajouterPlateforme(new PlateformeRectangleDeBase(new Point(Jeu.LARGEUR_PX/2,Jeu.HAUTEUR_PX - 200)));
	}

	@Override
	public String getNom() {
		return "StageForet";
	}


}