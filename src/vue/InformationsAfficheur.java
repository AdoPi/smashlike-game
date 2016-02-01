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
public class InformationsAfficheur implements Afficheur {
	private Joueur joueur;
	private int x;
	private int y;
	private final static int ETAPE_POURCENT_1 = 25;
	private final static int ETAPE_POURCENT_2 = 50;
	private static int nbInstances = 0;
	public InformationsAfficheur(Joueur joueur,int x,int y) {
		this.x = x;
		this.y = y;
		this.joueur = joueur;
	}	
	public InformationsAfficheur(Joueur joueur) {
		nbInstances++;
		if (nbInstances == 1) {
			this.x = Jeu.LARGEUR_PX/2-400;
		} else {
			this.x = (Jeu.LARGEUR_PX/2)+200;
		}
		
		this.y = Jeu.HAUTEUR_PX-120;
		this.joueur = joueur;
	}		
	public void afficher(Graphics g) {
		//affichage timer si le jeu en contient un
		//lissage police
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.black);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
		RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
		RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		//affichage du score
		g2d.drawString(joueur.getChaineScore(),x+50,y+100);
		g2d.setFont(new Font("Verdana", Font.BOLD, 80));
		
		int pourcent = (int)(joueur.getPersonnage().getPourcentage());
		String pourcentChaine = pourcent+"%";

		Color couleurOmbre = Color.BLACK;
		Color couleurPourcent = Color.WHITE;
		
		if (pourcent > ETAPE_POURCENT_1 && pourcent <= ETAPE_POURCENT_2) {
			couleurPourcent = new Color(0xF9690E);
		} else {
			if (pourcent > ETAPE_POURCENT_2){
				couleurPourcent = Color.RED;
			}
		}
		
		//on dessine l'ombre
		g2d.setColor(couleurOmbre);
		int yPourcent = y + 75;
		g2d.drawString(pourcentChaine, ShiftWest(x, 3), ShiftNorth(yPourcent, 3));
		g2d.drawString(pourcentChaine, ShiftWest(x, 3), ShiftSouth(yPourcent, 3));
		g2d.drawString(pourcentChaine, ShiftEast(x, 3), ShiftNorth(yPourcent, 3));
		g2d.drawString(pourcentChaine, ShiftEast(x, 3), ShiftSouth(yPourcent, 3));
		//on dessine le texte
		g2d.setColor(couleurPourcent);
		g2d.drawString(pourcentChaine, x, yPourcent);

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
