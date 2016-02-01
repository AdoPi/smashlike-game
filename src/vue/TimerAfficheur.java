package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import jeu.Jeu;
import jeu.Joueur;

public class TimerAfficheur implements Afficheur {
	public final static int DUREE_AFFICHAGE = 10;
	public final static Color COULEUR_OMBRE = Color.ORANGE;
	public final static Color COULEUR_TEXTE = Color.BLACK;
	public final static int FONT_SIZE = 70;
	public final static int DECALAGE_Y = 10;
	public final static int POSITION_X = Jeu.LARGEUR_PX/2 - FONT_SIZE;
	public final static int POSITION_Y = FONT_SIZE;
	//permet de faire clignoter le chrono
	private int nbFrames; 
	private boolean afficheSeparateur;
	private int ancienneDuree;
	private Jeu jeu;
	public TimerAfficheur(Jeu jeu) {
		this.jeu = jeu;
		afficheSeparateur = true;
	}
	@Override
	public int getDureeVie() {
		return -1;
	}

	@Override
	public void decrementerDureeVie() {
	}

	@Override
	public void afficher(Graphics g) {
		int minutes = jeu.getDuree()/60;
		int secondes = jeu.getDuree()%60;
		String texteAffichage = (minutes + (afficheSeparateur ? ":" : " ")+ ((secondes < 10) ? "0"+secondes : ""+secondes));
		
		int positionY = POSITION_Y;
		Graphics2D g2d = (Graphics2D)g; 
		//lissage police
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
		RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
		RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		
		g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, FONT_SIZE));
		//on dessine l'ombre
		g2d.setColor(COULEUR_OMBRE);
		g2d.drawString(texteAffichage, ShiftWest(POSITION_X, 2), ShiftNorth(positionY, 2));
		g2d.drawString(texteAffichage, ShiftWest(POSITION_X, 2), ShiftSouth(positionY, 2));
		g2d.drawString(texteAffichage, ShiftEast(POSITION_X, 2), ShiftNorth(positionY, 2));
		g2d.drawString(texteAffichage, ShiftEast(POSITION_X, 2), ShiftSouth(positionY, 2));
		//on dessine le texte
		g2d.setColor(COULEUR_TEXTE);
		g2d.drawString(texteAffichage, POSITION_X, positionY);
		
		int nouvelleDuree = jeu.getDuree();
		
		if ( ancienneDuree != nouvelleDuree) {
			afficheSeparateur = !afficheSeparateur;
			ancienneDuree = nouvelleDuree;
		}

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