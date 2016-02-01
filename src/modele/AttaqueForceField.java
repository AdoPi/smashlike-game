package modele;

import physique.collider.*;

public class AttaqueForceField extends AttaqueBase {

	public AttaqueForceField(Personnage p, double damage, double str) {
		super(p,damage,str);

		
		super.dureeDeVie = 10;
		
		super.position = p.getPosition();
		
		double taille = p.getCollider().getTailles()[0]/2;
		super.collider = new Cercle(super.position,1*taille);
		
		double[] v = {0,0};
		super.vitesse = v;
		
	}
	
	public boolean evoluerAttaque() {
		switch (super.dureeDeVie) {
		case 9 :
		case 2 :
			super.collider = new Cercle(super.position,super.getCollider().getTailles()[0]*1.1);
			break;
		case 8 :
		case 3 :
			super.collider = new Cercle(super.position,super.getCollider().getTailles()[0]*1.2);
			break;
		case 7 :
			super.collider = new Cercle(super.position,super.getCollider().getTailles()[0]*1.3);
			break;
		case 1 :
			super.collider = new Cercle(super.position,super.getCollider().getTailles()[0]);
			break;
		default :
			break;
		}
			
		super.dureeDeVie--;
		return false;
	}

	public void attaquer(Personnage personnage) {
		personnage.additionnerPourcentage(getDegats());
		personnage.setEtat(Personnage.SUBIT_ATTAQUE, 1);
		personnage.setVitesseCourante(0, 0);	
	}

	public void attaquer(Plateforme plateforme) {
	}

	public void attaquer(Attaque attaque) {
		if (attaque.estProjectile()) {
			attaque.setProprietaire(getProprietaire());
			attaque.setVitesse(-attaque.getVitesseCourante()[0], -attaque.getVitesseCourante()[1]);
		}
	
	}

	public boolean estProjectile() {
		return false;
	}

	public boolean getOrientation() {
		return super.proprietaire.estDroite();
	}

	public String getNom() {
		return "AttaqueForceField";
	}

	@Override
	public void affecter(Personnage personnage) {
		// TODO Auto-generated method stub
		
	}
}