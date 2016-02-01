package modele;

import physique.collider.*;

public class RockAttaqueBouleMagique extends AttaqueBase {
		private int nbRebondsMax = 5;//nb de Rebonds max restants
		private int nbRebonds;
		private double vitesseX = 400;
		private double effetG = 28;
	
	public RockAttaqueBouleMagique(Personnage p, double damage,double str ) {
			super(p,damage,str);

			nbRebonds = nbRebondsMax;
			super.dureeDeVie = nbRebondsMax*40;
			super.duree=25;
			
			super.position = new Point();
			super.position.setLocation(0, -50);
			
			double taille = 0.5*p.getCollider().getTailles()[0];
			super.collider = new Cercle(position, taille);
			
			double[] v = {0,0};
			super.vitesse = v;
			super.orientation = p.getOrientation();
			
	}
	

	public boolean evoluerAttaque() {
		boolean physique = false;
		if (dureeDeVie==nbRebondsMax*40){
			super.proprietaire.setEtat(Personnage.EN_ATTAQUE, 6);
		}
		if(dureeDeVie==(nbRebondsMax*40)-5) {
			physique = true;
			double decY = 0.4*proprietaire.getCollider().getTailles()[1];
			double decX = 1.5*proprietaire.getCollider().getTailles()[0];
			if (!getOrientation()) {
				decX = -decX;
				vitesseX=-vitesseX;
			}
			super.position.setLocation(proprietaire.getPosition().getX()+decX,proprietaire.getPosition().getY()-decY);
			setVitesse(vitesseX,super.vitesse[1]+effetG);
		}
		if (dureeDeVie<(nbRebondsMax*40)-5) {
			physique = true;
			setVitesse(vitesse[0],super.vitesse[1]+effetG);
		}
		dureeDeVie--;
		if (nbRebonds ==0) {
		  dureeDeVie=0;
		}
		
		return physique;
	}

	public void attaquer(Personnage personnage) {
		if(dureeDeVie<=(nbRebondsMax*40)-5) {
			personnage.additionnerPourcentage(super.degats);
			personnage.setEtat(Personnage.SUBIT_ATTAQUE, 5);
			dureeDeVie=0;
		}
	}

	public void attaquer(Plateforme plateforme) {
		if (super.vitesse[0]==-vitesseX) {
			setOrientation(!getOrientation());
			nbRebonds--;
		}
	}

	public void attaquer(Attaque attaque) {
		this.tuer();
		if (attaque.estProjectile() && getPriorite()<=attaque.getPriorite()) {
			attaque.tuer();
		}
	}

	public boolean estProjectile() {
		return true;
	}

	public String getNom() {
		return "BouleMagique";
	}	

	public void affecter(Personnage personnage) {
		double baseProjection = (force/personnage.getMasse())*(1+(personnage.getPourcentage()/100));
		if (dureeDeVie<=(nbRebondsMax*40)-5) {
			if (getOrientation()) {
				personnage.setVitesseCourante(0.7*baseProjection, -0.3*baseProjection);
			} else {
				personnage.setVitesseCourante(-0.7*baseProjection, -0.3*baseProjection);
			}
		}
		
	}
}
