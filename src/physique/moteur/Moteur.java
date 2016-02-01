package physique.moteur;

import java.util.LinkedList;
import java.util.List;

import jeu.*;
import collision.*;
import modele.*;
import physique.*;



/**Implementation de MoteurPhysique
* Contexte: les paterns des attaques ont deja etaient appliquees par le moteur de jeu */

public class Moteur implements MoteurPhysique {
	
	double gravite; /*constante de gravite*/
	double tpsFrame; /*duree d'une frame*/
	double frt = 0.9; /*coef de frottement de l'air, 0<=frt<1*/

	public Moteur(double g,double d) {
		this.gravite = g;
		tpsFrame = d;
	}

	public List<Collision> majPhysique(Jeu jeu) {
		List<Collision> listeCollisions = new LinkedList<Collision>();
		
		int i =0;
		for(Attaque a:jeu.getAttaques()) {
			majAttaque(a,i,jeu,listeCollisions);
			i++;
			/*ajouter a la liste des collisions les collisions de l'attaque avec les autres elements du jeu*/
		}
		i=0;
		for(Personnage p:jeu.getPersonnages()) {
			majPersonnage(p,i,jeu,listeCollisions);
			/*detecter,ajouter a la liste des collisions et prendre en compte les collisions du personnage avec les autres elements du jeu*/
			/*deplacer le personnage*/
			i++;
		}

		return listeCollisions;
	}


	public void setGravite(int g) {
		this.gravite = g;
	}
	public void setDureeFrame (double d) {
	  	this.tpsFrame = d;
	}

	/** Met a jour une attaque en fonction du jeu.
	/* Teste les collisions avec chaque elements du jeu, et les ajoute a la liste si besoin.
	 * @param a Attaque dont on cherche les collisions avec les autres éléments
	 * @param jeu Jeu dans lequel on cherche les collisions
	 * @param listeCollisions List<Collision> dans laquelle on rajoute les collisions détectées
	 */
	private void majAttaque(Attaque a,int index, Jeu jeu, List<Collision> listeCollisions) {
		boolean testPhysique;
		testPhysique = a.evoluerAttaque();
		if (testPhysique) {
			deplacerAttaque(a);
		}
		
		int i =0;
		for (Attaque atk:jeu.getAttaques()) {
			if (!atk.equals(a)) { /* test a modifier/supprimer selon la politique de gestion des attaques*/
				if (collide(a,atk)) { /*methode de test de collision, utilise les colliders*/
					Point impactPoint=a.getCollider().collidePoint(atk.getCollider());
					int[] index1 = {0,index};
					int[] index2 = {0,i}; 
					Collision collisionTemp = creerCollision(impactPoint,index1,index2);
					if (!listeCollisions.contains(collisionTemp)) { /* pour eviter les doublons*/
						listeCollisions.add(collisionTemp); /*seule action a effectuer pour le attaques*/
					}
				}
			}
			i++;
		}
		i=0;
		for (Personnage perso:jeu.getPersonnages()) {
			if (collide(a,perso)) {
				Point impactPoint=a.getCollider().collidePoint(perso.getCollider());
				int[] index1 = {0,index};
				int[] index2 = {1,i}; 
				Collision collisionTemp = creerCollision(impactPoint,index1,index2);
				majCollision(a, perso,collisionTemp);
				if (!listeCollisions.contains(collisionTemp)) {
					listeCollisions.add(collisionTemp);
					majCollision(a, perso, collisionTemp);
				}
			}
			i++;
		}
		i=0;
		for (Plateforme pf:jeu.getPlateformes()) {
			if (collide(a,pf)) {
				Point impactPoint=a.getCollider().collidePoint(pf.getCollider());
				int[] index1 = {0,index};
				int[] index2 = {2,i}; 
				Collision collisionTemp = creerCollision(impactPoint,index1,index2);
				if (!listeCollisions.contains(collisionTemp)) {
					listeCollisions.add(collisionTemp);
					majCollision(a,pf,collisionTemp);
				}
			}
			i++;
		}


	}

