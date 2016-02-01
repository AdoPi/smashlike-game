package vue;

import java.awt.Graphics;
import modele.Point;
import java.awt.image.BufferedImage;

import modele.*;

/* 
SI PROJECTILE, ON L'AFFICHE, SINON ON AFFICHE PAS 
SI PAS PROJECTILE, C'EST LE PERSONNAGE QUI VA AFFICHER L'ATQ
*/

public class ProjectileAfficheur implements Afficheur {
	private Attaque attaque;
	private SpriteSheet sheet;
	private int dureeDeVie;
	private int animation;
	
	public ProjectileAfficheur(Attaque attaque) {
		this.sheet = SpriteSheet.PROJECTILES.get(attaque.getNom());
		this.animation = 1;
		this.attaque = attaque;	
	}
	
	@Override
	public int getDureeVie() {
		return attaque.getDureeDeVie();
	}
	@Override
	public void decrementerDureeVie() {
		//dureeDeVie = dureeDeVie - 1;
	}
	
	@Override
	public void afficher(Graphics g) {
		//trouver la bonne image
		BufferedImage image = this.sheet.getSprite(this.animation,1,this.attaque.getOrientation());
    	int w = (int)(image.getWidth()*sheet.getScaleLargeur());
    	int h = (int)(image.getHeight()*sheet.getScaleHauteur());
    	incrementeSequence(this.sheet.getNombreSequences());
		//recuperer la position
		Point position = this.attaque.getPositionTranslatee();
		//afficher l'image
		//g.drawImage(image, (int) Math.floor(position.getX()), (int) Math.floor(position.getY()), null);
		g.drawImage(image, (int) Math.floor(position.getX()+this.sheet.getCalageX(1)), (int) Math.floor(position.getY()+this.sheet.getCalageX(1)),h,w, null);
	}
	
	private void incrementeSequence (int nbSequencesMax) {
		//en considerant que le projectile evolue 
		this.animation = this.animation % nbSequencesMax + 1;		
	}
	
	


	
}