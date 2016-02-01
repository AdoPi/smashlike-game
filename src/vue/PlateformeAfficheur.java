package vue;

import java.awt.Color;
import java.awt.Graphics;

import modele.Point;

import java.awt.image.BufferedImage;

import modele.Plateforme;

public class PlateformeAfficheur implements Afficheur {
	private Plateforme plateforme;
	private SpriteSheet sheet;
	private boolean estAffichable;
	private int xHautGauche;//il n'y a pour l'instant que des positions fixes, 
	private int yHautGauche;//aisement modifiable si besoin
	
	public PlateformeAfficheur(Plateforme plateforme) {
		this.plateforme = plateforme;
		Point hautGauche = this.plateforme.getPositionTranslatee();
		xHautGauche = (int) Math.floor(hautGauche.getX());
		yHautGauche = (int) Math.floor(hautGauche.getY());
		if (SpriteSheet.PLATEFORMES.containsKey(this.plateforme.getNom())){
			this.sheet = SpriteSheet.PLATEFORMES.get(plateforme.getNom());
			this.estAffichable = true;
		} else {
			this.estAffichable = false;
		}
	}
	@Override
	public int getDureeVie() {
		return -1;
	}
	@Override
	public void decrementerDureeVie() {}
	
	@Override
	public void afficher(Graphics g) {	
		//g.drawImage(image, xHautGauche, yHautGauche, null);
		if (this.estAffichable){
			BufferedImage image = this.sheet.getSprite(1);
			g.drawImage(image,(int) Math.floor(xHautGauche+this.sheet.getCalageX(1)), (int) Math.floor(yHautGauche+this.sheet.getCalageX(1)), null);
	
		} else {
			//pour les tests
			g.setColor(Color.RED);
			g.fillRect(xHautGauche, yHautGauche,(int) plateforme.getLargeur(), (int) plateforme.getHauteur());
		}
	}

}