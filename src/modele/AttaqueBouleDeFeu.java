package modele;

import physique.collider.*;

public class AttaqueBouleDeFeu extends AttaqueBase {
		private int nbRebondsMax; //nb de Rebonds max restants
		private double vitesseX = 10;
		private double effetG = 5;
		private double memVitesseY;
	
	public AttaqueBouleDeFeu(Personnage p, double damage,double str ) {
			super(p,damage,str);
		
			nbRebondsMax = 10;
			super.dureeDeVie = nbRebondsMax*20;
			
			Point pos = p.getPosition();
			pos.translater(p.getCollider().getTailles()[0]/2,-0.2*p.getCollider().getTailles()[1]/2);
			if (p.estGauche()) {
				pos.setLocation(-pos.getX(), pos.getY());
			}
			super.position = pos;
			
			double taille = 0.2*p.getCollider().getTailles()[0];
			super.collider = new Cercle(pos, taille);
			
			double[] v = {vitesseX,0};
			if (p.estGauche()) {
				v[0] = - v[0];
			}
			super.vitesse = v;
			memVitesseY = super.vitesse[1];
			
	}
	

	public boolean evoluerAttaque() {
		super.dureeDeVie --;
		//compter les rebonds
		if (memVitesseY*super.vitesse[1]<0 && super.vitesse[1]<0) { //inversion de la vitesse via Moteur
			nbRebondsMax--;
		}
		memVitesseY = super.vitesse[1];
		
		//si impact lateral:
		if (super.vitesse[0]!=vitesseX) { //si Moteur a modifier la vitesse en X
			nbRebondsMax --;
			vitesseX = -vitesseX;
			setOrientation(!getOrientation());
		}
		
		//mort par rebonds max:
		if (nbRebondsMax ==0) {
			super.dureeDeVie = 0;
		}
		
		//chute par frame:
		super.vitesse[1] += effetG; 
		
		return true;
	}

	public void attaquer(Personnage personnage) {
		personnage.additionnerPourcentage(super.degats);
		personnage.setEtat(Personnage.SUBIT_ATTAQUE, 1);
	}

	public void attaquer(Plateforme plateforme) {	
	}

	public void attaquer(Attaque attaque) {
	}

	public boolean estProjectile() {
		return true;
	}

	public String getNom() {
		return "AttaqueBouleDeFeu";
	}
	
	@Override
	public void affecter(Personnage personnage) {
		// TODO Auto-generated method stub
		
	}
}