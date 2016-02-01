package modele;


import physique.collider.*;

public class MaiAttaqueDash extends AttaqueBase {
	
	private double decX;
	private double decY;
	private double vitesseX = 1;
	private double strengthTmp;
	private double force;
	
	public MaiAttaqueDash(Personnage p, double damage, double str) {
			super(p,damage,0);
			
			strengthTmp = 0;
			force = str;
			
			super.dureeDeVie = 10;
			duree = 20;
			
			decY= 0;
			
			decX= 0.2*p.getCollider().getTailles()[0];
			if (p.estGauche()) {
				decX=-decX;
			}
			
			//translation
			super.position = new Point();
			super.position.setLocation(p.getPosition().getX()+decX,p.getPosition().getY()-decY);																		
			super.collider = new Rectangle(1.4*p.getCollider().getTailles()[0],p.getCollider().getTailles()[1],super.position);
			
			double[] v = {0,0};
			vitesse = v;
			if (p.estGauche()) {
				vitesseX = -vitesseX;
			}
			
			
	}


	public boolean evoluerAttaque() {
		proprietaire.setVitesseCourante(vitesseX,0);
		position.setLocation(proprietaire.getPosition().getX()+decX,proprietaire.getPosition().getY()-decY);
		if(dureeDeVie == 5) {
			super.proprietaire.setVitesseCourante(1.5*vitesseX, 0);
			//force = strength;
			strengthTmp = force;
		}
		
		dureeDeVie--;
		return false;
	}

	public double getForce() {
		return strengthTmp;
	}

	
	public void attaquer(Personnage personnage) {
		
		if (super.proprietaire!=personnage) {
			if (dureeDeVie<=5) {
				if (!personnagesTouches.contains(personnage)) {
					personnage.additionnerPourcentage(super.degats);
					personnage.setEtat(Personnage.SUBIT_ATTAQUE,3);
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
		return "MaiAttaqueDash";
	}

	public void affecter(Personnage personnage) {
		double baseProjection = (strengthTmp/personnage.getMasse())*(1+(personnage.getPourcentage()/100));
		if (proprietaire.estGauche()) {
			baseProjection = -baseProjection;
		}
		if (dureeDeVie<=5) {
			personnage.setVitesseCourante(0.8*baseProjection+vitesseX, -0.2*Math.abs(baseProjection));
		}	
	}
	
}