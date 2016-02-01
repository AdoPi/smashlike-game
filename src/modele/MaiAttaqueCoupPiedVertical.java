package modele;


import physique.collider.*;

public class MaiAttaqueCoupPiedVertical extends AttaqueBase {
	
	private double decX;
	private double decY;
	private double strTemp;
	private double force;
	
	public MaiAttaqueCoupPiedVertical(Personnage p, double damage, double str) {
		
		super(p,damage,0);
		force =str;
		strTemp=0;
		
		super.dureeDeVie = 10;
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

	
	public double getForce() {
		return strTemp;
	}

	public boolean evoluerAttaque() {
		switch (super.dureeDeVie) {
		case 10:
		break;
		case 9:
			decX=0.3*proprietaire.getCollider().getTailles()[0];
			if (proprietaire.estGauche()) {
				decX=-decX;
			}
			position.translater(decX,0);
			strTemp = force;
			//super.force=strTemp; //mettre a jour strTemp
		break;
		case 8:
			decY= 2*decY;
			decX= decX/2;
			position.translater(-decX,-decY);
			collider.setTailleY(collider.getTailles()[1]/2);
		break;
		case 7:
		break;
		case 6:
			position.translater(decX,0);
		break;
		case 5:
			decX= decX/2;
			decY= decY/2;
			position.translater(decX,decY);
			collider.setTailleX(collider.getTailles()[0]*2);
			collider.setTailleY(collider.getTailles()[1]*0.5);
		case 4:
			//force=0;
			strTemp = 0;
		break;
		default:
		break;
		}
		dureeDeVie--;			
		return false;
	}


	
	public void attaquer(Personnage personnage) {
		if (super.proprietaire!=personnage) {
			if (dureeDeVie>4) {
				if (!super.personnagesTouches.contains(personnage)) {
					personnage.additionnerPourcentage(super.degats);
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
		return "MaiAttaqueCoupPiedVertical";
	}


	public void affecter(Personnage personnage) {
		double baseProjection = (strTemp/personnage.getMasse())*(1+(personnage.getPourcentage()/100));
		if (dureeDeVie>4 && dureeDeVie<10){
			personnage.setVitesseCourante(0, -baseProjection);
		}
	}
	
}
