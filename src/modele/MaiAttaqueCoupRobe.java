package modele;


import physique.collider.*;

public class MaiAttaqueCoupRobe extends AttaqueBase {
	
	private double decX;
	private double decY;
	private double strTemp;
	private double force;
	
	public MaiAttaqueCoupRobe(Personnage p, double damage, double str) {
			super(p,damage,0);
			force =str;
			strTemp=0;
			
			super.dureeDeVie = 6;
			duree = 15;
			
			decY= 0.25*p.getCollider().getTailles()[1];
			
			decX= 1*p.getCollider().getTailles()[0];
			if (p.estGauche()) {
				decX=-decX;
			}
			
			//translation
			super.position=new Point();
			super.position.setLocation(p.getPosition().getX()+decX,p.getPosition().getY()+decY);																		
			super.collider = new Rectangle(1.2*p.getCollider().getTailles()[0],0.4*p.getCollider().getTailles()[1],super.position);
			double[] v = {0,0};
			super.vitesse = v;		
	}

	public double getForce() {
		return strTemp;
	}
	
	public boolean evoluerAttaque() {
		switch (super.dureeDeVie) {
		case 6:
		break;
		case 5:
		break;
		case 4:
			strTemp = force;
			//force = strTemp;
		break;
		case 3:
			decX= 1.5*proprietaire.getCollider().getTailles()[0];
			if (proprietaire.estGauche()) {
				decX=-decX;
			}
			super.position.setLocation(proprietaire.getPosition().getX()+decX,proprietaire.getPosition().getY()+decY);
			super.collider.setTailleX(super.collider.getTailles()[0]*1.5);
			super.collider.setTailleY(super.collider.getTailles()[1]*0.8);
		break;
		case 2:
			decX= 1.2*proprietaire.getCollider().getTailles()[0];
			if (proprietaire.estGauche()) {
				decX=-decX;
			}	
			super.position.setLocation(proprietaire.getPosition().getX()+decX,proprietaire.getPosition().getY()+decY);
			super.collider.setTailleX(super.collider.getTailles()[0]*1.5);
			super.collider.setTailleY(super.collider.getTailles()[1]*0.8);
		break;
		case 1:
			strTemp =0;
		default:
		break;
		}
		dureeDeVie--;
		return false;
	}


	
	public void attaquer(Personnage personnage) {
			if (dureeDeVie>1 && dureeDeVie<5) {
				if (!personnagesTouches.contains(personnage)) {
					personnage.additionnerPourcentage(getDegats());
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
		return "MaiAttaqueCoupRobe";
	}

	public void affecter(Personnage personnage) {
		double baseProjection = (strTemp/personnage.getMasse())*(1+(personnage.getPourcentage()/100));
		if (dureeDeVie>1 && dureeDeVie<5) {
			if (getOrientation()) {
				personnage.setVitesseCourante(0.9*baseProjection, -0.1*baseProjection);
			} else {
				personnage.setVitesseCourante(-0.9*baseProjection, -0.1*baseProjection);
			}
		}
	}
	
}