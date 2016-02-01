package modele;


import physique.collider.*;

public class MaiAttaqueStripStun extends AttaqueBase {
	
	private double decX;
	private double decY;
	
	
	public MaiAttaqueStripStun(Personnage p, double damage, double str) {
		super(p,damage,str);
		super.dureeDeVie = 9;
		super.duree = 15;
		
		decY= 0;
		decX= 0;
		
		//translation
		super.position = new Point();
		super.position.setLocation(p.getPosition().getX()+decX,p.getPosition().getY()+decY);																		
		super.collider = new Rectangle(p.getCollider().getTailles()[0],p.getCollider().getTailles()[1],super.position);
		double[] v = {0,0};
		super.vitesse = v;	
	}


	public boolean evoluerAttaque() {
		switch (super.dureeDeVie) {
		case 10:
		case 9:
		case 8:
		break;
		case 7:
			position.translater(-decX,0);
			decX=0.5*proprietaire.getCollider().getTailles()[0];
			if (proprietaire.estGauche()) {
				decX=-decX;
			}
			position.translater(decX,0);
			collider.setTailleX(collider.getTailles()[0]*2);
		break;
		case 6:
			position.translater(-decX,0);
			decX=proprietaire.getCollider().getTailles()[0];
			if (proprietaire.estGauche()) {
				decX=-decX;
			}
			position.translater(decX,0);
			collider.setTailleX(collider.getTailles()[0]*1.5);
		break;
		case 5:
			position.translater(-decX,0);
			decX=1.5*proprietaire.getCollider().getTailles()[0];
			if (proprietaire.estGauche()) {
				decX=-decX;
			}
			position.translater(decX,0);
			collider.setTailleX(collider.getTailles()[0]*4/3);
		break;
		default:
		break;
		}
		dureeDeVie--;			
		return false;
	}


	
	public void attaquer(Personnage personnage) {
		if (super.proprietaire!=personnage) {
			if (dureeDeVie<8) {
				if (!super.personnagesTouches.contains(personnage)) {
					personnage.setEtat(Personnage.STUN, (int)(duree*1.5));
					personnage.setEtat(Personnage.SUBIT_ATTAQUE,5);
					super.personnagesTouches.add(personnage);
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
		return "MaiAttaqueStripStun";
	}

	public void affecter(Personnage personnage) {
		if (dureeDeVie<8){
			personnage.setVitesseCourante(0, 0);
		}
	}
	
}