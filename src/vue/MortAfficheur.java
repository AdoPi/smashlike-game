package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import jeu.Jeu;
import jeu.Joueur;

public class MortAfficheur implements Afficheur {
	public final static int DUREE_AFFICHAGE = 10;
	public final static Color COULEUR_OMBRE = Color.orange;
	public final static Color COULEUR_TEXTE = Color.RED;
	public final static int POSITION_X = 200;
	public final static int POSITION_Y = Jeu.HAUTEUR_PX/2;
	public final static int FONT_SIZE = 200;
	public final static int DECALAGE_Y = 180;
	public static int nbInstances = 0;
	
	private int nbFrames;
	private Joueur joueurMort;
	private int numero;
	public MortAfficheur(Joueur joueurMort) {
		this.joueurMort = joueurMort;
		nbFrames = DUREE_AFFICHAGE;
		nbInstances++;
		if (nbInstances == 3) {
			nbInstances = 1;
		}
		numero = nbInstances;
	}
	@Override
	public int getDureeVie() {
		return nbFrames;
	}

	@Override
	public void decrementerDureeVie() {
		nbFrames--;
		if (nbFrames == 0) {
			nbInstances--;			
		}
	}

	@Override
	public void afficher(Graphics g) {
		String texteAffichage = this.joueurMort.getNom() + " K.O !";
		//si je suis le premier afficheur et qu'il y a 2 instances, j'affiche rien
		int positionY = POSITION_Y;
		if (numero == 2) {
			positionY += DECALAGE_Y;
		}
		Graphics2D g2d = (Graphics2D)g; 
		g2d.setFont(new Font("Verdana", Font.BOLD, FONT_SIZE));
		//on dessine l'ombre
		g2d.setColor(COULEUR_OMBRE);
		g2d.drawString(texteAffichage, ShiftWest(POSITION_X, 5), ShiftNorth(positionY, 5));
		g2d.drawString(texteAffichage, ShiftWest(POSITION_X, 5), ShiftSouth(positionY, 5));
		g2d.drawString(texteAffichage, ShiftEast(POSITION_X, 5), ShiftNorth(positionY, 5));
		g2d.drawString(texteAffichage, ShiftEast(POSITION_X, 5), ShiftSouth(positionY, 5));
		//on dessine le texte
		g2d.setColor(COULEUR_TEXTE);
		g2d.drawString(texteAffichage, POSITION_X, positionY);
	}
	
	
	/* methodes utilisees pour les ombres sur le texte */
	int ShiftNorth(int p, int distance) {
		return (p - distance);
	}
	int ShiftSouth(int p, int distance) {
		return (p + distance);
	}
	int ShiftEast(int p, int distance) {
		return (p + distance);
	}
	int ShiftWest(int p, int distance) {
		return (p - distance);
	}
	
}