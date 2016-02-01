package modele;

import physique.collider.*;

public class AttaqueHadoken extends AttaqueBase {
	double vitesseX = 3;
	boolean isRight;

	public AttaqueHadoken(Personnage p, double damage, double str) {
		super(p,damage,str);

	
		super.dureeDeVie = 240;
		
		Point pos = p.getPosition();
		pos.translater(p.getCollider().getTailles()[0]/2,-0.2*p.getCollider().getTailles()[1]/2);
		if (p.estGauche()) {
			pos.setLocation(-pos.getX(), pos.getY());
		}
		super.position = pos;
		
		double taille = 0.3*p.getCollider().getTailles()[0];
		super.collider = new Cercle(pos, taille);
		
		double[] v = {vitesseX,0};
		if (p.estGauche()) {
			v[0] = - v[0];
		}
		super.vitesse = v;
		
		isRight = p.estDroite();
	}
	
	
	public boolean evoluerAttaque() { //rien a faire a part bouger
		return true;
	}

	public void attaquer(Personnage personnage) {
		personnage.additionnerPourcentage(super.degats);
		personnage.setEtat(Personnage.SUBIT_ATTAQUE, 1);		
	}

	public void attaquer(Plateforme plateforme) {
		super.dureeDeVie = 0;
		
	}

	public void attaquer(Attaque attaque) {
	}

	public boolean estProjectile() {
		return true;
	}

	public boolean getOrientation() {
		return isRight;
	}

	public String getNom() {
		return "AttaqueHadoken";
	}


	@Override
	public void affecter(Personnage personnage) {
		// TODO Auto-generated method stub
		
	}
}
