package modele;

import physique.collider.*;

/**une plateforme carre de cote 30*/

public class PlateformeCarrePetite extends PlateformeBase{
	
	/**Prend la position du centre du carre*/
	public PlateformeCarrePetite ( Point positionCentre) {
		super(30,30,true, positionCentre, new Rectangle(30, 30, positionCentre), "PlateformeCarrePetite");	
	}
	
}