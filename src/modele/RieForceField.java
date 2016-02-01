package modele;

import physique.collider.*;
import son.MakeSound;

public class RieForceField extends AttaqueBase {
	double rayon;

	public RieForceField(Personnage p, double damage, double str) {
		super(p,damage,str);
		
		dureeDeVie = 13;
		duree = 20;
		
		rayon = p.getCollider().getTailles()[1]*1.5;
		
		super.position = new Point();
		super.position.translater(p.getPosition().getX(), p.getPosition().getY()+p.getCollider().getTailles()[1]*0.1);
		super.collider=new Cercle(position, rayon/2);
		
		double[] v = {0,0};
		super.vitesse = v;
		
	}
	
	
	public boolean evoluerAttaque() {
		proprietaire.setVitesseCourante(0, 0);
		dureeDeVie--;
		return false;
	}

	public void attaquer(Personnage personnage) {
		if (super.proprietaire!=personnage) {
			if (dureeDeVie>4) {
				if (!super.personnagesTouches.contains(personnage)) {
					personnage.additionnerPourcentage(super.degats);
					personnage.setEtat(Personnage.SUBIT_ATTAQUE,5);
					super.personnagesTouches.add(personnage);
				}
			}
		}
	}

	public void attaquer(Plateforme plateforme) {
	}

	public void attaquer(Attaque attaque) {
		if (attaque.estProjectile() && (attaque.getProprietaire()!=proprietaire)) {
			attaque.setProprietaire(getProprietaire());
			attaque.setVitesse(-1.2*attaque.getVitesseCourante()[0], -1.2*attaque.getVitesseCourante()[1]);
			attaque.setOrientation(!attaque.getOrientation());
		}
	}

	public boolean estProjectile() {
		return true;
	}

	public String getNom() {
		return "Force_Field";
	}
	
	public int getPriorite() {
		return 5;
	}

	public void affecter(Personnage personnage) {
		double baseProjection = (force/personnage.getMasse())*(1+(personnage.getPourcentage()/100));
		if (dureeDeVie>4) {
			if (proprietaire.getPosition().getX()>personnage.getPosition().getX()){
				personnage.additionnerVitesse(-baseProjection, 0);
			} else {
				personnage.additionnerVitesse(baseProjection, 0);
			}
		}
	}
}