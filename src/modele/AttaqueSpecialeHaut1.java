package modele;

import physique.collider.*;

public class AttaqueSpecialeHaut1 extends AttaqueBase{

	private boolean isRight = true;
	private double decX;
	private double decY;
	
	public AttaqueSpecialeHaut1(Personnage p, double damage, double str) {
		super(p,damage,str);

		
		super.dureeDeVie = 6;
		
		decY= 0.8*p.getCollider().getTailles()[1];
		decX= 0.2*p.getCollider().getTailles()[0];
		
		if (p.estGauche()) {
			isRight = false;
			decX = -decX;
		}
		
		
		
		super.position.setLocation(p.getPosition().getX()+decX,p.getPosition().getY()-decY);																		
		super.collider = new Cercle(super.position,0.3*p.getCollider().getTailles()[0]);
		double[] v = {0,0};
		super.vitesse = v;		
		
		p.additionnerVitesse(1, -2*p.getVitesseMax()[1]/3);
	}
	
	public boolean evoluerAttaque() {
		dureeDeVie--;
		super.position.setLocation(getProprietaire().getPosition().getX()+decX,getProprietaire().getPosition().getY()-decY);
		return false;
	}


	public void attaquer(Personnage personnage) {
		if (super.proprietaire!=personnage) {
			personnage.additionnerPourcentage(super.degats);
			personnage.setEtat(Personnage.SUBIT_ATTAQUE,1);
		}
	}


	public void attaquer(Plateforme plateforme) {
	}


	public void attaquer(Attaque attaque) {
	}


	public boolean estProjectile() {
		return false;
	}


	public boolean getOrientation() {
		return isRight;
	}


	public String getNom() {
		return "AttaqueSpecialeHaut1";
	}

	@Override
	public void affecter(Personnage personnage) {
		// TODO Auto-generated method stub
		
	}

}