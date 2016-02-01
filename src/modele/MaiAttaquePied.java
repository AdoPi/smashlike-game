package modele;


import physique.collider.*;

public class MaiAttaquePied extends AttaqueBase {
	
	private double decX;
	private double decY;
	private double strTemp;
	private double force;
	
	public MaiAttaquePied(Personnage p, double damage, double str) {
		super(p,damage,str);
			force = str;
			strTemp=0;
			super.dureeDeVie = 7;
			super.duree = 15;
			
			decY= 0.25*p.getCollider().getTailles()[1];
			
			decX= 0;
			
			//translation
			super.position = new Point();
			super.position.setLocation(p.getPosition().getX()+decX,p.getPosition().getY()+decY);																		
			super.collider = new Rectangle(p.getCollider().getTailles()[0],0.5*p.getCollider().getTailles()[1],super.position);
			double[] v = {0,0};
			super.vitesse = v;		
	}


	public boolean evoluerAttaque() {
		switch (super.dureeDeVie) {
		case 6:
		break;
		case 5:
			decX=1.3*proprietaire.getCollider().getTailles()[0];
			if (proprietaire.estGauche()) {
				decX=-decX;
			}
			decY=2.5*decY;
			position.translater(decX,-decY);
			//super.force=strTemp;
			strTemp = force;
			super.collider.setTailleX(proprietaire.getCollider().getTailles()[0]*1.5);
			super.collider.setTailleY(proprietaire.getCollider().getTailles()[0]*1.1);
		break;
		case 3:
			//remettre a 0 strTemp ici ?
			decY= 0.5*decY;
			decX= 0.3*decX;
			position.translater(-decX,decY);
			collider.setTailleY(collider.getTailles()[1]/2);
		break;
		case 2:
			position=proprietaire.getPosition();
			collider.setTailleX(0);
			collider.setTailleY(0);
			//WTF ICI ?
			strTemp = force;
		break;
		default:
		break;
		}
		dureeDeVie--;			
		return false;
	}

	
	public double getForce() {
		return strTemp;
	}

	
	public void attaquer(Personnage personnage) {
		if (super.proprietaire!=personnage) {
			if (!personnagesTouches.contains(personnage)) {
				if (dureeDeVie>2 && dureeDeVie<6) {
					personnage.additionnerPourcentage(getDegats());
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
		return "MaiAttaquePied";
	}


	public void affecter(Personnage personnage) {
		double baseProjection = (strTemp/personnage.getMasse())*(1+(personnage.getPourcentage()/100));
		if (dureeDeVie>2 && dureeDeVie<6) {
			if (getOrientation()) {
				personnage.setVitesseCourante(0.5*baseProjection, -0.5*baseProjection);
			} else {
				personnage.setVitesseCourante(-0.5*baseProjection, -0.5*baseProjection);
			}
		}
	}
	
}