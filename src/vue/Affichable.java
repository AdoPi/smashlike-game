package vue;

import modele.Point;

public interface Affichable {
	
   /** Retourne le nom de l'affichable
    * @return le nom de l'affichable
    */
	public String getNom();
   
	/** Retourne la position de l'affichable adaptée pour la vue.
	 * @return Point la position translatée au "coin" haut gauche du calculable
	 */
   public Point getPositionTranslatee();

}
