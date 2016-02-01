package modele;

import jeu.Jeu;
import physique.collider.*;

/**une plateforme rectangle 360 * 30*/

public class PlateformeRectangleDeBase extends PlateformeBase{
	
	/**Prend la position du centre du rectangle*/
	public PlateformeRectangleDeBase ( Point positionCentre) {
		super(2*Jeu.LARGEUR_PX/3,30,false, positionCentre, new Rectangle(2*Jeu.LARGEUR_PX/3, 30, positionCentre), "PlateformeRectangleDeBase");	
	}
	
} 