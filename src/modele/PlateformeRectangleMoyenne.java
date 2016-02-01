package modele;

import physique.collider.*;

/**une plateforme rectangle 90 * 30*/

public class PlateformeRectangleMoyenne extends PlateformeBase{
	
	/**Prend la position du centre du rectangle*/
	public PlateformeRectangleMoyenne (Point positionCentre) {
		super(200,30,false, positionCentre, new Rectangle(200, 30, positionCentre), "PlateformeRectangleMoyenne");	
	} 
	
} 