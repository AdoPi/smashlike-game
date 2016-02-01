package modele;

public class Sauter implements Mouvement {
	/** applique le mouvement sur un joueur
	 * @param p le personnage qui va faire le mouvement
	 **/
	public boolean appliquerMouvement(Personnage p) {
		double v = p.getVitesseCourante()[1];
		double vMax = p.getVitesseMax()[1];
		boolean mouvementApplique = false;
		if ((p.getEtat(Personnage.STUN) == 0 && p.getEtat(Personnage.SUBIT_ATTAQUE) == 0) && p.getEtat(Personnage.SAUT_EFFECTUE)==0) {
			if (p.getNbSauts() == 2) {
				p.decrementerSaut();
				p.setEtat(Personnage.SUR_PLATEFORME,0);
				p.setEtat(Personnage.SAUT_EFFECTUE,13); //cooldown de saut
				p.setVitesseCourante(p.getVitesseCourante()[0],-0.14*p.getVitesseMax()[1]);
				mouvementApplique = true;
			} else if (p.getNbSauts() == 1 && p.getEtat(Personnage.SAUT_EFFECTUE)==0) {
				p.setEtat(Personnage.SAUT_EFFECTUE,20);
				p.decrementerSaut();
				p.setVitesseCourante(p.getVitesseCourante()[0],-0.16*p.getVitesseMax()[1]);
				mouvementApplique = true;
			} else {
			}

		} else {
		}
		return mouvementApplique;
	}
}
