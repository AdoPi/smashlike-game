package modele;


import physique.collider.*;

public class RieAttaqueShoryuken extends AttaqueBase {
	
	private double decX;
	private double decY;
	
	
	public RieAttaqueShoryuken(Personnage p, double damage, double str) {
		super(p,damage,str);
			super.dureeDeVie = 9;
			super.duree = 15;
			
			decY= -p.getCollider().getTailles()[1]*0.5;
			decX= p.getCollider().getTailles()[0];
			if (!getOrientation()) {
				decX = -decX;
			}
			
			
			//translation
			super.position = new Point();
			super.position.setLocation(p.getPosition().getX()+decX,p.getPosition().getY()+decY);																		
			super.collider = new Rectangle(0,0,super.position);
			double[] v = {0,0};
			super.vitesse = v;		
	}


	public boolean evoluerAttaque() {
		switch (super.dureeDeVie) {
		case 8:
			if (getOrientation()) {
				proprietaire.additionnerVitesse(1, 0);
			} else {
				proprietaire.additionnerVitesse(-1, 0);
			}
			position.setLocation(proprietaire.getPosition().getX()+decX,proprietaire.getPosition().getY()+decY*0.8);
			collider.setTailleX(proprietaire.getCollider().getTailles()[0]*0.5);
			collider.setTailleY(proprietaire.getCollider().getTailles()[1]*0.7);
		break;
		case 7:
			position.setLocation(proprietaire.getPosition().getX()+decX*1.5,proprietaire.getPosition().getY()+decY*3);
			collider.setTailleX(proprietaire.getCollider().getTailles()[0]);
			collider.setTailleY(proprietaire.getCollider().getTailles()[1]*1.0);
			break;
		case 4:
			collider.setTailleX(0);
			collider.setTailleY(0);
			break;
		case 1:
			collider.setTailleX(0);
			collider.setTailleY(0);
			break;
		
		default:
		break;
		}
		super.position.setLocation(proprietaire.getPosition().getX()+decX,proprietaire.getPosition().getY()+decY);
		dureeDeVie--;			
		return false;
	}


	
	public void attaquer(Personnage personnage) {
		if (super.proprietaire!=personnage) {
			if (!personnagesTouches.contains(personnage)) {
				if (dureeDeVie>2 && dureeDeVie<6) {
					personnage.additionnerPourcentage(super.degats);
					personnage.setEtat(Personnage.SUBIT_ATTAQUE,5);
					personnagesTouches.add(personnage);
				}
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
		return "RieAttaqueShoryuken";
	}

	public void affecter(Personnage personnage) {
		double baseProjection = (force/personnage.getMasse())*(1+(personnage.getPourcentage()/100));
		if (dureeDeVie>2 && dureeDeVie<6) {
			if (getOrientation()) {
				personnage.setVitesseCourante(0.1*baseProjection,-0.9*baseProjection);
			} else {
				personnage.setVitesseCourante(-0.1*baseProjection,-0.9*baseProjection);
			}
		}
	}
	
}