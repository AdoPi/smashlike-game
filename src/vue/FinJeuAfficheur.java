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
public class FinJeuAfficheur implements Afficheur {
	private Joueur joueur;
	private static int nbInstances = 0;
	public FinJeuAfficheur(Joueur joueur) {
		this.joueur = joueur;
	}	

	public void afficher(Graphics g) {
		//lissage police
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.black);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
		RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
		RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		//affichage de la police

		g2d.setFont(new Font("Verdana", Font.BOLD, 80));
		
		String chaine = "";
		if (joueur != null) {
			chaine = joueur.getNom()+" VAINQUEUR !";	
		}
		else {
			chaine = " MATCH NUL ! ";
		}


		Color couleurOmbre = Color.BLACK;
		Color couleurPourcent = Color.WHITE;
		
		
		//on dessine l'ombre
		g2d.setColor(couleurOmbre);
		int yPourcent = 400;
		int x = 300;
		g2d.drawString(chaine, ShiftWest(x, 3), ShiftNorth(yPourcent, 3));
		g2d.drawString(chaine, ShiftWest(x, 3), ShiftSouth(yPourcent, 3));
		g2d.drawString(chaine, ShiftEast(x, 3), ShiftNorth(yPourcent, 3));
		g2d.drawString(chaine, ShiftEast(x, 3), ShiftSouth(yPourcent, 3));
		//on dessine le texte
		g2d.setColor(couleurPourcent);
		g2d.drawString(chaine, x, yPourcent);

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
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public void decrementerDureeVie() {
		// TODO Auto-generated method stub
		
	}
}