	private void majPersonnage(Personnage p,int index, Jeu jeu,List<Collision> listeCollision) {
		
		int iterPuissance =0;
		double memFrame = tpsFrame;
		double memfrt = frt;
		double[] taille = p.getCollider().getTailles();
		double[] vitesse = p.getVitesseCourante();
		
		boolean conditionX = (Math.abs(vitesse[0]*tpsFrame)<(taille[0]/8));
		boolean conditionY = (Math.abs(vitesse[1]*tpsFrame)<(taille[1]/8));
		boolean conditionVitesse = conditionX && conditionY;
		
		
		while ((iterPuissance<20) && !conditionVitesse) {
			frt = 0;
			iterPuissance++;
			tpsFrame=tpsFrame/2;
			conditionX = (Math.abs(vitesse[0]*tpsFrame)<(taille[0]/2));
			conditionY = (Math.abs(vitesse[1]*tpsFrame)<(taille[1]/2));
			conditionVitesse = conditionX && conditionY;
		}

		for (int j=0;j<((Math.pow(2, iterPuissance)));j++) {
			if (j==((Math.pow(2, iterPuissance))-1)) {
				frt = memfrt;
			}
		/*deplacer le personnage selon sa vitesse*/
		deplacerPersonnage(p);
		/*detecter toute collision du personnage avec un element du jeu
		 * puis modifier sa vitesse si besoin*/
		
		int i =0;
		for (Personnage perso:jeu.getPersonnages()) {
			if (i!=index) {
				if (collide(p,perso)) {
					Point impactPoint=p.getCollider().collidePoint(perso.getCollider());
					int[] index1 = {1,index};
					int[] index2 = {1,i}; 
					Collision collisionTemp = creerCollision(impactPoint,index1,index2);
					if (!listeCollision.contains(collisionTemp)) {
						listeCollision.add(collisionTemp);
						majCollision(p,perso,collisionTemp); /*modifications des vitesses et etats de p et perso causees par collisionTemp*/
					}
				}
				i++;
			}
		}
		
		i=0;
		for (Plateforme pf:jeu.getPlateformes()) {
		
			if (collide(p,pf)) {
				Point impactPoint=p.getCollider().collidePoint(pf.getCollider());
				int[] index1 = {1,index};
				int[] index2 = {2,i}; 
				Collision collisionTemp = creerCollision(impactPoint,index1,index2);
				if (!listeCollision.contains(collisionTemp)) {
					listeCollision.add(collisionTemp);
					majCollision(p,pf,collisionTemp); /*modifications de la vitesse et des etats de p causees par collisionTemp*/
					}
				}
			i++;
			}
		/*System.out.println("-------------------------------------------------------");
		System.out.println(p.getNom());
		System.out.println("Etats:");
		System.out.println("SUR_PLATEFORME: "+p.getEtat(Personnage.SUR_PLATEFORME));
		System.out.println("STUN: "+p.getEtat(Personnage.STUN));
		System.out.println("SAUT_EFFECTUE: "+p.getEtat(Personnage.SAUT_EFFECTUE));
		System.out.println("SUBIT_ATTAQUE: "+p.getEtat(Personnage.SUBIT_ATTAQUE));
		System.out.println("EN_ATTAQUE: "+p.getEtat(Personnage.EN_ATTAQUE));
		System.out.println("INVULNERABLE: "+p.getEtat(Personnage.INVULNERABLE));
		System.out.println("EN_MOUVEMENT: "+p.getEtat(Personnage.EN_MOUVEMENT));
		System.out.println("EN_RECOVERY: "+p.getEtat(Personnage.EN_RECOVERY));
		System.out.println("Position: "+p.getPosition().getX()+":"+p.getPosition().getY());
		System.out.println("Vitesse: "+p.getVitesseCourante()[0]+":"+p.getVitesseCourante()[1]);
		*/
		}
		tpsFrame = memFrame;	
	}


	private Collision creerCollision(Point impactPoint, int[] i1, int[] i2) {
		return new CollisionGeneral(impactPoint, i1, i2);
	}

	private boolean collide (Calculable c1, Calculable c2) {
		boolean test;
		test = c1.getCollider().collidesWith(c2.getCollider());
		test = test || c2.getCollider().collidesWith(c1.getCollider());
		return test;
	}
	

	private void deplacerPersonnage(Personnage p) {
	  	Point posAct = p.getPosition();
		double[] vitAct = p.getVitesseCourante();
		
		double nvX = posAct.getX() + vitAct[0]*tpsFrame;
		double nvY = posAct.getY() + vitAct[1]*tpsFrame;
		
		double nvVitX = vitAct[0] - vitAct[0]*frt;
		double nvVitY = vitAct[1];
		nvVitY += gravite*tpsFrame/1000; //axeY vers le bas
		p.setEtat(Personnage.SUR_PLATEFORME, 0);
		p.setPosition(nvX,nvY);
		p.setVitesseCourante(nvVitX,nvVitY);
	}
	
