package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import jeu.Jeu;
import jeu.Joueur;
import modele.Personnage;

/* affiche les informations d'un personnage */
public class DoubleKoAfficheur implements Afficheur {

	private int dureeVie;

	public DoubleKoAfficheur() {
		dureeVie = 20;
	}		
	public void afficher(Graphics g) {
		//lissage police
		Graphics2D g2d = (Graphics2D)g;

		String texte = "DOUBLE KO!";

		int x = 200;
		int y = Jeu.LARGEUR_PX/2;
		g2d.setFont(new Font("Verdana", Font.BOLD, 100));

		Color couleurOmbre = Color.ORANGE;
		Color couleurPourcent = Color.RED;

		
		//on dessine l'ombre
		g2d.setColor(couleurOmbre);
		g2d.drawString(texte, ShiftWest(x, 3), ShiftNorth(y, 3));
		g2d.drawString(texte, ShiftWest(x, 3), ShiftSouth(y, 3));
		g2d.drawString(texte, ShiftEast(x, 3), ShiftNorth(y, 3));
		g2d.drawString(texte, ShiftEast(x, 3), ShiftSouth(y, 3));
		//on dessine le texte
		g2d.setColor(couleurPourcent);
		g2d.drawString(texte, x, y);

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
	
	
	@Override
	public int getDureeVie() {
		return dureeVie;
	}

	@Override
	public void decrementerDureeVie() {
		// TODO Auto-generated method stub
		dureeVie--;
	}
}