package modele;

import physique.collider.*;

public class RieAttaqueHadoken extends AttaqueBase {
	double distanceMax = 100;
	double vitesseX = 400;
	double decX;
	int dureeMax;


	public RieAttaqueHadoken(Personnage p, double damage, double str) {
		super(p,damage,str);

		dureeDeVie = 6000;
		dureeMax =  6000;
		duree = 20;
		decX = 1.3*super.proprietaire.getCollider().getTailles()[0];
		if (p.estGauche()){
			decX=-decX;
		}
		
		super.position = new Point();
		super.position.translater(p.getPosition().getX()+decX, p.getPosition().getY()+-0.25*super.proprietaire.getCollider().getTailles()[1]);
		super.collider=new Rectangle(0,0,super.position);
		
		double[] v = {0,0};
		super.vitesse = v;
		
	}
	
	
	public boolean evoluerAttaque() {
		boolean physique = true;

		if (dureeDeVie==dureeMax){
			super.proprietaire.setEtat(Personnage.EN_ATTAQUE, 6);
		}
		
		
		if (dureeDeVie>=dureeMax-4) {
			physique = false;
		} else if(dureeDeVie==dureeMax-5) {
			double[] v = {vitesseX,0};
			if (!getOrientation()) {
				v[0]=-v[0];
			}
			super.vitesse = v;
			super.collider.setTailleX(0.6*super.proprietaire.getCollider().getTailles()[0]);
			super.collider.setTailleY(0.4*super.proprietaire.getCollider().getTailles()[1]);		
		} else if(dureeDeVie<dureeMax-5) {
			//double[] v = {vitesseX,0};
			//if (!getOrientation()) {
			//	v[0]=-v[0];
			//}
			//super.vitesse = v;
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
			}
		dureeDeVie=0;
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
		return "Hadoken";
	}

	public void affecter(Personnage personnage) {
		double baseProjection = (force/personnage.getMasse())*(1+(personnage.getPourcentage()/100));
		if (dureeDeVie<=dureeMax-5){
			if (getOrientation()) {
				personnage.setVitesseCourante(0.7*baseProjection, -0.3*baseProjection);
			} else {
				personnage.setVitesseCourante(-0.7*baseProjection, -0.3*baseProjection);
			}
		}
	}
}