package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import jeu.Jeu;

public class PauseAfficheur implements Afficheur {
	public final static Color COULEUR_OMBRE = Color.orange;
	public final static Color COULEUR_TEXTE = Color.RED;
	public final static int POSITION_X = 200;
	public final static int POSITION_Y = Jeu.HAUTEUR_PX/2;
	public final static int FONT_SIZE = 200;
	
	@Override
	public int getDureeVie() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void decrementerDureeVie() {}

	@Override
	public void afficher(Graphics g) {
		// TODO Auto-generated method stub
		String texteAffichage = "En Pause !";
		Graphics2D g2d = (Graphics2D)g; 
		g2d.setFont(new Font("Verdana", Font.BOLD, FONT_SIZE));
		//on dessine l'ombre
		g2d.setColor(COULEUR_OMBRE);
		g2d.drawString(texteAffichage, ShiftWest(POSITION_X, 5), ShiftNorth(POSITION_Y, 5));
		g2d.drawString(texteAffichage, ShiftWest(POSITION_X, 5), ShiftSouth(POSITION_Y, 5));
		g2d.drawString(texteAffichage, ShiftEast(POSITION_X, 5), ShiftNorth(POSITION_Y, 5));
		g2d.drawString(texteAffichage, ShiftEast(POSITION_X, 5), ShiftSouth(POSITION_Y, 5));
		//on dessine le texte
		g2d.setColor(COULEUR_TEXTE);
		g2d.drawString(texteAffichage, POSITION_X, POSITION_Y);		
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