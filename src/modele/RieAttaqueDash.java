package modele;

import physique.collider.Rectangle;


public class RieAttaqueDash extends AttaqueBase {
	
	private double decX;
	private double decY;
	private double avancer;
	
	
	public RieAttaqueDash(Personnage p, double damage, double str) {
		super(p,damage,str);	

			super.dureeDeVie = 16;
			duree = 17;
			
			decY= p.getCollider().getTailles()[1];
			
			decX= p.getCollider().getTailles()[0];
			avancer= 1;
			
			if (p.estGauche()) {
				decX=-decX;
			}
			
			//translation
			super.position=new Point();
			super.position.setLocation(p.getPosition().getX()+decX,p.getPosition().getY()-decY*0.1);																		
			super.collider = new Rectangle(0,0,super.position);
			double[] v = {0,0};
			super.vitesse = v;
			if (super.proprietaire.estGauche()) {
				avancer=-avancer;
			}	
	}


	public boolean evoluerAttaque() {
		super.position.setLocation(super.proprietaire.getPosition().getX()+decX, proprietaire.getPosition().getY()-decY*0.1 );
		if (dureeDeVie>6 && dureeDeVie<14){
			proprietaire.setVitesseCourante(avancer, 0);
		}
		switch (super.dureeDeVie) {
		case 13:
			collider.setTailleX(proprietaire.getCollider().getTailles()[0]*1.2);
			collider.setTailleY(proprietaire.getCollider().getTailles()[1]*0.6);
			break;
		case 12:
			super.position.setLocation(super.proprietaire.getPosition().getX()+decX, proprietaire.getPosition().getY()-decY*0.1 );
			break;
		case 6:
			//passage en recovery;
			super.collider.setTailleX(0);
			super.collider.setTailleY(0);
			super.force = 0;
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
		return "RieAttaqueDash";
	}

	public void affecter(Personnage personnage) {
		double baseProjection = (force/personnage.getMasse())*(1+(personnage.getPourcentage()/100));
		if (dureeDeVie>=6) {
			if (getOrientation()) {
				personnage.setVitesseCourante(avancer+0.9*baseProjection, -0.1*baseProjection);
			} else {
				personnage.setVitesseCourante(avancer-0.9*baseProjection, -0.1*baseProjection);
			}
		}
	}
	
}