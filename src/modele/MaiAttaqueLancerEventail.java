package modele;

import physique.collider.*;
import son.MakeSound;

public class MaiAttaqueLancerEventail extends AttaqueBase {
	double distanceMax = 100;
	double vitesseX = 500;
	int dureeMax;
	

	public MaiAttaqueLancerEventail(Personnage p, double damage, double str) {
		super(p,damage,str);
	
		dureeDeVie = (int) (distanceMax/vitesseX);
		dureeMax =  dureeDeVie;
		duree = 20;
		
		super.position = new Point();
		super.position.translater(p.getPosition().getX(), p.getPosition().getY());
		super.collider=new Rectangle(0,0,super.position);
		
		double[] v = {0,0};
		super.vitesse = v;
		
	}
	
	
	public boolean evoluerAttaque() {
		boolean physique = true;
		
		if (dureeDeVie==dureeMax){
			super.proprietaire.setEtat(Personnage.EN_ATTAQUE, 14);
		}
		
		if (dureeDeVie>=dureeMax-4) {
			physique = false;
		} else if(dureeDeVie==dureeMax-5) {
			double[] v = {vitesseX,0};
			if (!getOrientation()) {
				v[0]=-v[0];
			}
			super.vitesse = v;
			super.position.translater(1.3*super.proprietaire.getCollider().getTailles()[0],-0.25*super.proprietaire.getCollider().getTailles()[1]);
			super.collider.setTailleX(super.proprietaire.getCollider().getTailles()[0]);
			super.collider.setTailleY(0.2*super.proprietaire.getCollider().getTailles()[1]);		
		} else if(dureeDeVie<dureeMax-5) {
			double[] v = {vitesseX,0};
			if (!getOrientation()) {
				v[0]=-v[0];
			}
			super.vitesse = v;
		}
		dureeDeVie--;
		return physique;
	}

	public void attaquer(Personnage personnage) {
		if (dureeDeVie<=dureeMax-5) {
			if(!personnagesTouches.contains(personnage)) {
				personnage.additionnerPourcentage(super.degats);
				personnage.setEtat(Personnage.SUBIT_ATTAQUE, 6);
				personnagesTouches.add(personnage);
				dureeDeVie=0;
			}
		}
	}

	public void attaquer(Plateforme plateforme) {
		super.dureeDeVie = 0;
		
	}

	public void attaquer(Attaque attaque) {
		this.tuer();
		if (attaque.estProjectile()) {
			attaque.tuer();
		}
	}

	public boolean estProjectile() {
		return true;
	}


	public String getNom() {
		return "Eventail";
	}

	public void affecter(Personnage personnage) {
		if (dureeDeVie<=dureeMax-5){
			personnage.setVitesseCourante(0, 0);
		}
	}
}