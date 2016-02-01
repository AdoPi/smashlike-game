package modele;


import physique.collider.*;

public class RieCoupPuissant extends AttaqueBase {
	
	private double decX;
	private double decY;
	
	
	public RieCoupPuissant(Personnage p, double damage, double str) {
		super(p,damage,str);	

			
			super.dureeDeVie = 12;
			duree = 18;
			
			decX= super.proprietaire.getCollider().getTailles()[1]*0.2;
			decY =0;
			
			if (p.estGauche()) {
				decX=-decX;
			}
			
			//translation
			super.position=new Point();
			super.position.setLocation(p.getPosition().getX(),p.getPosition().getY()-0.1*p.getCollider().getTailles()[1]);																		
			super.collider = new Rectangle(0,0,super.position);
			double[] v = {0,0};
			super.vitesse = v;		
	}


	public boolean evoluerAttaque() {
		switch (super.dureeDeVie) {
		case 9:
			super.collider.setTailleX(super.proprietaire.getCollider().getTailles()[0]*1.2);
			super.collider.setTailleY(super.proprietaire.getCollider().getTailles()[1]*0.3);
		case 8:
			super.position.translater(decX,0);
			super.collider.setTailleX(super.proprietaire.getCollider().getTailles()[0]*1.6);
			super.collider.setTailleY(super.proprietaire.getCollider().getTailles()[1]*0.8);
		case 7:
			super.position.translater(decX,0);
		case 6:
			super.collider.setTailleX(0);
			super.collider.setTailleY(0);
		break;
		default:
		break;
		}
		dureeDeVie--;
		return false;
	}


	
	public void attaquer(Personnage personnage) {
			if (dureeDeVie>=6) {
				if (!personnagesTouches.contains(personnage)) {
					personnage.additionnerPourcentage(super.degats);
					personnage.setEtat(Personnage.SUBIT_ATTAQUE,5);
					personnagesTouches.add(personnage);
				}
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
		return "RieCoupPuissant";
	}

	public void affecter(Personnage personnage) {
		double baseProjection = (force/personnage.getMasse())*(1+(personnage.getPourcentage()/100));
		if (dureeDeVie==9 || dureeDeVie==8 || dureeDeVie==7) {
			if (super.proprietaire.estGauche()){
			    personnage.setVitesseCourante(-0.8*baseProjection, -0.2*baseProjection);
			} else {
				personnage.setVitesseCourante(0.8*baseProjection, -0.2*baseProjection);
			}
		}
	}
	
}