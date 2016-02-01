package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import modele.Attaque;
import modele.Point;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import physique.collider.Collider;
import modele.Personnage;

/* classe qui utilise un SpriteSheet pour s'afficher */

public class PersonnageAfficheur implements Afficheur {

	private Personnage personnage;
	private SpriteSheet sheet; // la sprite sheet contenant les images du personnage
	private int animation; // indique le numero de la sequence en cours
	private int etatPrecedent = 4;
	//private static int FRAMES_PAR_IMAGE = 5;
	//private int decompteEtatCourant = 1;
	
	public PersonnageAfficheur(Personnage personnage) {
		animation = 1;
		this.personnage = personnage;
		//on recupere la feuille de sprite
		this.sheet = SpriteSheet.PERSONNAGES.get(personnage.getNom());
	}	

	public void afficher(Graphics g) {
    	Point position = this.personnage.getPositionTranslatee();
		//test
    	/***********************/
    	boolean test = false;
    	if (test) {
        	Collider hit = this.personnage.getCollider();
    		if(this.personnage.getNom() == "Mai"){
    			g.setColor(Color.green);
    		} else {
    			g.setColor(Color.blue);
    		}
    		//affichage attaques
    		Attaque a= this.personnage.getAttaqueCourante();
    		g.fillRect((int) position.getX(), (int) position.getY(), (int) hit.getTailles()[0], (int) hit.getTailles()[1]);
    		if (a!=null) {
    			Collider hitattaque = a.getCollider();
    			g.setColor(Color.yellow);
    			g.fillRect((int) a.getPositionTranslatee().getX(), (int) a.getPositionTranslatee().getY(), (int) hitattaque.getTailles()[0], (int) hitattaque.getTailles()[1]);
    		}   		
    	}

		
		/***********************/
		
		//on choisit la bonne tuile
    	int tuile = tuile();
    	
    	// calcul du scale
    	BufferedImage img = sheet.getSprite(animation,tuile,personnage.getOrientation());
    	int w = (int)(img.getWidth()*sheet.getScaleLargeur());
    	int h = (int)(img.getHeight()*sheet.getScaleHauteur());
		
    	
    	//on modifie l'indicateur d'image courante, en prenant en compte le nombre max de sequences de la tuile
		incrementeSequence(sheet.getNombreSequences(tuile),tuile);
		
		//on met à jour l'etat precedent
		this.etatPrecedent = tuile;	
		
		
		int calageX;
		//on affiche la bonne image
    	g.drawImage(img, (int) Math.floor(position.getX()+this.sheet.getCalageX(tuile)), (int) Math.floor(position.getY()+this.sheet.getCalageY(tuile)),h,w,null);			
	}

	private void incrementeSequence(int maxSequences,int tuileCourante) {
			if (tuileCourante == etatPrecedent) {
				animation = (animation % maxSequences) + 1;
			} else {
				animation = 1;
			}
	}
	private int tuile() {

		if (personnage.getEtat(Personnage.STUN) > 0) {
			return 1;
		}
		if (personnage.getEtat(Personnage.SUBIT_ATTAQUE) > 0) {
			return 3;
		}
		if (personnage.getEtat(Personnage.EN_ATTAQUE) > 0) {
			return 6 + this.personnage.getCodeAttaqueCourante();
		}
		if (personnage.getEtat(Personnage.SAUT_EFFECTUE) > 0) {
			return 2;
		}
		
		if (personnage.getEtat(Personnage.EN_MOUVEMENT) > 0) {
			return 5;
		}
			return 4;
	}

	public void decrementerDureeVie() {}

	public int getDureeVie() {
		return -1;
	}

}