	private void deplacerAttaque(Attaque a) {
		Point posAct = a.getPosition();
		double[] vitAct = a.getVitesseCourante();
		
		
		
		double nvX = posAct.getX() + vitAct[0]*tpsFrame/1000;
		double nvY = posAct.getY() + vitAct[1]*tpsFrame/1000;
		
		double nvVitX = vitAct[0];
		double nvVitY = vitAct[1];
		
		a.setPosition(nvX,nvY);
		a.setVitesse(nvVitX,nvVitY);
	}
	  	

	private void majCollision(Personnage p1,Personnage p2,Collision c) {
		double[] vit1 = p1.getVitesseCourante();
		double[] vit2 = p2.getVitesseCourante();
		
		if (vit1[0]==0 && vit1[1]==0) {
			p1.setVitesseCourante(vit2[0],vit1[1]);
		} else if (vit2[0]==0 && vit2[1]==0) {
			p2.setVitesseCourante(vit1[0], vit2[1]);
		}
		
	}

	private void majCollision(Personnage p,Plateforme pf,Collision c) {
		double[] vitAct = p.getVitesseCourante();

		int colSide = collideSide(pf,p,c);
		
		if((colSide == 0 || colSide == 2) &&!pf.estTraversable()) {  //faces laterales
		  	p.setVitesseCourante(-2*vitAct[0]/3,vitAct[1]);
		  	if(colSide==2) {
		  		p.setPosition(pf.getPosition().getX()+(1.01*pf.getLargeur()/2)+(p.getCollider().getTailles()[0]/2),p.getPosition().getY());
		  	} else {
		  		p.setPosition(pf.getPosition().getX()-(1.01*pf.getLargeur()/2)-(p.getCollider().getTailles()[0]/2),p.getPosition().getY());
		  	}
		
		} else if(colSide == 1) {  //face superieure
		  	if(p.getEtat(Personnage.SUR_PLATEFORME) > 0 && vitAct[1] >0) { 
		    		if(!pf.estTraversable()) {
		      			p.setVitesseCourante(vitAct[0],0);
		    		}
		  	} else if(p.getEtat(Personnage.SUR_PLATEFORME) == 0 && vitAct[1]>0) {
		  		if (p.getEtat(Personnage.STUN) == 0) {
		  				p.setEtat(Personnage.SUR_PLATEFORME,1);
		  				p.setVitesseCourante(vitAct[0],0);
		  				p.setPosition(p.getPosition().getX(),pf.getPosition().getY()-pf.getHauteur()/2-p.getCollider().getTailles()[1]/2);
		  				p.setEtat(Personnage.SAUT_EFFECTUE,0);
		  		} else {
		    		p.setVitesseCourante(vitAct[0],-2*vitAct[1]/3);
		  		}
		  	}
		} else if(colSide == 3) {  //face inferieure
			
		  	if(!pf.estTraversable()){
		  			if(p.getVitesseCourante()[1]<0) {
		  				p.setVitesseCourante(vitAct[0],1*Math.abs(vitAct[1])/3);
		  			}
		    		p.setPosition(p.getPosition().getX(),pf.getPosition().getY()+(pf.getHauteur()/2)+(p.getCollider().getTailles()[1]/2));
		  	}
		}
	}
	
	private void majCollision(Attaque atk, Personnage p, Collision c){
		if (!atk.getProprietaire().equals(p)) {		
			atk.affecter(p);
		}
	}
	
	private void majCollision(Attaque atk, Plateforme pf, Collision c) {
		double[] vitAct = atk.getVitesseCourante();
		int colSide = collideSide(pf,atk,c);
		
		if(colSide == 0 || colSide == 2) {  //faces laterales
		  	atk.setVitesse(-vitAct[0],vitAct[1]);
		
		} else if(colSide == 1 || colSide == 3) {//face superieure,inferieure
			atk.setVitesse(vitAct[0], -vitAct[1]);
		}
	}

	private int collideSide(Plateforme percute,Calculable percutant, Collision c) {
	  	int side;
		Point impact = c.getEmplacementPoint();

		double sommet = 0.5*Math.atan(percute.getHauteur()/percute.getLargeur());
		double tempX = impact.getX() - percute.getPosition().getX();
		double tempY = impact.getY() - percute.getPosition().getY();
		double angleImpact = Math.atan2(tempY,tempX);
		
		if (angleImpact>-sommet && angleImpact<sommet) {
			side = 2;
		} else if (angleImpact>=sommet && angleImpact<=Math.PI-sommet) {
			side = 3;
		} else if (angleImpact<=-sommet && angleImpact>=sommet-Math.PI) {
			side = 1;
		} else {
			side = 0;
		}
		
		return side;
	}

}
