package modele;


import physique.collider.*;

public class MaiAttaqueSalto extends AttaqueBase {
	
	private double decX;
	private double decY;
	private double vitesseY;
	private double strengthTmp;
	private double force;
	
	public MaiAttaqueSalto(Personnage p, double damage, double str) {
		super(p,damage,0);
			force = 0;
			strengthTmp = str;
			
			if (p.getNbSauts()>=0) {
				super.dureeDeVie = 10;
				duree = 15;
			} else {
				super.dureeDeVie = 1;
				duree = 1;
			}
			
			decX=0;
			decY=0;
			
			//translation
			super.position = new Point();																
			super.collider = new Cercle(super.position,0);
			
			double[] v = {0,0};
			super.vitesse = v;
			
			vitesseY= 0.15*p.getVitesseMax()[1];
	}


	public boolean evoluerAttaque() {
		super.position.setLocation(proprietaire.getPosition().getX()+decX,proprietaire.getPosition().getY()+decY);
		super.collider = new Cercle(super.position,0);
		switch (dureeDeVie) {
		case 10:
		break;
		case 9 :
			force = strengthTmp;
			proprietaire.setEtat(Personnage.SAUT_EFFECTUE, -1);
			collider.setTailleX(1.2*proprietaire.getCollider().getTailles()[1]);
			proprietaire.setVitesseCourante(0, -vitesseY);
		break;
		case 2:
			force=0;
		break;
		default:
		break;
		}
		dureeDeVie--;
		
		return false;
	}


	
	public void attaquer(Personnage personnage) {
		
		if (super.proprietaire!=personnage) {
			if (dureeDeVie<10 && dureeDeVie>1) {
				if (!personnagesTouches.contains(personnage)) {
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
		return "MaiAttaqueSalto";
	}

	public void affecter(Personnage personnage) {
		double baseProjection = (force/personnage.getMasse())*(1+(personnage.getPourcentage()/100));
		if (dureeDeVie<10 && dureeDeVie>1) {
			personnage.setVitesseCourante(0, -baseProjection);
		}	
	}
	
}