package modele;


import physique.collider.*;

public class MaiAttaquePoing extends AttaqueBase {
	
	private double decX;
	private double decY;
	
	
	public MaiAttaquePoing(Personnage p, double damage, double str) {
			super(p,damage,str);
			
			super.dureeDeVie = 8;
			duree = 10;
			
			decY= 0.25*p.getCollider().getTailles()[1];
			
			decX= p.getCollider().getTailles()[0];
			if (p.estGauche()) {
				decX=-decX;
			}
			
			//translation
			super.position=new Point();
			super.position.setLocation(p.getPosition().getX()+decX,p.getPosition().getY()-decY);																		
			super.collider = new Rectangle(0.8*p.getCollider().getTailles()[0],0.3*p.getCollider().getTailles()[1],super.position);
			double[] v = {0,0};
			super.vitesse = v;		
	}


	public boolean evoluerAttaque() {
		switch (super.dureeDeVie) {
		case 7:
			super.position.translaterX(-decX);
			decX = 1.2*decX;
			super.position.translaterX(decX);
			super.collider.setTailleX(super.collider.getTailles()[0]*1.5);
		break;
		case 6:
			super.position.translater(-decX,decY);
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
		return "MaiAttaquePoing";
	}

	public void affecter(Personnage personnage) {
		if (dureeDeVie>=6) {
			personnage.setVitesseCourante(0, 0);
		}
	}
	
}