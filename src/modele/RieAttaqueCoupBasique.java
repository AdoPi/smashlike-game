package modele;

import physique.collider.Rectangle;


public class RieAttaqueCoupBasique extends AttaqueBase {
	
	private double decX;
	private double decY;
	private double avancer;
	
	
	public RieAttaqueCoupBasique(Personnage p, double damage, double str) {
		super(p,damage,str);	
			
			super.dureeDeVie = 10;
			duree = 15;
			
			decY= 0.25*p.getCollider().getTailles()[1];
			
			decX= p.getCollider().getTailles()[0];
			avancer= 0.5;
			
			if (p.estGauche()) {
				decX=-decX;
			}
			
			//translation
			super.position=new Point();
			super.position.setLocation(p.getPosition().getX()+decX,p.getPosition().getY()-decY);																		
			super.collider = new Rectangle(p.getCollider().getTailles()[0],0.3*p.getCollider().getTailles()[1],super.position);
			double[] v = {0,0};
			super.vitesse = v;		
	}


	public boolean evoluerAttaque() {
		switch (super.dureeDeVie) {
		case 7:
			super.position.translaterX(-decX);
			decX = 1.2*decX;
			if (super.proprietaire.estGauche()) {
				avancer=-avancer;
			}
			super.position.translaterX(decX);
			super.collider.setTailleX(super.collider.getTailles()[0]*1.5);
			super.proprietaire.additionnerVitesse(avancer, 0);
		break;
		case 6:
			//passage en recovery;
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
		if (!personnagesTouches.contains(personnage)) {
			personnage.additionnerPourcentage(super.degats);
			personnage.setEtat(Personnage.SUBIT_ATTAQUE,5);
			personnagesTouches.add(personnage);
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
		return "RieAttaqueCoupBasique";
	}

	public void affecter(Personnage personnage) {
		double baseProjection = (force/personnage.getMasse())*(1+(personnage.getPourcentage()/100));
		if (dureeDeVie>=6) {
			if (getOrientation()) {
				personnage.setVitesseCourante(0.7*baseProjection, -0.3*baseProjection);
			} else {
				personnage.setVitesseCourante(-0.7*baseProjection, -0.3*baseProjection);
			}
		}
	}
	
}