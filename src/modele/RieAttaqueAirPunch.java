package modele;


import physique.collider.*;

public class RieAttaqueAirPunch extends AttaqueBase {
	
	private double decX;
	private double decY;
	//private double strTemp;
	//private double force;
	
	public RieAttaqueAirPunch(Personnage p, double damage, double str) {
			super(p,damage,str);
			/* cette attaque a toujours une force a 0 WTF OU PAS ? */
			
			if (p.getNbSauts()>=0) {
				super.dureeDeVie = 8;
				super.duree = 16;
			} else {
				super.dureeDeVie = 1;
				duree = 1;
			}
			
			decY= -p.getCollider().getTailles()[1]*0.2;
			decX= p.getCollider().getTailles()[0]/2;
			if (!p.getOrientation()) {
				decX = -decX;
			}
			
			//translation
			super.position = new Point();
			super.position.setLocation(p.getPosition().getX()+decX,p.getPosition().getY()+decY);																		
			super.collider = new Rectangle(p.getCollider().getTailles()[0]*1.2,p.getCollider().getTailles()[1],super.position);
			double[] v = {0,0};
			super.vitesse = v;		
	}


	public boolean evoluerAttaque() {
		switch (super.dureeDeVie) {
		case 6:
			proprietaire.setVitesseCourante(0, -0.16*proprietaire.getVitesseMax()[1]);
			proprietaire.setEtat(Personnage.SAUT_EFFECTUE, -1);
		break;
		case 1:
			collider.setTailleX(0);
			collider.setTailleY(0);
			force = 0;
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
		return "RieAttaqueAirPunch";
	}

	public void affecter(Personnage personnage) {
		double baseProjection = (force/personnage.getMasse())*(1+(personnage.getPourcentage()/100));
		if (dureeDeVie>2 && dureeDeVie<6) {
			if (getOrientation()) {
				personnage.setVitesseCourante(0.1*baseProjection, -0.9*baseProjection);
			} else {
				personnage.setVitesseCourante(-0.1*baseProjection, -0.9*baseProjection);
			}
		}
	}
	
}