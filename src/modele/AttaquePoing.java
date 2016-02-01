package modele;


import physique.collider.*;

public class AttaquePoing extends AttaqueBase {
	
	private double decX;
	private double decY;
	
	
	
	public AttaquePoing(Personnage p, double damage, double str) {
		super(p,damage,str);

			
			super.dureeDeVie = 6;
			super.duree = 6;
			
			decY= 0.2*p.getCollider().getTailles()[1];
			
			decX= 0.8*p.getCollider().getTailles()[0];
			if (p.estGauche()) {
				decX=-decX;
			}
			
			//translation
			super.position = new Point();
			super.position.setLocation(p.getPosition().getX()+decX,p.getPosition().getY()-decY);																		
			super.collider = new Cercle(super.position,0.2*p.getCollider().getTailles()[0]);
			double[] v = {0,0};
			super.vitesse = v;
	}


	public boolean evoluerAttaque() {
		//utilisation de la position ici
		switch (getDureeDeVie()) {
		case 2:
		case 5:
			super.position.translaterX(-decX);
			decX=super.proprietaire.getCollider().getTailles()[0];
			if(super.proprietaire.estGauche()) {
				decX=-Math.abs(decX);
			}
			//translation
			super.position.translaterX(decX);
			break;
		case 3:
		case 4:
			super.position.translaterX(-decX);
			decX=1.2*super.proprietaire.getCollider().getTailles()[0];
			if(super.proprietaire.estGauche()) {
				decX=-Math.abs(decX);
			}
			//translation
			super.position.translaterX(decX);
			break;
		case 1:
			System.out.println("attaque morte");
			finirAttaque();
		break;
		default:
		break;		
		}
		dureeDeVie--;
		if (dureeDeVie<=0) {
			dureeDeVie=0;
		}
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
	
	public void attaquer(Attaque attaque){
    }
	
	public boolean estProjectile() {
		return false;
	}
	
	public String getNom() {
		return "AttaquePoing";
	}


	public boolean getOrientation() {
		return super.proprietaire.estDroite();
	}

	public void affecter(Personnage personnage) {
		personnage.additionnerVitesse(0, -0.5);
		
	}
	
	
}


